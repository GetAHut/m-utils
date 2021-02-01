package com.xyt.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义登录认证失败后跳转处理器
 *
 * @className: MyAuthenticationFailureHandler
 * @Description:
 * @Author: abby
 * @Date: 2021/1/28 14:50
 */
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private String redirectUrl;

    public MyAuthenticationFailureHandler(String redirectUrl){
        this.redirectUrl = redirectUrl;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        httpServletResponse.sendRedirect(redirectUrl);
    }
}
