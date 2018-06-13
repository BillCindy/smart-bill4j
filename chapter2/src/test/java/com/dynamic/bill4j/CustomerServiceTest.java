package com.dynamic.bill4j;

import com.dynamic.bill4j.model.Customer;
import com.dynamic.bill4j.service.CustomerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Customer 单元测试类
 */
public class CustomerServiceTest {
    private final CustomerService customerService;

    public CustomerServiceTest(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Before
    public void init() {
        //初始化数据库
    }

    @Test
    public void getCustomerListTest() {
        List<Customer> customerList = customerService.getCustomerList();
        Assert.assertEquals(2, customerList.size());
    }

    @Test
    public void getCustomerTest() {
        long Id = 1;
        Customer customer = customerService.getCustomerById(Id);
        Assert.assertNotNull(customer);
    }

    @Test
    public void createCustomerTest() {
        Map<String, Object> fieldMap = new HashMap<String, Object>();
        fieldMap.put("name", "customerbill");
        fieldMap.put("contact", "bill");
        fieldMap.put("telephone", "13434343554");
        boolean result = customerService.creatCustomer(fieldMap);
        Assert.assertTrue(result);


    }

    @Test
    public void updateCustomerTest() {
        Map<String, Object> fieldMap = new HashMap<>();
        fieldMap.put("contact", "Eric");
        boolean result = customerService.updateCustomer(fieldMap);
        Assert.assertTrue(result);
    }


    @Test
    public void deleteCustomerTest() {
        long Id = 1;
        boolean result = customerService.deleteCustomer(Id);
        Assert.assertTrue(result);

    }
}