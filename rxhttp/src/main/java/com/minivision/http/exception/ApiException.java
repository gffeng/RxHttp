package com.minivision.http.exception;

/**
 * 自定义的异常,处理解析网络时的错误
 *
 * @date 2019/10/22
 * @author gf
 */
public class ApiException extends Exception {

    public int code;
    public String message;

    public ApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }

    @Override public String toString() {
        return "ApiException{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
