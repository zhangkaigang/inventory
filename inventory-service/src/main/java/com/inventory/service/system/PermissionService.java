package com.inventory.service.system;

import com.inventory.vo.system.PermissionVO;

import java.util.List;
import java.util.Map;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2019/4/26 13:51
 * @Version:1.0
 */
public interface PermissionService {

    /**
     * 根据用户id获取权限数据
     * @param map
     * @return
     */
    List<PermissionVO> queryPermissionByUserId(Map<String, Object> map);

    /**
     * 查询权限列表
     * @return
     */
    List<PermissionVO> queryPermissionList();

    /**
     * 新增权限
     * @param permissionVO
     * @return
     */
    int addPermission(PermissionVO permissionVO);

    /**
     * 编辑权限
     * @param permissionVO
     * @return
     */
    int editPermission(PermissionVO permissionVO);

    /**
     * 删除权限
     * @param id
     */
    int deletePermission(int id);
}
