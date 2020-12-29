package com.xyt.utils.servlet;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * HttpClient get/post请求
 *
 * @Author: abby
 * @Date: 2020/11/10 9:21
 *
 */
public class HttpClientUtil {

    /**
     * 带参数的get请求
     * @param url
     * @param param
     * @return
     */
    public static String doGet(String url, Map<String, String> param){

        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        String resultString = "";
        CloseableHttpResponse httpResponse = null;

        try {
            //创建uri
            URIBuilder uriBuilder = new URIBuilder(url);
            if(param != null){
                for (String key : param.keySet()) {
                    uriBuilder.addParameter(key, param.get(key));
                }
            }
            URI uri = uriBuilder.build();
            //创建Get请求
            HttpGet get = new HttpGet(uri);
            //执行请求
            httpResponse = httpClient.execute(get);
            //判断状态码
            if (httpResponse.getStatusLine().getStatusCode() == 200){
                resultString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpResponse != null) {
                    httpResponse.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    /**
     * 不带参数的get请求
     *
     * @param url
     * @return
     */
    public static String doGet(String url){
        return doGet(url, null);
    }

    /**
     * 带参数的post请求
     * @param url
     * @param param
     * @return
     */
    public static String doPost(String url, Map<String, String> param){

        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        String resultString = "";
        CloseableHttpResponse httpResponse = null;

        try {
            //创建post请求
            HttpPost post = new HttpPost(url);
            //参数列表
            if(param != null){
                List<NameValuePair> paramList = new ArrayList();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, param.get(key)));
                }
                //模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
                post.setEntity(entity);
            }
            //执行请求
            httpResponse = httpClient.execute(post);
            //判断状态码
            resultString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpResponse != null) {
                    httpResponse.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    /**
     * 不带参数的post请求
     * @param url
     * @return
     */
    public static String doPost(String url){
        return doPost(url, null);
    }

    /**
     * 参数为json的post请求
     * @param url
     * @param json
     * @return
     */
    public static String doPostJson(String url, String json){
        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse httpResponse = null;

        try {
            //创建post请求
            HttpPost post = new HttpPost(url);
            //创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            post.setEntity(entity);
            //执行请求
            httpResponse = httpClient.execute(post);
            //判断状态码
            resultString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpResponse != null) {
                    httpResponse.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

}
