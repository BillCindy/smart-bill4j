package com.dynamic.bill4j.service;

import com.dynamic.bill4j.helper.DataBaseHelper;
import com.dynamic.bill4j.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * @author bill
 * @date 2018-6-23
 */
public class CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(Customer.class);


    /**
     * 获取单个客户信息
     *
     * @param id
     * @return customer
     */
    public Customer getCustomerById(long id) {

        String sql = "select * from customer where id =" + id;
        Customer customer = DataBaseHelper.queryEntity(Customer.class, sql);
        return customer;
    }


    /**
     * 获取客户列表
     *
     * @return
     */
    public List<Customer> getCustomerList() {
        List<Customer> customerList;

            String sql = "select * from customer";
        customerList = DataBaseHelper.queryEntityList(Customer.class, sql);
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
