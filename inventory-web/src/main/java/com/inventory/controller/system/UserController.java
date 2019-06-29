package com.inventory.controller.system;

import com.github.pagehelper.PageInfo;
import com.inventory.annotation.BusinessLogAnnotation;
import com.inventory.controller.BaseController;
import com.inventory.log.LogObjectHolder;
import com.inventory.po.system.User;
import com.inventory.service.system.RoleService;
import com.inventory.service.system.UserService;
import com.inventory.util.CommonConstants;
import com.inventory.vo.system.PermissionVO;
import com.inventory.vo.system.RoleVO;
import com.inventory.vo.system.UserVO;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class UserController extends BaseController {

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
    public void queryUserList(@RequestParam(value = "page", defaultValue="1") Integer currentPage,
                              @RequestParam(value = "limit", defaultValue="10") Integer pageSize,
                              UserVO userVO,
                              HttpServletResponse response){
        //拼装分页信息
        Map<String, Object> pageMap = new HashMap();
        pageMap.put(CommonConstants.CURRENT_PAGE, currentPage);
        pageMap.put(CommonConstants.PAGE_SIZE, pageSize);
        PageInfo<UserVO> pageInfo = userService.queryUserList(pageMap,userVO);
        JSONObject resultObject = resultJSONObject(pageInfo);
        writeResponse(resultObject, response);
    }

    /**
     * 跳转到用户新增页面，执行需要user:add权限
     * @return
     */
    @RequestMapping(value = "/addUserPage")
    @RequiresPermissions("user:add")
    public String addRolePage(Model model){
        try{
            // 查询所有角色
            List<RoleVO> roleVOList = roleService.queryRoleList();
            model.addAttribute("roleList", roleVOList);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }
        return "/user/addUser";
    }

    /**
     * 添加用户
     */
    @RequestMapping(value = "/addUser")
    @ResponseBody
    @BusinessLogAnnotation(name = "添加用户", key = "loginName", keyDesc = "用户名", column = "login_name", table = "sys_user")
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
    @BusinessLogAnnotation(name = "删除用户", key = "id", keyDesc = "用户ID", column = "id", table = "sys_user")
    public Object deleteUser(@RequestParam("id") Integer id) {
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
    public String editUserPage(@RequestParam("id") Integer userId, Model model){
        try{
            // 根据id查询用户信息
            UserVO userVO = userService.queryUserById(userId);
            model.addAttribute("userVO", userVO);
            // 临时存放要修改的bean
            LogObjectHolder.me().setOldObject(userVO);
            // 查询所有角色
            List<RoleVO> roleVOList = roleService.queryRoleList();
            model.addAttribute("roleList", roleVOList);
            // 查询用户所拥有的角色
            List<String> existRolesList = userService.queryRolesByUserId(userId);
            String existRolesStr = StringUtils.join(existRolesList, ",");
            model.addAttribute("existRolesStr", existRolesStr);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }
        return "/user/editUser";
    }

    /**
     * 修改用户
     * @param userVO
     * @return
     */
    @RequestMapping(value = "/editUser")
    @ResponseBody
    @BusinessLogAnnotation(name = "修改用户", key = "loginName", keyDesc = "用户名")
    public Object editUser(UserVO userVO){
        try{
            // 临时存放要修改的bean
            LogObjectHolder.me().setNewObject(userVO);

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
