package com.xyt.http;

import java.net.URL;

/**
 *
 * @Author: abby
 * @Date: 2020/11/13 10:11
 */
public interface Protocol {

    void start(URL url);

    public String send(URL url, Invocation invocation);
}
