package com.xyt.utils.system.event;

import org.springframework.context.ApplicationEvent;

/**
 * @Author: abby
 * @Date: 2020/11/9 10:15
 */

/**
 * 事件 ： 存储当前发生事情的过程
 * 事件源：发起这事件的源头
 */
public class MyEvent extends ApplicationEvent {

    public MyEvent(Object source) {
        super(source);
    }
}
