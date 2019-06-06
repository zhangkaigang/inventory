package com.inventory.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.inventory.dao.UserDao;
import com.inventory.dao.UserRoleDao;
import com.inventory.po.User;
import com.inventory.po.UserRole;
import com.inventory.service.UserService;
import com.inventory.util.CommonConstants;
import com.inventory.util.PoJoConverter;
import com.inventory.vo.UserVO;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
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
    public PageInfo<UserVO> queryUserList(Map<String, Object> pageMap) {
        int currentPage = MapUtils.getIntValue(pageMap, CommonConstants.CURRENT_PAGE, 1);
        int pageSize = MapUtils.getIntValue(pageMap, CommonConstants.PAGE_SIZE, 10);
        PageHelper.startPage(currentPage, pageSize);
        List<User> userList = userDao.queryUserList();
        List<UserVO> userVOList = PoJoConverter.mapList(userList, UserVO.class);
        PageInfo<UserVO> pageInfo = new PageInfo<>(userVOList);
        return pageInfo;
    }

    @Override
    public void addUser(UserVO userVO) {
        String roleIds = userVO.getRoleIds();
        User user = PoJoConverter.map(userVO, User.class);
        userDao.addUser(user);
        int userId = user.getId();
        String[] roleIdsArray = roleIds.split(",");
        addUserRole(userId, roleIdsArray);
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
}
