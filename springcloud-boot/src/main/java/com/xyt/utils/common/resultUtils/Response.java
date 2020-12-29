package com.xyt.utils.common.resultUtils;

/**
 *
 * @className: Response
 * @Description: 客户端响应结果集
 * @Author: abby
 * @Date: 2020/12/29 10:16
 */
public class Response {

    private final static String SUCCESS = "success";

    private final static String FAIL = "fail";

    public static <T> ResponseResult<T> success(){
        return new ResponseResult<T>().setCode(ResultCode.SUCCESS).setMessage(SUCCESS);
    }

    public static <T> ResponseResult<T> success(T data){
        return new ResponseResult<T>().setCode(ResultCode.SUCCESS).setMessage(SUCCESS).setData(data);
    }

    public static <T> ResponseResult<T> success(String message){
        return new ResponseResult<T>().setCode(ResultCode.SUCCESS).setMessage(message);
    }

    public static <T> ResponseResult<T> success(String message, T data){
        return new ResponseResult<T>().setCode(ResultCode.SUCCESS).setMessage(message).setData(data);
    }

    public static <T> ResponseResult<T> error(String message){
        return new ResponseResult<T>().setCode(ResultCode.INTERNAL_SERVER_REEOR).setMessage(message);
    }

    public static <T> ResponseResult<T> error(ResultCode code, String message){
        return new ResponseResult<T>().setCode(code).setMessage(message);
    }

    public static <T> ResponseResult<T> error(ResultCode code, String message, T data){
        return new ResponseResult<T>().setCode(code).setMessage(message).setData(data);
    }
}
