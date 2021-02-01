package com.xyt.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @className: UserUtils
 * @Description:
 * @Author: abby
 * @Date: 2021/1/28 9:37
 */
public class UserUtils {

    /**
     * 获取当前登录用户的信息
     * @return
     */
    public static String getUserName(){
        //SecurityContextHolder ->
        //          会话管理， 避免每次操作都进行认证可将用户信息保存在会话中，认证后将会话信息保存在 SecurityContextHolder中
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // isAuthenticated ->指示当前用户是否已通过身份验证(已登录)
        if (!authentication.isAuthenticated()){
            return null;
        }
        Object principal = authentication.getPrincipal();
        String username = null;
        if (principal instanceof UserDetails){
            username = ((UserDetails) principal).getUsername();

        } else {
            username = principal.toString();
        }

        return username;

    }
}
