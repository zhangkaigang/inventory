package com.inventory.controller;

import com.inventory.service.PermissionService;
import com.inventory.util.CommonConstants;
import com.inventory.util.ProcessResult;
import com.inventory.vo.PermissionVO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2019/4/26 13:46
 * @Version:1.0
 */
@Controller
@RequestMapping("/permission")
public class PermissionController extends BaseController{

    @Autowired
    private PermissionService permissionService;

    /**
     * 跳转权限列表页面
     * @return
     */
    @RequestMapping(value = "/permissionList")
    public String roleList(){
        return "/permission/permissionList";
    }

    /**
     * 查询所有权限列表，不分页
     * @param request
     * @param response
     */
    @RequestMapping(value = "/queryPermissionList")
    public void queryPermissionList(HttpServletRequest request, HttpServletResponse response){
        try{
            List<PermissionVO> permissionVOList = permissionService.queryPermissionList();
            JSONObject resultArray = new JSONObject();
            resultArray.put(CommonConstants.LAYUI_CODE, 0);
            resultArray.put(CommonConstants.LAYUI_DATA, JSONArray.fromObject(permissionVOList));
            writeResponse(resultArray, response);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 跳转到权限新增页面
     * @return
     */
    @RequestMapping(value = "/addPermissionPage")
    public String addRolePage(){
        return "/permission/addPermission";
    }

    /**
     * 添加权限
     */
    @RequestMapping(value = "/addPermission")
    @ResponseBody
    public Object addPermission(PermissionVO permissionVO){
        try{
            int i = permissionService.addPermission(permissionVO);
            if(i > 0){
                return proccessResult(CommonConstants.SUCCESS);
            }else {
                return proccessResult(CommonConstants.ERROR);
            }

        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return proccessResult(CommonConstants.ERROR);
        }
    }
}
