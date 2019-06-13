package com.inventory.dao.system;

import com.inventory.po.system.Permission;

import java.util.List;
import java.util.Map;

/**
 * @Description:权限DAO层
 * @Author:zhang.kaigang
 * @Date:2019/4/18 14:56
 * @Version:1.0
 */
public interface PermissionDao {

    /**
     * 根据用户ID和权限类型(菜单或者功能权限)获取权限数据
     * @param map
     * @return
     */
    List<Permission> queryPermissionByUserId(Map<String, Object> map);

    /**
     * 查询权限列表
     * @return
     */
    List<Permission> queryPermissionList();

    /**
     * 新增权限
     * @param permission
     * @return
     */
    int addPermission(Permission permission);

    /**
     * 编辑权限
     * @param permission
     * @return
     */
    int editPermission(Permission permission);

    /**
     * 删除权限
     * @param id
     * @return
     */
    int deletePermission(int id);

}
