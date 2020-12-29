package com.xyt.utils.system.event.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @Author: abby
 * @Date: 2020/11/9 14:30
 *
 * 监听事件的第二种方式
 */
@Component
public class SpringListener2 implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ContextRefreshedEvent source = (ContextRefreshedEvent)event.getSource();
        System.out.println("监听了ContextRefreshed事件2：" + source);
    }
}
