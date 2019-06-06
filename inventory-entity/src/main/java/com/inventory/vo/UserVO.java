package com.inventory.vo;

import java.util.List;

/**
 * @Description:用户信息VO类
 * @Author:zhang.kaigang
 * @Date:2019/4/19 12:03
 * @Version:1.0
 */
public class UserVO implements java.io.Serializable{

    /**
     * 主键
     */
    private Integer id;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 真实姓名
     */
    private String realName;

    private String password;

    private String isJob;
    private String createDate;

    /**
     * 菜单权限
     */
    private List<PermissionVO> menuList;

    /**
     * 功能权限
     */
    private List<PermissionVO> permissionList;

    /**
     * 角色id，以逗号隔开
     */
    private String roleIds;

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIsJob() {
        return isJob;
    }

    public void setIsJob(String isJob) {
        this.isJob = isJob;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public List<PermissionVO> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<PermissionVO> menuList) {
        this.menuList = menuList;
    }

    public List<PermissionVO> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<PermissionVO> permissionList) {
        this.permissionList = permissionList;
    }
}
