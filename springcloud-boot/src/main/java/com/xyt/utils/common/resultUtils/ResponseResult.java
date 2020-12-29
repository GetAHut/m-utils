package com.xyt.utils.common.resultUtils;

/**
 *  返回结果封装
 *
 * @Author: abby
 * @Date: 2020/12/29 10:02
 */
public class ResponseResult<T> {

    private int code; //状态码
    private String message; //消息
    private T data;  // 数据


    public int getCode(){
        return code;
    }

    public ResponseResult<T> setCode(ResultCode code) {
        this.code = code.code;
        return this;
    }

    public String getMessage(){
        return message;
    }

    public ResponseResult<T> setMessage(String message){
        this.message = message;
        return this;
    }

    public T getData(){
        return data;
    }

    public ResponseResult<T> setData(T data){
        this.data = data;
        return this;
    }
}
