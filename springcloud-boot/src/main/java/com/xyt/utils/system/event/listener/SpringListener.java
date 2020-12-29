package com.xyt.utils.system.event.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Spring事件的监听器：
 *  1. 如何获取Spring中的监听器：
 *     a. 从Bean工厂中获取ApplicationListener类型的bean
 *     b. 扫描所有带有@EventListener注解的方法对象
 *
 *  2. Spring如何发布事件
 *      a. 判断是否是否刚兴趣
 *      b. 调用监听器方法
 *
 *  3. 监听器可以使用线程池
 *      在SimpleApplicationEventMulticaster Bean中传入线程池
 *
 * @Author: abby
 * @Date: 2020/11/9 10:19
 */
@Component
public class SpringListener {

    /**
     * ContextRefreshedEvent： 监听了Spring容器启动事件
     * @param event
     */
    @EventListener
    public void t(ContextRefreshedEvent event){
        ApplicationContext source = (ApplicationContext)event.getSource();
        System.out.println("监听了ContextRefreshed事件：" + source);
    }
}
