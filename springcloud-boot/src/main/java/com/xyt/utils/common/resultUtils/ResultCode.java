package com.xyt.utils.common.resultUtils;

/**
 *  相应枚举状态码
 *
 * @Author: abby
 * @Date: 2020/12/29 9:57
 */
public enum ResultCode {

    //成功
    SUCCESS(200),
    //失败
    FAIL(400),
    //证书错误
    UNAUTHORIZED(401),
    // 404
    NOT_FOUND(404),
    //服务器内部错误
    INTERNAL_SERVER_REEOR(500);

    public int code;

    ResultCode(int code){
        this.code = code;
    }
}
