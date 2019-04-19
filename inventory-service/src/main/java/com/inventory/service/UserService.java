package com.inventory.service;

import com.inventory.pojo.User;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2019/4/18 14:56
 * @Version:1.0
 */
public interface UserService {
    /**
     * 根据登录名查询用户数据
     * @param loginName
     * @return
     */
    User findUserByLoginName(String loginName);

}
