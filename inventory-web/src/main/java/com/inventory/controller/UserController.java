package com.inventory.controller;

import com.inventory.entity.UserDTO;
import com.inventory.pojo.User;
import com.inventory.service.UserService;
import com.inventory.util.CommonConstants;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;

import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zkaigang
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param user
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public void login(UserDTO user, @RequestParam(value = "rememberMe", required = false) boolean rememberMe, HttpServletResponse response) {
        Map<String,Object> map = new HashMap<>(16);
        if (null == user) {
            map.put(CommonConstants.RESULT, CommonConstants.ERROR);
            map.put(CommonConstants.MESSAGE, "请求参数有误，请您稍后再试");
            writeResponse(map, response);
            return;
        }
        // 用户是否存在
        User existUser = userService.findUserByLoginName(user.getLoginName());
        if (existUser == null) {
            map.put(CommonConstants.RESULT, CommonConstants.ERROR);
            map.put(CommonConstants.MESSAGE, "该用户不存在，请您联系管理员");
            writeResponse(map, response);
            return;
        } else {
            // 是否离职
            if (CommonConstants.FLAG_1.equals(existUser.getIsJob())) {
                map.put(CommonConstants.RESULT, CommonConstants.ERROR);
                map.put(CommonConstants.MESSAGE, "登录用户已离职，请您联系管理员");
                writeResponse(map, response);
                return;
            }
        }
        // 用户登录
        try {
            // 1、 封装用户名、密码、是否记住我到token令牌对象 [支持记住我]
//            AuthenticationToken token = new UsernamePasswordToken(
//                    user.getLoginName(), DigestUtils.md5Hex(user.getPassword()),
//                    rememberMe);
            UsernamePasswordToken token = new UsernamePasswordToken(user.getLoginName(), DigestUtils.md5Hex(user.getPassword()));
            System.out.println(DigestUtils.md5Hex(user.getPassword()));
            token.setRememberMe(true);
            // 2、 Subject调用login
            Subject subject = SecurityUtils.getSubject();
            // 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            // 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            // 所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            subject.login(token);
            map.put(CommonConstants.RESULT, CommonConstants.SUCCESS);
        } catch (UnknownAccountException uae) {
            logger.error(uae.getMessage(), uae);
            map.put(CommonConstants.RESULT, CommonConstants.ERROR);
            map.put(CommonConstants.MESSAGE, "该用户不存在，请您联系管理员");
            writeResponse(map, response);
            return;
        } catch (IncorrectCredentialsException ice) {
            // 获取输错次数
            map.put(CommonConstants.RESULT, CommonConstants.ERROR);
            logger.error("用户登录，用户验证未通过：错误的凭证，密码输入错误！user=" + user.getMobile(),
                    ice);
            map.put(CommonConstants.MESSAGE, "用户名或密码不正确");
            writeResponse(map, response);
            return;
        } catch (LockedAccountException lae) {
            map.put(CommonConstants.RESULT, CommonConstants.ERROR);
            logger.error("用户登录，用户验证未通过：账户已锁定！user=" + user.getMobile(), lae);
            map.put(CommonConstants.MESSAGE, "账户已锁定");
            writeResponse(map, response);
            return;
        } catch (ExcessiveAttemptsException eae) {
            logger.error(
                    "用户登录，用户验证未通过：错误次数大于5次,账户已锁定！user=.getMobile()" + user, eae);
            map.put(CommonConstants.RESULT, CommonConstants.ERROR);
            map.put(CommonConstants.MESSAGE, "用户名或密码错误次数大于5次,账户已锁定!</br><span style='color:red;font-weight:bold; '>2分钟后可再次登录，或联系管理员解锁</span>");
            writeResponse(map, response);
            return;
            // 这里结合了，另一种密码输错限制的实现，基于redis或mysql的实现；也可以直接使用RetryLimitHashedCredentialsMatcher限制5次
        } catch (AuthenticationException ae) {
            // 通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            logger.error("用户登录，用户验证未通过：认证异常，异常信息如下！user=" + user.getMobile(),
                    ae);
            map.put(CommonConstants.RESULT, CommonConstants.ERROR);
            map.put(CommonConstants.MESSAGE, "用户名或密码不正确");
            writeResponse(map, response);
            return;
        } catch (Exception e) {
            logger.error("用户登录，用户验证未通过：操作异常，异常信息如下！user=" + user.getMobile(), e);
            map.put(CommonConstants.RESULT, CommonConstants.ERROR);
            map.put(CommonConstants.MESSAGE, "用户登录失败，请您稍后再试");
            writeResponse(map, response);
            return;
        }
        writeResponse(map, response);
    }





}
