package com.xyt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @className: UserDetailsServiceImpl
 * @Description:
 * @Author: abby
 * @Date: 2021/1/22 14:27
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserDetails userDetails = User.withUsername("admin")
//                //使用noop方式加密
//                //支持的加密方式可以通过PasswordEncoderFactories 中查看
//                //spring security官方推荐的加密方式BCrypt
//                .password("{noop}123456")
//                .authorities("admin")
//                .build();
//        return userDetails;
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //String hashpw = BCrypt.hashpw("123456", BCrypt.gensalt());
        //通过BCrypt方式加密
        String hashpw = passwordEncoder.encode("123456");
        UserDetails userDetails = User.withUsername("admin")
                .password(hashpw)
                .authorities("admin")
                .build();
        return userDetails;
    }
}
