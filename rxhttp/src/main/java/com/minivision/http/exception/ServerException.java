package com.minivision.http.exception;

/**
 * 自定义异常
 *
 * @author gf
 * @date 2019/10/22
 */
public class ServerException extends RuntimeException {
    public int code;
    public String message;

    public ServerException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
