package com.xyt.utils.http;

import java.net.URL;

/**
 * @Author: abby
 * @Date: 2020/11/13 10:13
 */
public class HttpProtocol implements Protocol {

    @Override
    public void start(URL url) {

        //服务器启动
        HttpServer server = new HttpServer();
        server.start(url.getHost(), url.getPort());

    }

    @Override
    public String send(URL url, Invocation invocation) {

        HttpClient client = new HttpClient();
        return client.send(url.getHost(), url.getPort(), invocation);
    }
}
