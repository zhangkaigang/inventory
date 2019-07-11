package com.inventory.controller.system;

import com.inventory.annotation.BusinessLogAnnotation;
import com.inventory.controller.BaseController;
import com.inventory.po.system.RolePermission;
import com.inventory.service.system.PermissionService;
import com.inventory.shiro.CustomRealm;
import com.inventory.util.CommonConstants;
import com.inventory.vo.system.PermissionVO;
import com.inventory.vo.system.RoleVO;
import com.inventory.service.system.RoleService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

/**
 * @Description:角色控制器
 * @Author:zhang.kaigang
 * @Date:2019/4/23 19:10
 * @Version:1.0
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private CustomRealm customRealm;

    /**
     * 跳转角色列表页面
     * @return
     */
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

    /**
     * 跳转到角色新增页面
     * @return
     */
    @RequestMapping(value = "/addRolePage")
    @RequiresPermissions("role:add")
    public String addRolePage(){
        return "/role/addRole";
    }

    /**
     * 角色新增
     * @param roleVO
     * @return
     */
    @RequestMapping(value = "/addRole")
    @ResponseBody
    @BusinessLogAnnotation(name = "添加角色", key = "roleName", keyDesc = "角色名称", column = "role_name", table = "sys_role")
    public Object addRole(RoleVO roleVO){
        try{
            String permissionIds = roleVO.getPermissionIds();
            if(StringUtils.isEmpty(permissionIds)){
                return processResult(CommonConstants.ERROR, "未授权，请您给该角色授权");
            }
            if(null == roleVO){
                return processResult(CommonConstants.ERROR, "请您填写完整的角色数据");
            }
            roleService.addRole(roleVO, permissionIds);
            return processResult(CommonConstants.SUCCESS);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return processResult(CommonConstants.ERROR);
        }
    }

    /**
     * 跳转到角色编辑页面
     * @return
     */
    @RequestMapping(value = "/editRolePage")
    @RequiresPermissions("role:edit")
    public String editRolePage(){
        return "/role/editRole";
    }

    /**
     * 角色编辑
     * @param roleVO
     * @return
     */
    @RequestMapping(value = "/editRole")
    @ResponseBody
    @BusinessLogAnnotation(name = "修改角色", key = "roleName", keyDesc = "角色名称")
    public Object editRole(RoleVO roleVO){
        try{
            String permissionIds = roleVO.getPermissionIds();
            if(StringUtils.isEmpty(permissionIds)){
                return processResult(CommonConstants.ERROR, "未授权，请您给该角色授权");
            }
            if(null == roleVO){
                return processResult(CommonConstants.ERROR, "请您填写完整的角色数据");
            }
            roleService.editRole(roleVO, permissionIds);
            // 修改完角色的权限清除缓存
            customRealm.clearCached();
            return processResult(CommonConstants.SUCCESS);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return processResult(CommonConstants.ERROR);
        }
    }

    /**
     * 根据角色ID查询权限ID集合
     * @return
     */
    @RequestMapping(value = "/queryPermissionsByRoleId")
    @ResponseBody
    public Object queryPermissionsByRoleId(HttpServletRequest request, HttpServletResponse response){
        try{
            String roleId = request.getParameter("roleId");
            // 角色下的权限
            List<RolePermission> permissionList = roleService.queryPermissionsByRoleId(Integer.parseInt(roleId));
            // 全部权限
            List<PermissionVO> allPermissionList = permissionService.queryPermissionList();
            for (RolePermission rolePermission : permissionList) {
                // 设置角色下的权限checked状态为：true
                for (PermissionVO permissionVO : allPermissionList) {
                    if(Objects.equals(rolePermission.getPermissionId(), permissionVO.getId())){
                        permissionVO.setChecked(true);
                    }
                }
            }
            return processResult(CommonConstants.SUCCESS, "", allPermissionList);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return processResult(CommonConstants.ERROR);
        }

    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteRole")
    @ResponseBody
    @RequiresPermissions("role:delete")
    @BusinessLogAnnotation(name = "删除角色", key = "id", keyDesc = "角色ID", column = "id", table = "sys_role")
    public Object deleteRole(@RequestParam("id") Integer id){
        try {
            roleService.deleteRole(id);
            return processResult(CommonConstants.SUCCESS);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return processResult(CommonConstants.ERROR);
        }
    }
}
