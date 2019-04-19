package com.inventory.service;

import com.inventory.entity.PermissionVO;

import java.util.List;
import java.util.Map;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2019/4/18 14:56
 * @Version:1.0
 */
public interface AuthService {

    /**
     * 根据用户id获取权限数据
     * @param map
     * @return
     */
    List<PermissionVO> findPermsByUserId(Map<String, Object> map);
}
