package com.inventory.service.impl;

import com.inventory.dao.RoleDao;
import com.inventory.po.Role;
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

    @Override
    public List<RoleVO> queryRoleList() {
        List<Role> roleList = roleDao.queryRoleList();
        List<RoleVO> roleVOList = PoJoConverter.mapList(roleList, RoleVO.class);
        return roleVOList;
    }
}
