package com.inventory.dao;

import com.inventory.po.Role;

import java.util.List;

/**
 * @Description:角色权限
 * @Author:zhang.kaigang
 * @Date:2019/4/18 14:56
 * @Version:1.0
 */
public interface RoleDao {

    /**
     * 查询角色列表
     * @return
     */
    List<Role> queryRoleList();

    /**
     * 新增角色
     * @param role
     * @return
     */
    int addRole(Role role);

    /**
     * 编辑角色
     * @param role
     * @return
     */
    int editRole(Role role);

    /**
     * 删除角色
     * @param id
     * @return
     */
    int deleteRole(int id);

}
