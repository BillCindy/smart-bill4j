package com.dynamic.bill4j.service;

import com.dynamic.bill4j.model.Customer;
import com.dynamic.bill4j.utils.PropsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author bill
 * @date 2018-6-23
 */
public class CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(Customer.class);
    private static final String DRIVER;
    private static final String URL;
    private static final String USERNAME;

    private static final String PASSWORD;

    static {
        Properties conf = PropsUtil.loadProps("config.properties");
        DRIVER = conf.getProperty("jdbc.driver");
        URL = conf.getProperty("jdbc.url");
        USERNAME = conf.getProperty("jdbc.username");
        PASSWORD = conf.getProperty("jdbc.password");

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            LOGGER.error("can not load jdbc driver", e);
        }
    }

    /**
     * 获取单个客户信息
     *
     * @param id
     * @return customer
     */
    public Customer getCustomerById(long id) {

        // TODO: 2018/6/13
        return null;
    }


    /**
     * 获取客户列表
     *
     * @return
     */
    public List<Customer> getCustomerList() {
        List<Customer> customerList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "select * from customer";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getLong("id"));
                customer.setContact(resultSet.getString("contact"));
                customer.setEmail(resultSet.getString("email"));
                customer.setName(resultSet.getString("name"));
                customer.setRemark(resultSet.getString("remark"));
                customer.setTelephone(resultSet.getString("telephone"));
                customerList.add(customer);
            }
        } catch (SQLException e) {
            LOGGER.error("execute sql error ", e);
        }

        return customerList;


    }

    /**
     * 获取客户列表
     *
     * @param keyword
     * @return
     */
    public List<Customer> getCustomerList(String keyword) {
        return null;
        // TODO: 2018/6/13
    }

    /**
     * 删除单个客户
     *
     * @param id
     * @return
     */
    public boolean deleteCustomer(long id) {
        return true;
        // TODO: 2018/6/13
    }


    /**
     * 更新客户信息
     *
     * @param fieldMap
     * @return
     */
    public boolean updateCustomer(Map<String, Object> fieldMap) {
        return false;
        // TODO: 2018/6/13
    }


    /**
     * 创建客户
     *
     * @param fieldMap
     * @return
     */
    public boolean creatCustomer(Map<String, Object> fieldMap) {
        return false;
        // TODO: 2018/6/13
    }
}
