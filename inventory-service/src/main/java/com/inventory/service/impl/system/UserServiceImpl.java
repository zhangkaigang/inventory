package com.inventory.service.impl.system;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.inventory.dao.system.UserDao;
import com.inventory.dao.system.UserRoleDao;
import com.inventory.po.system.User;
import com.inventory.po.system.UserRole;
import com.inventory.service.system.UserService;
import com.inventory.util.CommonConstants;
import com.inventory.util.PoJoConverterUtil;
import com.inventory.vo.system.UserVO;
import org.apache.commons.collections.MapUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static org.apache.commons.collections.MapUtils.getIntValue;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2019/4/18 14:56
 * @Version:1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public User findUserByLoginName(String loginName) {
        return userDao.findUserByLoginName(loginName);
    }

    @Override
    public PageInfo<UserVO> queryUserList(Map<String, Object> pageMap, UserVO userVO) {
        int currentPage = MapUtils.getIntValue(pageMap, CommonConstants.CURRENT_PAGE);
        int pageSize = MapUtils.getIntValue(pageMap, CommonConstants.PAGE_SIZE);
        PageHelper.startPage(currentPage, pageSize);
        User user = PoJoConverterUtil.objectConverter(userVO, User.class);
        List<User> userList = userDao.queryUserList(user);
        List<UserVO> userVOList = PoJoConverterUtil.objectListConverter(userList, UserVO.class);
        PageInfo<UserVO> pageInfo = new PageInfo<>(userVOList);
        return pageInfo;
    }

    @Override
    public void addUser(UserVO userVO) {
        String roleIds = userVO.getRoleIds();
        User user = PoJoConverterUtil.objectConverter(userVO, User.class);
        String encodePwd = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(encodePwd);
        userDao.addUser(user);
        int userId = user.getId();
        String[] roleIdsArray = roleIds.split(",");
        addUserRole(userId, roleIdsArray);
    }

    @Override
    public void deleteUser(int id){
        // 删除用户
        userDao.deleteUser(id);
        // 删除用户关联角色
        userRoleDao.deleteUserRole(id);
    }

    @Override
    public UserVO queryUserById(int id) {
        User user = userDao.queryUserById(id);
        UserVO userVO = PoJoConverterUtil.objectConverter(user, UserVO.class);
        return userVO;
    }

    @Override
    public List<String> queryRolesByUserId(int userId) {
        return userRoleDao.queryRolesByUserId(userId);
    }

    @Override
    public void editUser(UserVO userVO) {
        String roleIds = userVO.getRoleIds();
        User user = PoJoConverterUtil.objectConverter(userVO, User.class);
        userDao.editUser(user);
        int userId = user.getId();
        String[] roleIdsArray = roleIds.split(",");
        // 先删除用户关联角色，再插入
        editUserRole(userId, roleIdsArray);
    }

    @Override
    public int setJobUser(int userId, String isJob) {
        return userDao.setJobUser(userId, isJob);
    }

    /**
     * 给用户授予角色
     * @param userId
     * @param roleIdsArray
     */
    private void addUserRole(int userId, String[] roleIdsArray){
        for(String roleId : roleIdsArray){
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(Integer.parseInt(roleId));
            userRoleDao.addUserRole(userRole);
        }
    }

    /**
     * 用户角色编辑，先删除后新增
     * @param userId
     * @param roleIdsArray
     */
    private void editUserRole(int userId, String[] roleIdsArray){
        userRoleDao.deleteUserRole(userId);
        addUserRole(userId, roleIdsArray);
    }
}
