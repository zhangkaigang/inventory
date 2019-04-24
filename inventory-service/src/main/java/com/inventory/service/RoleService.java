package com.inventory.service;

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
}
