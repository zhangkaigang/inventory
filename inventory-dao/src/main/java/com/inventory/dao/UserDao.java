package com.inventory.dao;

import com.inventory.pojo.User;


/**
 * @author zkaigang
 */
public interface UserDao {

    /**
     * 根据登录名查询用户
     * @param loginName
     * @return
     */
    User findUserByLoginName(String loginName);
}
