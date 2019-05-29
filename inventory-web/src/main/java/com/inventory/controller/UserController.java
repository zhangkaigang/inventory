package com.inventory.controller;

import com.github.pagehelper.PageInfo;
import com.inventory.service.RoleService;
import com.inventory.service.UserService;
import com.inventory.util.CommonConstants;
import com.inventory.vo.RoleVO;
import com.inventory.vo.UserVO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2019/4/18 14:56
 * @Version:1.0
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * 跳转到用户列表页面
     * @return
     */
    @RequestMapping(value = "/userList")
    public String userList(){
        return "/user/userList";
    }

    /**
     * 用户列表，分页
     * @param response
     */
    @RequestMapping(value = "/queryUserList")
    public void queryUserList(HttpServletRequest request, HttpServletResponse response){
        //拼装分页信息
        int currentPage =  Integer.parseInt(request.getParameter(CommonConstants.LAYUI_PAGE));
        int pageSize = Integer.parseInt(request.getParameter(CommonConstants.LAYUI_LIMIT));
        Map<String, Object> pageMap = new HashMap();
        pageMap.put(CommonConstants.CURRENT_PAGE, currentPage);
        pageMap.put(CommonConstants.PAGE_SIZE, pageSize);
        PageInfo<UserVO> pageInfo = userService.queryUserList(pageMap);
        JSONObject resultArray = new JSONObject();
        resultArray.put(CommonConstants.LAYUI_CODE, 0);
        resultArray.put(CommonConstants.LAYUI_COUNT, pageInfo.getTotal());
        resultArray.put(CommonConstants.LAYUI_DATA, JSONArray.fromObject(pageInfo.getList()));
        writeResponse(resultArray, response);
    }

    /**
     * 跳转到用户新增页面，执行需要user:add权限
     * @return
     */
    @RequestMapping(value = "/addUserPage")
    @RequiresPermissions("user:add")
    public ModelAndView addRolePage(){
        ModelAndView modelAndView = new ModelAndView("/user/addUser");
        try{
            // 查询所有角色
            List<RoleVO> roleVOList = roleService.queryRoleList();
            modelAndView.addObject("roleList", roleVOList);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }
        return modelAndView;
    }

    /**
     * 添加用户
     */
    @RequestMapping(value = "/addUser")
    @ResponseBody
    public Object addUser(UserVO userVO){
        try{
            String roleIds = userVO.getRoleIds();
            if(StringUtils.isEmpty(roleIds)){
                return processResult(CommonConstants.ERROR, "未授权，请您给该用户授予角色");
            }
            if(null == userVO){
                return processResult(CommonConstants.ERROR, "请您填写完整的用户数据");
            }
            userService.addUser(userVO);

            return processResult(CommonConstants.SUCCESS);

        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return processResult(CommonConstants.ERROR);
        }
    }


}
