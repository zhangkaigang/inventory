package com.inventory.controller;

import com.inventory.po.User;
import com.inventory.service.UserService;
import com.inventory.util.CommonConstants;
import com.inventory.vo.UserVO;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2019/4/18 14:56
 * @Version:1.0
 */
@Controller
public class IndexController extends BaseController{

    private static final String SHIRO_LOGIN_FAILURE = "shiroLoginFailure";
    private static final String RANDOM_CODE_ERROR = "randomCodeError";


    @Autowired
    private UserService userService;

    /**
     * 登录页面
     * @return
     */
    @RequestMapping(value = "toLogin")
    public String toLogin(){
        return "login";
    }

    /**
     * 登陆提交地址，和applicationContext-shiro.xml中配置的loginurl一致
     * 此方法不处理登陆成功（认证成功），shiro认证成功会自动跳转到上一个请求路径
     * @return
     */
    @RequestMapping("login")
    @ResponseBody
    public Object login(UserVO userVO, HttpServletRequest request){
        // 1.校验请求参数
        if(null == userVO || !checkParams(userVO)){
            return processResult(CommonConstants.ERROR, "请求参数错误，请联系管理员");
        }
        try{
            // 2.验证码校验
            String realCode = (String)request.getSession().getAttribute("validateCode");
            if(!userVO.getCode().equalsIgnoreCase(realCode)){
                return processResult(CommonConstants.ERROR, "验证码输入错误");
            }
            // 3.判断用户是否存在
            User user = userService.findUserByLoginName(userVO.getLoginName());
            if(null == user){
                return processResult(CommonConstants.ERROR, "该用户不存在");
            }
            // 4.判断用户是否离职，离职则不能登录
            if(CommonConstants.FLAG_1.equals(user.getIsJob())){
                return processResult(CommonConstants.ERROR, "该用户已离职");
            }
            // 5. 开始登录
            boolean rememberMe = false;
            String rememberMeStr = userVO.getRememberMe();
            if("on".equals(rememberMeStr)){
                rememberMe = true;
            }
            // 5.1封装用户名、密码、是否记住我到token令牌对象 [支持记住我]
            AuthenticationToken token = new UsernamePasswordToken(
                    user.getLoginName(), userVO.getPassword(),
                    rememberMe);
            // 5.2Subject调用login
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            // 6.将用户信息保存到session
            UserVO sessionUser = (UserVO) subject.getPrincipal();
            // 获取session
            HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
            HttpSession session = httpServletRequest.getSession();
            // 把用户信息保存到session
            session.setAttribute("sessionUser", sessionUser);
            return processResult(CommonConstants.SUCCESS);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return processResult(CommonConstants.ERROR);
        }

    }



    /**
     * 首页
     * @return
     */
    @RequestMapping(value = "home")
    public String home(){
        return "home";
    }

    /**
     * 校验登录参数
     * @param userVO
     * @return
     */
    private boolean checkParams(UserVO userVO){
        String loginName = userVO.getLoginName();
        String password = userVO.getPassword();
        String code = userVO.getCode();
        // 验证输入参数不为空
        if(StringUtils.isEmpty(loginName) || StringUtils.isEmpty(password) || StringUtils.isEmpty(code)){
            return false;
        }
        return true;
    }


}
