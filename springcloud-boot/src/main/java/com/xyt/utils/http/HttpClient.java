package com.xyt.utils.http;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * http client
 *  URL： 统一资源定位符
 *  URI： 统一资源标识符
 *
 * @Author: abby
 * @Date: 2020/11/13 9:27
 */
public class HttpClient {

    public String send(String hostname, Integer port, Invocation invocation){

        try {
            URL url = new URL( "http", hostname, port, "/");
            HttpURLConnection httpurlConnection = (HttpURLConnection)url.openConnection();
            httpurlConnection.setRequestMethod("POST");
            //setDoOutput ---> 默认是false， 设置为true 就可以调用getOutputStream()从服务器端获得字节流
            httpurlConnection.setDoOutput(true);

            //获取字节流getOutputStream()
            OutputStream outputStream = httpurlConnection.getOutputStream();
            //包装
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            oos.writeObject(invocation);
            oos.flush();
            oos.close();

            InputStream inputStream = httpurlConnection.getInputStream();
            String result = IOUtils.toString(inputStream);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
