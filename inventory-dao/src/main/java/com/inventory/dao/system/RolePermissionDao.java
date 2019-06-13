package com.inventory.dao.system;

import com.inventory.po.system.RolePermission;

import java.util.List;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2019/5/8 11:02
 * @Version:1.0
 */
public interface RolePermissionDao {

    /**
     * 角色和权限关联
     * @param rolePermission
     * @return
     */
    int addRolePermission(RolePermission rolePermission);

    /**
     * 根据角色id查询权限
     * @param roleId
     * @return
     */
    List<RolePermission> queryPermissionsByRoleId(int roleId);

    /**
     * 删除角色相关权限
     * @param roleId
     * @return
     */
    int deleteRolePermission(int roleId);
}
