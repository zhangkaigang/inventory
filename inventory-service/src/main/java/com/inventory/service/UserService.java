package com.inventory.service;

import com.inventory.pojo.User;

import java.util.List;

/**
 * @author zkaigang
 */
public interface UserService {
    /**
     * 根据登录名查询用户数据
     * @param loginName
     * @return
     */
    User findUserByLoginName(String loginName);

}
