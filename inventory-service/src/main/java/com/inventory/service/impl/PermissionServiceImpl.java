package com.inventory.service.impl;

import com.inventory.dao.PermissionDao;
import com.inventory.po.Permission;
import com.inventory.service.PermissionService;
import com.inventory.util.PoJoConverter;
import com.inventory.vo.PermissionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2019/4/26 13:51
 * @Version:1.0
 */
@Service
public class PermissionServiceImpl implements PermissionService{

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public List<PermissionVO> queryPermissionByUserId(Map<String, Object> map) {
        List<Permission> permissionList = permissionDao.queryPermissionByUserId(map);
        List<PermissionVO> permissionVOList = PoJoConverter.mapList(permissionList, PermissionVO.class);
        return permissionVOList;
    }

    @Override
    public List<PermissionVO> queryPermissionList() {
        List<Permission> permissionList = permissionDao.queryPermissionList();
        List<PermissionVO> permissionVOList = PoJoConverter.mapList(permissionList, PermissionVO.class);
        return permissionVOList;
    }

    @Override
    public int addPermission(PermissionVO permissionVO) {
        Permission permission = PoJoConverter.map(permissionVO, Permission.class);
        return permissionDao.addPermission(permission);
    }

    @Override
    public int editPermission(PermissionVO permissionVO) {
        Permission permission = PoJoConverter.map(permissionVO, Permission.class);
        return permissionDao.editPermission(permission);
    }

    @Override
    public int deletePermission(int id) {
        // 子节点和本身都需要删除
        return permissionDao.deletePermission(id);
    }
}
