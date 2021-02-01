package com.xyt.config;

import com.xyt.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @className: WebSecurityConfig
 * @Description:
 * @Author: abby
 * @Date: 2021/1/22 14:32
 */
@Configuration
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        //String hashpw = BCrypt.hashpw("123456", BCrypt.gensalt());
        //通过BCrypt方式加密
        String hashpw = passwordEncoder.encode("123456");
        UserDetails userDetails = User.withUsername("admin")
                .password(hashpw)
                .authorities("admin")
                .build();
        return userDetails;
    }

    /**
     * 自定义登录页面配置
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin() //表单提交
                .loginPage("/login.html") // 自定义登录页面配置
                .loginProcessingUrl("/user/login") // 登录访问路径，必须和表单提交接口一致
                .defaultSuccessUrl("/admin/index") //  登录成功跳转默认路劲
                //若重定向至其他页面则实现AuthenticationSuccessHandler
                //.successHandler(new MyAuthenticationSuccessHandler("/main.html"))
                //登录认证失败后跳转至自定义页面
                .failureHandler(new MyAuthenticationFailureHandler("/failure.html"))
                .and().authorizeRequests()
                //白名单  无需认证可以访问
                .antMatchers("/user/login", "/admin/index").permitAll()
                .anyRequest().authenticated() //需要认证
                .and().csrf().disable();//关闭csrf防护

        //用以精准控制会话何时创建以及与Spring Security如何交互
        /**
         *    机制           描述
         *  1.always         如果session不存在总是需要创建
         *  2.ifRequired     如果需要就创建一个session(默认) 登录的时候
         *  3.never          Spring Security 将不会创建session，但是如果应用中其他地方创建了session，那
         *                                  么Spring Security将会使用它
         *  4.stateless      Security永远不会创建session  也不是用session。并且
         *                               他会按时不使用cookie，所以每个请求都会重新认证，
         *                           ---->这种无状态架构适用与REST API及其无状态认证机制
         */
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
    }

    /**
     * 配置自定义 用户认证
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userService);
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
