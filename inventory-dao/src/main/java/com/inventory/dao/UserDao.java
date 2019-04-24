package com.inventory.dao;

import com.inventory.po.User;

/**
 * @Description:用户信息DAO类
 * @Author:zhang.kaigang
 * @Date:2019/4/18 14:56
 * @Version:1.0
 */
public interface UserDao {

    /**
     * 根据登录名查询用户
     * @param loginName
     * @return
     */
    User findUserByLoginName(String loginName);
}
