package com.inventory.controller;

import com.inventory.service.UserService;
import com.inventory.util.CommonConstants;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2019/4/18 14:56
 * @Version:1.0
 */
@Controller
public class IndexController {

    private static final String SHIRO_LOGIN_FAILURE = "shiroLoginFailure";
    private static final String RANDOM_CODE_ERROR = "randomCodeError";


    @Autowired
    private UserService userService;

    /**
     * 登陆提交地址，和applicationContext-shiro.xml中配置的loginurl一致
     * 此方法不处理登陆成功（认证成功），shiro认证成功会自动跳转到上一个请求路径
     * @return
     */
    @RequestMapping("login")
    public String login(HttpServletRequest request)throws Exception{
        // 如果登陆失败从request中获取认证异常信息，shiroLoginFailure就是shiro异常类的全限定名
        String exceptionClassName = (String) request.getAttribute(SHIRO_LOGIN_FAILURE);
        // 根据shiro返回的异常类路径判断，抛出指定异常信息
        if(exceptionClassName!=null){
            if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
                request.setAttribute(CommonConstants.MESSAGE, "账号不存在");
            } else if (IncorrectCredentialsException.class.getName().equals(
                    exceptionClassName)) {
                request.setAttribute(CommonConstants.MESSAGE, "用户名/密码错误");
            } else if(RANDOM_CODE_ERROR.equals(exceptionClassName)){
                request.setAttribute(CommonConstants.MESSAGE, "验证码错误");
            }else {
                request.setAttribute(CommonConstants.MESSAGE, "用户名/密码错误");
            }
        }
        // 登陆失败还到login页面
        return "login";

    }

    /**
     * 首页
     * @return
     */
    @RequestMapping(value = "home")
    public String home(){
        return "home";
    }
}
