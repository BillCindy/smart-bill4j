package com.dynamic.bill4j.framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author bill
 * @date 2018/7/4
 */
public class ClassUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);

    //获取当前线程的类加载器
    public static final ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    //根据类名，加载类，并决定是否进行初始化
    public static Class<?> loadClass(String className, boolean isInitialized) {
        Class<?> cls;
        try {
            cls = Class.forName(className, isInitialized, getClassLoader());
        } catch (ClassNotFoundException e) {
            LOGGER.error("load class failure", e);
            throw new RuntimeException(e);
        }
        return cls;
    }

    //获取指定包名下的所有类
    public static Set<Class<?>> getClassSet(String packageName) {
        Set<Class<?>> classSet = new HashSet<>();

        //根据包的名字，转化为文件路径url
        try {
            Enumeration<URL> urlEnumeration = getClassLoader().getResources(packageName.replace(".", "/"));
            //根据不同的协议类型，处理文件内容
            while (urlEnumeration.hasMoreElements()) {
                URL url = urlEnumeration.nextElement();
                if (url != null) {
                    String protocl = url.getProtocol();
                    if (protocl.equals("file")) {
                        String packagePath = url.getPath().replaceAll("%20", "");
                        addClass(classSet, packagePath, packageName);
                    } else if (protocl.equals("jar")) {
                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                        if (jarURLConnection != null) {
                            JarFile jarFile = jarURLConnection.getJarFile();
                            if (jarFile != null) {
                                Enumeration<JarEntry> jarEntryEnumeration = jarFile.entries();
                                while (jarEntryEnumeration.hasMoreElements()) {
                                    JarEntry jarEntry = jarEntryEnumeration.nextElement();
                                    String jarEntryName = jarEntry.getName();
                                    if (jarEntryName.endsWith(".class")) {
                                        String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
                                        doAddClass(classSet, className);
                                    }
                                }
                            }
                        }
                    }
                }
            }

        } catch (IOException e) {
            LOGGER.error("get classset failure", e);
            throw new RuntimeException(e);
        }
        return classSet;
    }

    private static void doAddClass(Set<Class<?>> classSet, String className) {
        Class<?> cls = loadClass(className, false);
        classSet.add(cls);
    }

    private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {
        File[] files = new File(packagePath).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
            }
        });
        for (File file : files) {
            String fileName = file.getName();
            if (file.isFile()) {
                String className = fileName.substring(0, fileName.lastIndexOf("."));
                if (StringUtil.isNotEmpty(packageName)) {
                    className = packageName + "." + className;
                    doAddClass(classSet, className);
                }
            } else {
                String subPackagePath = fileName;
                if (StringUtil.isNotEmpty(subPackagePath)) {
                    subPackagePath = packagePath + "/" + subPackagePath;
                }
                String subPackageName = fileName;
                if (StringUtil.isNotEmpty(subPackageName)) {
                    subPackageName = packageName + "." + subPackageName;
                }
                addClass(classSet, subPackagePath, subPackageName);
            }
        }
    }


}
