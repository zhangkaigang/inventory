package com.inventory.service;

import com.github.pagehelper.PageInfo;
import com.inventory.po.User;
import com.inventory.vo.UserVO;

import java.util.List;
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

    /**
     * 删除用户
     * @param id
     * @return
     */
    void deleteUser(int id);

    /**
     * 获取用户所拥有的的角色id集合
     * @param userId
     * @return
     */
    List<String> queryRolesByUserId(int userId);

    /**
     * 修改用户
     * @param userVO
     */
    void editUser(UserVO userVO);

}
