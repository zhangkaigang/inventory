package com.inventory.po.system;

import com.inventory.po.PublicPO;

/**
 * @Description:权限信息POJO类
 * @Author:zhang.kaigang
 * @Date:2019/4/18 14:56
 * @Version:1.0
 */
public class Permission extends PublicPO {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 父级ID
     */
    private Integer parentId;

    /**
     * 权限分类，0菜单，1功能
     */
    private String itemType;

    /**
     * 描述
     */
    private String description;

    /**
     * 权限编码
     */
    private String permissionCode;

    /**
     * 权限路径
     */
    private String permissionUrl;

    /**
     * 顺序
     */
    private Integer sort;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    public String getPermissionUrl() {
        return permissionUrl;
    }

    public void setPermissionUrl(String permissionUrl) {
        this.permissionUrl = permissionUrl;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
