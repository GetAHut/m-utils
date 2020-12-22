package com.xyt.system.web;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Springboot中的内置容器
 *
 * @Author: abby
 * @Date: 2020/11/11 10:53
 */
//@Configuration
public class TomcatConfig {

    /**
     * 第一种配置容器方式
     * Springboot的内置容器接口
     * @return
     */
    @Bean
    public WebServerFactoryCustomizer customizer(){
        WebServerFactoryCustomizer webServerFactoryCustomizer = new WebServerFactoryCustomizer() {
            @Override
            public void customize(WebServerFactory factory) {
                //factory 当前正在使用的web容器对象
                //将其强转为tomcat
                TomcatServletWebServerFactory tomcat = (TomcatServletWebServerFactory) factory;
                tomcat.setPort(8081);
            }
        };

        return webServerFactoryCustomizer;
    }

    /**
     * 第二种配置容器方式
     * 配置tomcat
     * @return
     */
    @Bean
    public TomcatServletWebServerFactory tomcatServletWebServerFactory(){
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.setPort(8081);
        return tomcat;
    }
}
