package com.xyt.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * 实现登录成功后重定向至自定义的页面
 * @className: MyAuthenticationSuccessHandler
 * @Description:
 * @Author: abby
 * @Date: 2021/1/28 14:32
 */
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private String redirectUrl;

    public MyAuthenticationSuccessHandler(String redirectUrl){
        this.redirectUrl = redirectUrl;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        httpServletResponse.sendRedirect(redirectUrl);
    }
}
