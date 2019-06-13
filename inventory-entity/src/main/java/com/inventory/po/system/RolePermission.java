package com.inventory.po.system;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2019/5/8 11:00
 * @Version:1.0
 */
public class RolePermission {

    private Integer roleId;
    private Integer permissionId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }
}
