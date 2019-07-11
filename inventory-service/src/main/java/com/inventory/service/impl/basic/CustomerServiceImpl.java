package com.inventory.service.impl.basic;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.inventory.dao.basic.CustomerDao;
import com.inventory.dto.Record;
import com.inventory.po.basic.Customer;
import com.inventory.service.basic.CustomerService;
import com.inventory.util.CommonConstants;
import com.inventory.util.PoJoConverterUtil;
import com.inventory.vo.basic.CustomerVO;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2019/7/4
 * @Version:1.0
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;




    @Override
    public PageInfo<CustomerVO> queryCustomerList(Map<String, Object> pageMap, CustomerVO customerVO) {
        int currentPage = MapUtils.getIntValue(pageMap, CommonConstants.CURRENT_PAGE);
        int pageSize = MapUtils.getIntValue(pageMap, CommonConstants.PAGE_SIZE);
        PageHelper.startPage(currentPage, pageSize);
        Customer customer = PoJoConverterUtil.objectConverter(customerVO, Customer.class);
        List<Customer> userList = customerDao.queryCustomerList(customer);
        List<CustomerVO> customerVOList = PoJoConverterUtil.objectListConverter(userList, CustomerVO.class);
        PageInfo<CustomerVO> pageInfo = new PageInfo<>(customerVOList);
        return pageInfo;
    }


}
