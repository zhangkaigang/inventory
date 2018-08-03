package com.inventory.service.impl;

import com.inventory.dao.UserDao;
import com.inventory.entity.User;
import com.inventory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zkaigang on 2018/7/26.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public List<User> selectListNoPage(User user) {
        return userDao.selectListNoPage(user);
    }

    public User findUsersById(Integer id) {
        return userDao.findUsersById(id);
    }
}
