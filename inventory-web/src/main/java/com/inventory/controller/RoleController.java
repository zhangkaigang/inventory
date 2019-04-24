package com.inventory.controller;

import com.inventory.util.CommonConstants;
import com.inventory.vo.RoleVO;
import com.inventory.service.RoleService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Description:角色控制器
 * @Author:zhang.kaigang
 * @Date:2019/4/23 19:10
 * @Version:1.0
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController{

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/roleList")
    public String roleList(){
        return "/role/roleList";
    }

    /**
     * 查询所有角色列表，不分页
     * @param request
     * @param response
     */
    @RequestMapping(value = "/queryRoleList")
    public void queryRoleList(HttpServletRequest request, HttpServletResponse response){
        try{
            List<RoleVO> roleVOList = roleService.queryRoleList();
            JSONObject resultArray = new JSONObject();
            resultArray.put(CommonConstants.LAYUI_CODE, 0);
            resultArray.put(CommonConstants.LAYUI_DATA, JSONArray.fromObject(roleVOList));
            writeResponse(resultArray, response);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }
    }
}
