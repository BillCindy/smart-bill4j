package com.dynamic.bill4j;

import com.dynamic.bill4j.helper.DataBaseHelper;
import com.dynamic.bill4j.model.Customer;
import com.dynamic.bill4j.service.CustomerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
    public void init() throws IOException {
        //初始化数据库
        String file = "sql/customer_init.sql";
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String sql;
        while ((sql = reader.readLine()) != null) {
            DataBaseHelper.executeUpdate(sql);
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
