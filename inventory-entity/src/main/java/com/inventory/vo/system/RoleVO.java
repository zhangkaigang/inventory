package com.inventory.vo.system;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2019/4/23 19:58
 * @Version:1.0
 */
public class RoleVO {
    private Integer id;
    private String roleName;
    private String roleCode;
    private String description;
    private String createDate;

    // 权限id，以逗号隔开
    private String permissionIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(String permissionIds) {
        this.permissionIds = permissionIds;
    }
}
