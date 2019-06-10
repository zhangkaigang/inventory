package com.inventory.controller;

import com.github.pagehelper.PageInfo;
import com.inventory.po.User;
import com.inventory.service.RoleService;
import com.inventory.service.UserService;
import com.inventory.shiro.CustomRealm;
import com.inventory.util.CommonConstants;
import com.inventory.vo.PermissionVO;
import com.inventory.vo.RoleVO;
import com.inventory.vo.UserVO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
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

    @Autowired
    private CustomRealm customRealm;

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
            User user = userService.findUserByLoginName(userVO.getLoginName());
            if(null != user){
                return processResult(CommonConstants.ERROR, "该用户名已存在");
            }
            userService.addUser(userVO);
            return processResult(CommonConstants.SUCCESS);

        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return processResult(CommonConstants.ERROR);
        }
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteUser")
    @ResponseBody
    @RequiresPermissions("user:delete")
    public Object deleteUser(@RequestParam("id") int id){
        try{
            userService.deleteUser(id);
            return processResult(CommonConstants.SUCCESS);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return processResult(CommonConstants.ERROR);
        }
    }

    /**
     * 跳转到用户编辑页面
     * @return
     */
    @RequestMapping(value = "/editUserPage")
    @RequiresPermissions("user:edit")
    public ModelAndView editUserPage(@RequestParam("id") int userId){
        ModelAndView modelAndView = new ModelAndView("/user/editUser");
        try{
            // 查询所有角色
            List<RoleVO> roleVOList = roleService.queryRoleList();
            modelAndView.addObject("roleList", roleVOList);
            // 查询用户所拥有的角色
            List<String> existRolesList = userService.queryRolesByUserId(userId);
            String existRolesStr = StringUtils.join(existRolesList, ",");
            modelAndView.addObject("existRolesStr", existRolesStr);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }
        return modelAndView;
    }

    /**
     * 修改用户
     * @param userVO
     * @return
     */
    @RequestMapping(value = "/editUser")
    @ResponseBody
    public Object editUser(UserVO userVO){
        try{
            String roleIds = userVO.getRoleIds();
            if(StringUtils.isEmpty(roleIds)){
                return processResult(CommonConstants.ERROR, "未授权，请您给该用户授予角色");
            }
            if(null == userVO){
                return processResult(CommonConstants.ERROR, "请您填写完整的用户数据");
            }
            userService.editUser(userVO);
            return processResult(CommonConstants.SUCCESS);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return processResult(CommonConstants.ERROR);
        }
    }

    /**
     * 设置用户是否离职
     * @return
     */
    @RequestMapping(value = "/setJobUser")
    @ResponseBody
    public Object setJobUser(@RequestParam("id") Integer id,
                             @RequestParam("isJob") String isJob){
        try{
            if(null == id ||  null == isJob){
                return processResult(CommonConstants.ERROR, "请求参数有误");
            }
            // 获取当前用户看是否拥有设置离职的权限
            boolean flag = false;
            UserVO userVO = (UserVO)SecurityUtils.getSubject().getPrincipal();
            List<PermissionVO> permissionVOList = userVO.getPermissionList();
            for(PermissionVO permissionVO : permissionVOList){
                if("user:setJobUser".equals(permissionVO.getPermissionCode())){
                    flag = true;
                    break;
                }
            }
            if(!flag){
                return processResult(CommonConstants.ERROR, "您没有设置是否在职的权限");
            }
            userService.setJobUser(id, isJob);
            return processResult(CommonConstants.SUCCESS);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return processResult(CommonConstants.ERROR);
        }
    }

}
