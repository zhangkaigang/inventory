package com.inventory.controller.basic;

import com.github.pagehelper.PageInfo;
import com.inventory.controller.BaseController;
import com.inventory.service.basic.CustomerService;
import com.inventory.util.CommonConstants;
import com.inventory.vo.basic.CustomerVO;
import com.inventory.vo.system.UserVO;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2019/7/4
 * @Version:1.0
 */
@Controller
@RequestMapping("/customer")
public class CustomerController extends BaseController {

    @Autowired
    private CustomerService customerService;

    /**
     * 用户列表，分页
     * @param response
     */
    @RequestMapping(value = "/queryCustomerList")
    public void queryCustomerList(@RequestParam(value = "page", defaultValue="1") Integer currentPage,
                              @RequestParam(value = "limit", defaultValue="10") Integer pageSize,
                              CustomerVO customerVO,
                              HttpServletResponse response){
        //拼装分页信息
        Map<String, Object> pageMap = new HashMap();
        pageMap.put(CommonConstants.CURRENT_PAGE, currentPage);
        pageMap.put(CommonConstants.PAGE_SIZE, pageSize);
        PageInfo<CustomerVO> pageInfo = customerService.queryCustomerList(pageMap, customerVO);
        JSONObject resultObject = resultJSONObject(pageInfo);
        writeResponse(resultObject, response);
    }
}
