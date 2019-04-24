package com.inventory.dao;

import com.inventory.vo.PermissionVO;
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
    List<PermissionVO> findPermsByUserId(Map<String, Object> map);

}
