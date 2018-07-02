package com.dynamic.bill4j.framework.helper;

import com.dynamic.bill4j.framework.utils.PropsUtil;

import java.util.Properties;

/**
 * Created by bill on 2018/6/27.
 */
public class ConfigHelper {
    private static final Properties CONFIG_PROPS = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);

    //获取jdbc驱动
    public static String getJdbcDriver(){
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.JDBC_DRIVER);
    }
    //获取jdbc的url
    public static String getJdbcURL(){
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.JDBC_URL);
    }

    //获取jdbc 的username
    public static String getJdbcUserName(){
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.JDBC_USERNAME);
    }
    //获取jdbc 的password
    public static String getJdbcPassword(){
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.JDBC_PASSWORD);
    }
    //获取应用基础包名
    public static String getAppPackage(){
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.APP_BASE_PACKAGE);
    }

    // 获取静态资源路径
    public static String getAppAseetPath(){
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.APP_ASSET_PATH);
    }

    //获取jsp路径
    public static String getAppJspPaht(){
        return  PropsUtil.getString(CONFIG_PROPS,ConfigConstant.App_JSP_Path);
    }

}
