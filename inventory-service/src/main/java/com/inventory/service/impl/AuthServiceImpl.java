package com.inventory.service.impl;

import com.inventory.dao.PermissionDao;
import com.inventory.vo.PermissionVO;
import com.inventory.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2019/4/18 14:56
 * @Version:1.0
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private PermissionDao permissionDao;


    @Override
    public List<PermissionVO> findPermsByUserId(Map<String, Object> map) {
        return permissionDao.findPermsByUserId(map);
    }
}
