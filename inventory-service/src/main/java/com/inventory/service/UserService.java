package com.inventory.service;

import com.inventory.entity.User;

import java.util.List;

/**
 * Created by zkaigang on 2018/7/26.
 */
public interface UserService {
    List<User> selectListNoPage(User user);

    User findUsersById(Integer id);
}
