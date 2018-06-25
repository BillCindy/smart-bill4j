package com.dynamic.bill4j.helper;

import com.dynamic.bill4j.utils.CollectionUtil;
import com.dynamic.bill4j.utils.PropsUtil;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author Administrator
 */
public class DataBaseHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataBaseHelper.class);

    private static final QueryRunner QUERY_RUNNER = new QueryRunner();
    private static final String DRIVER;
    private static final String URL;
    private static final String USERNAME;

    private static final String PASSWORD;

    private static final ThreadLocal<Connection> CONNECTION_THREAD_LOCAL = new ThreadLocal<>();

    private static final BasicDataSource BASIC_DATA_SOURCE;

    static {
        Properties conf = PropsUtil.loadProps("config.properties");
        DRIVER = conf.getProperty("jdbc.driver");
        URL = conf.getProperty("jdbc.url");
        USERNAME = conf.getProperty("jdbc.username");
        PASSWORD = conf.getProperty("jdbc.password");

        BASIC_DATA_SOURCE = new BasicDataSource();
        BASIC_DATA_SOURCE.setDriverClassName(DRIVER);
        BASIC_DATA_SOURCE.setUrl(URL);
        BASIC_DATA_SOURCE.setUsername(USERNAME);
        BASIC_DATA_SOURCE.setPassword(PASSWORD);

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            LOGGER.error("can not load jdbc driver", e);
        }
    }

    /**
     * 获取连接
     *
     * @return
     */
    public static Connection getConnection() {
        Connection connection = CONNECTION_THREAD_LOCAL.get();
        if (connection == null) {
            try {
                connection = BASIC_DATA_SOURCE.getConnection();
            } catch (SQLException e) {
                LOGGER.error("get sql connection failure", e);
                throw new RuntimeException(e);
            } finally {
                CONNECTION_THREAD_LOCAL.set(connection);
            }
        }

        return connection;
    }



    /**
     * 查询实体列表
     *
     * @param tEntityClass
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    public static <T> List<T> queryEntityList(Class<T> tEntityClass, String sql, Object... params) {
        List<T> entityList;
        Connection connection = getConnection();
        try {
            entityList = QUERY_RUNNER.query(connection, sql, new BeanListHandler<>(tEntityClass), params);
        } catch (SQLException e) {
            LOGGER.error("query entity list failure ", e);
            throw new RuntimeException(e);
        }

        return entityList;
    }

    /**
     * 查询实体
     *
     * @param tEntityClass
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    public static <T> T queryEntity(Class<T> tEntityClass, String sql, Object... params) {
        T entity;
        Connection connection = getConnection();
        try {
            entity = QUERY_RUNNER.query(connection, sql, new BeanHandler<T>(tEntityClass), params);
        } catch (SQLException e) {
            LOGGER.error("query entity failure ", e);
            throw new RuntimeException(e);
        }
        return entity;
    }

    /**
     * 执行多表连接查询，返回listMap
     *
     * @param sql
     * @param params
     * @return
     */
    public static List<Map<String, Object>> executeQuery(String sql, Object... params) {
        List<Map<String, Object>> result = null;
        Connection connection = getConnection();
        try {
            result = QUERY_RUNNER.query(connection, sql, new MapListHandler(), params);
        } catch (SQLException e) {
            LOGGER.error("execute query failure ", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    //统一的更新方法,返回受影响的行
    public static int executeUpdate(String sql, Object... params) {
        int rows = 0;

        try {
            Connection connection = getConnection();
            rows = QUERY_RUNNER.update(connection, sql, params);
        } catch (SQLException e) {
            LOGGER.error("execute update failure ", e);
            throw new RuntimeException(e);
        }
        return rows;

    }

    /**
     * 具体的更新方法，insert
     *
     * @param tEntityClass
     * @param fieldMap
     * @param <T>
     * @return
     */
    public static <T> boolean insertEntity(Class<T> tEntityClass, Map<String, Object> fieldMap) {
        if (CollectionUtil.isEmpty(fieldMap)) {
            LOGGER.error("can not insert entity: fieldMap is empty");
            return false;
        }

        String sql = "insert into " + getTableName(tEntityClass);
        StringBuilder columns = new StringBuilder("(");
        StringBuilder values = new StringBuilder("(");
        for (String fieldName : fieldMap.keySet()) {
            columns.append(fieldName).append(", ");
            values.append("?, ");
        }
        columns.replace(columns.lastIndexOf(", "), columns.length(), ")");
        values.replace(values.lastIndexOf(", "), values.length(), ")");
        sql += columns + " values " + values;

        Object[] params = fieldMap.values().toArray();

        return executeUpdate(sql, params) == 1;
    }

    private static String getTableName(Class<?> tEntityClass) {
        return tEntityClass.getSimpleName();
    }


    /**
     * 具体的更新方法，update
     *
     * @param tEntityClass
     * @param id
     * @param fieldMap
     * @param <T>
     * @return
     */

    public static <T> boolean updateEntity(Class<T> tEntityClass, long id, Map<String, Object> fieldMap) {
        if (CollectionUtil.isEmpty(fieldMap)) {
            LOGGER.error("can not update entity : fieldMap is empty ");
            return false;
        }

        String sql = "update " + getTableName(tEntityClass) + " set ";
        StringBuilder colums = new StringBuilder();
        for (String fieldName : fieldMap.keySet()) {
            colums.append(fieldName).append("=?, ");
        }

        sql += colums.substring(0, colums.lastIndexOf(", ")) + " where id =?";

        List<Object> paramList = new ArrayList<>();
        paramList.addAll(fieldMap.values());
        paramList.add(id);
        Object[] params = paramList.toArray();

        return executeUpdate(sql, params) == 1;

    }

    /**
     * 具体的更新方法，delete
     *
     * @param tEntityClass
     * @param id
     * @param <T>
     * @return
     */
    public static <T> boolean deleteEntity(Class<T> tEntityClass, long id) {
        String sql = "delete from " + getTableName(tEntityClass) + " where id=?";
        return executeUpdate(sql, id) == 1;
    }

}
