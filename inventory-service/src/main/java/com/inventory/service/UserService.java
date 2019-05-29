package com.inventory.service;

import com.github.pagehelper.PageInfo;
import com.inventory.po.User;
import com.inventory.vo.UserVO;

import java.util.Map;

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

    /**
     * 查询用户列表，分页
     * @param pageMap
     * @return
     */
    PageInfo<UserVO> queryUserList(Map<String, Object> pageMap);

    /**
     * 添加用户
     * @param userVO
     * @return
     */
    void addUser(UserVO userVO);

}
