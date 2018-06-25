package com.dynamic.bill4j;

import com.dynamic.bill4j.helper.DataBaseHelper;
import com.dynamic.bill4j.model.Customer;
import com.dynamic.bill4j.service.CustomerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Customer 单元测试类
 */
public class CustomerServiceTest {
    private final CustomerService customerService;

    public CustomerServiceTest() {
        customerService = new CustomerService();
    }

    @Before
    public void init() {
        //初始化数据库
        try {
            DataBaseHelper.executeFromSqlFile("sql/customer_init.sql");
        } catch (IOException e) {
            System.out.println("CustomerServiceTest.init execute sql failure ");
            e.printStackTrace();
        }
    }

    @Test
    public void getCustomerListTest() {
        List<Customer> customerList = customerService.getCustomerList();
        Assert.assertEquals(3, customerList.size());
    }

    @Test
    public void getCustomerTest() {
        long id = 1;
        Customer customer = customerService.getCustomerById(id);
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
        long id = 4;
        fieldMap.put("contact", "Eric");
        boolean result = customerService.updateCustomer(id, fieldMap);
        Assert.assertTrue(result);
    }


    @Test
    public void deleteCustomerTest() {
        long id = 4;
        boolean result = customerService.deleteCustomer(id);
        Assert.assertTrue(result);

    }
}
