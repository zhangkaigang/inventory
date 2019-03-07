package com.inventory.service.impl;

import com.inventory.dao.UserDao;
import com.inventory.pojo.User;
import com.inventory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zkaigang
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
