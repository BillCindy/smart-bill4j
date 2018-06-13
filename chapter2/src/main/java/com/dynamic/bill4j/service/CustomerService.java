package com.dynamic.bill4j.service;

import com.dynamic.bill4j.model.Customer;

import java.util.List;
import java.util.Map;

public class CustomerService {


    /**
     * 获取单个客户信息
     *
     * @param Id
     * @return customer
     */
    public Customer getCustomerById(long Id) {

        // TODO: 2018/6/13
        return null;
    }


    /**
     * 获取客户列表
     *
     * @return
     */
    public List<Customer> getCustomerList() {
        return null;
        // TODO: 2018/6/13
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
     * @param Id
     * @return
     */
    public boolean deleteCustomer(long Id) {
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
