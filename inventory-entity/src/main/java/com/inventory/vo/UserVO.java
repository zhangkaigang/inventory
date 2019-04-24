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

    /**
     * 菜单权限
     */
    private List<PermissionVO> menuList;

    /**
     * 功能权限
     */
    private List<PermissionVO> permissionList;

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
