package com.inventory.service.basic;

import com.github.pagehelper.PageInfo;
import com.inventory.dto.Record;
import com.inventory.vo.basic.CustomerVO;

import java.util.List;
import java.util.Map;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2019/7/4
 * @Version:1.0
 */
public interface CustomerService {

    PageInfo<CustomerVO> queryCustomerList(Map<String, Object> pageMap, CustomerVO customerVO);
}
