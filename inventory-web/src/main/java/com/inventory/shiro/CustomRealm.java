package com.inventory.shiro;

import com.inventory.service.PermissionService;
import com.inventory.vo.PermissionVO;
import com.inventory.vo.UserVO;
import com.inventory.po.User;
import com.inventory.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Description:自定义Realm
 * @Author:zhang.kaigang
 * @Date:2019/4/18 14:56
 * @Version:1.0
 */
public class CustomRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory
            .getLogger(CustomRealm.class);
    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    /**
     * 设置realm的名称
     * @param name
     */
    @Override
    public void setName(String name) {
        super.setName("customRealm");
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

        // token是用户输入的用户名和密码
        // 第一步从token中取出用户名
        String loginName = (String) authenticationToken.getPrincipal();
        // 调用数据层
        User user = userService.findUserByLoginName(loginName);
        // 用户不存在，直接返回null
        if (user == null) {
            return null;
        }
        // 从数据库查询到密码
        String password = user.getPassword();
        // 封装需要展示的内容
        UserVO userVO = new UserVO();
        userVO.setId(user.getId());
        userVO.setRealName(user.getRealName());
        // 根据用户id取出菜单集合
        List<PermissionVO> menuList = null;
        try{
            Map<String, Object> map = new HashMap();
            map.put("ITEM_TYPE", "0");
            map.put("USER_ID", user.getId());
            menuList = permissionService.queryPermissionByUserId(map);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }
        // 将用户菜单设置到用户信息中
        userVO.setMenuList(menuList);
        // 第一个参数 ，登陆后，需要在session保存数据
        // 第二个参数，查询到密码(加密规则要和自定义的HashedCredentialsMatcher中的HashAlgorithmName散列算法一致)
        // 第三个参数 ，realm名字
        return new SimpleAuthenticationInfo(userVO, password, getName());
    }


    /**
     * 授予角色和权限
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principalCollection) {
        // 从 principals获取主身份信息
        // 将getPrimaryPrincipal方法返回值转为真实身份类型（在上边的doGetAuthenticationInfo认证通过填充到SimpleAuthenticationInfo中身份类型）
        UserVO userVO =  (UserVO) principalCollection.getPrimaryPrincipal();
        // 根据身份信息获取权限信息，从数据库获取到权限数据
        List<PermissionVO> permissionList = null;
        try{
            Map<String, Object> map = new HashMap();
            map.put("ITEM_TYPE", "1");
            map.put("USER_ID", userVO.getId());
            permissionList = permissionService.queryPermissionByUserId(map);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }
        // 单独定一个集合对象，存放功能权限的编码
        List<String> permissions = new ArrayList<String>();
        if(permissionList!=null){
            for(PermissionVO permissionVO : permissionList){
                // 将数据库中的权限标签 符放入集合
                permissions.add(permissionVO.getPermissionCode());
            }
            userVO.setPermissionList(permissionList);
        }
        // 查到权限数据，返回授权信息(要包括 上边的permissions)
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 将上边查询到授权信息填充到simpleAuthorizationInfo对象中
        simpleAuthorizationInfo.addStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

    /**
     * 清除缓存
     */
    public void clearCached() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }

}
