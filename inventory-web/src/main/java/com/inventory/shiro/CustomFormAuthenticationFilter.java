package com.inventory.shiro;

import com.inventory.vo.UserVO;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2019/4/19 14:09
 * @Version:1.0
 */
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {

    /**
     * 重写onLoginSuccess方法，将用户信息存入session
     * @param token
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
                                     ServletResponse response) throws Exception {
        //获取已登录的用户信息
        UserVO sessionUser = (UserVO) subject.getPrincipal();
        //获取session
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        HttpSession session = httpServletRequest.getSession();
        //把用户信息保存到session
        session.setAttribute("sessionUser", sessionUser);
        return super.onLoginSuccess(token, subject, request, response);
    }
}
