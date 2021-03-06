package com.inventory.dao.system;

import com.inventory.po.system.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 查询用户列表
     * @return
     */
    List<User> queryUserList(@Param("user") User user);

    /**
     * 新增用户
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 删除用户
     * @param id
     * @return
     */
    int deleteUser(int id);

    /**
     * 修改用户
     * @param user
     * @return
     */
    int editUser(User user);

    /**
     * 根据用户id查询用户信息
     * @param id
     * @return
     */
    User queryUserById(int id);

    /**
     * 设置用户在职状态
     * @param userId
     * @param isJob
     * @return
     */
    int setJobUser(@Param("id") int userId, @Param("isJob") String isJob);
}
