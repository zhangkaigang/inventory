package com.inventory.service.impl;

import com.inventory.dao.UserDao;
import com.inventory.po.User;
import com.inventory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2019/4/18 14:56
 * @Version:1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findUserByLoginName(String loginName) {
        return userDao.findUserByLoginName(loginName);
    }
}
