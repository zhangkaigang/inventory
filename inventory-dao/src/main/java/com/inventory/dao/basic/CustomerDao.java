package com.inventory.dao.basic;

import com.inventory.po.basic.Customer;

import java.util.List;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2019/7/4
 * @Version:1.0
 */
public interface CustomerDao {

    List<Customer> queryCustomerList(Customer customer);

    int queryCountByTel(String contactTel);
}
