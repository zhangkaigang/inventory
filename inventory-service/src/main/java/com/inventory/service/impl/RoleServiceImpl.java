package com.inventory.service.impl;

import com.inventory.dao.RoleDao;
import com.inventory.dao.RolePermissionDao;
import com.inventory.po.Role;
import com.inventory.po.RolePermission;
import com.inventory.util.PoJoConverter;
import com.inventory.vo.RoleVO;
import com.inventory.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2019/4/23 20:00
 * @Version:1.0
 */
@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Override
    public List<RoleVO> queryRoleList() {
        List<Role> roleList = roleDao.queryRoleList();
        List<RoleVO> roleVOList = PoJoConverter.mapList(roleList, RoleVO.class);
        return roleVOList;
    }

    @Override
    public void addRole(RoleVO roleVO, String permissionIds) {
        Role role = PoJoConverter.map(roleVO, Role.class);
        roleDao.addRole(role);
        int roleId = role.getId();
        String[] permissionIdsArray = permissionIds.split(",");
        addRolePermission(roleId, permissionIdsArray);
    }

    @Override
    public void editRole(RoleVO roleVO, String permissionIds) {
        Role role = PoJoConverter.map(roleVO, Role.class);
        roleDao.editRole(role);
        int roleId = role.getId();
        String[] permissionIdsArray = permissionIds.split(",");
        editRolePermission(roleId, permissionIdsArray);
    }

    @Override
    public List<RolePermission> queryPermissionsByRoleId(int roleId) {
        return rolePermissionDao.queryPermissionsByRoleId(roleId);
    }

    @Override
    public int deleteRole(int id) {
        return roleDao.deleteRole(id);
    }

    /**
     * 给角色设置权限
     * @param roleId
     * @param permissionIdsArray
     */
    private void addRolePermission(int roleId, String[] permissionIdsArray){
        for(String permissionId : permissionIdsArray){
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(Integer.parseInt(permissionId));
            rolePermissionDao.addRolePermission(rolePermission);
        }
    }

    /**
     * 编辑角色权限，先删除后新增
     * @param roleId
     * @param permissionIdsArray
     */
    private void editRolePermission(int roleId, String[] permissionIdsArray){
        rolePermissionDao.deleteRolePermission(roleId);
        addRolePermission(roleId, permissionIdsArray);
    }
}
