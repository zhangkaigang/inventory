package com.inventory.service;

import com.inventory.po.RolePermission;
import com.inventory.vo.RoleVO;

import java.util.List;


/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2019/4/23 20:00
 * @Version:1.0
 */
public interface RoleService {
    /**
     * 查询角色列表
     * @return
     */
    List<RoleVO> queryRoleList();

    /**
     * 开通角色
     * @param roleVO
     * @param permissionIds
     */
    void addRole(RoleVO roleVO, String permissionIds);

    /**
     * 编辑角色
     * @param roleVO
     * @param permissionIds
     */
    void editRole(RoleVO roleVO, String permissionIds);

    /**
     * 根据角色查询权限集合
     * @param roleId
     * @return
     */
    List<RolePermission> queryPermissionsByRoleId(int roleId);

    /**
     * 删除权限
     * @param id
     * @return
     */
    int deleteRole(int id);
}
