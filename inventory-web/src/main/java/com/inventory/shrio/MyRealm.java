package com.inventory.shrio;

import com.inventory.dao.UserDao;
import com.inventory.pojo.User;
import com.inventory.service.impl.UserServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zkaigang
 */
@Service
public class MyRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory
            .getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;
    /**
     * 授予角色和权限
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 登录认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authenticationToken)
            throws AuthenticationException {
            //UsernamePasswordToken用于存放提交的登录信息
            UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
            String loginName = token.getUsername();
            // 调用数据层
            User user = userDao.findUserByLoginName(loginName);
            if (user == null) {
                // 用户不存在
                return null;
            } else {
                // 密码存在
                // 第一个参数 ，登陆后，需要在session保存数据
                // 第二个参数，查询到密码(加密规则要和自定义的HashedCredentialsMatcher中的HashAlgorithmName散列算法一致)
                // 第三个参数 ，realm名字
                return new SimpleAuthenticationInfo(user, DigestUtils.md5Hex(user.getPassword()),
                        getName());
            }
    }

}
