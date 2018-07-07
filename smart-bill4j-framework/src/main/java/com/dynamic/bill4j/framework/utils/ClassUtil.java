package com.dynamic.bill4j.framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

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
        return null;
    }
}
