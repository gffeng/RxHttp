package com.minivision.http.exception;

public interface ErrorType {

    /**
     * 请求成功
     */
    int SUCCESS = 1;
    /**
     * 未知错误
     */
    int UNKONW = 1000;

    /**
     * 解析错误
     */
    int PARSE_ERROR = 1001;

    /**
     * 解析对象为空
     */
    int EMPTY_BEAN = 1002;

    /**
     * 网络连接错误
     */
    int CONNECT_ERROR = 1003;
    /**
     * 网络连接超时
     */
    int TIME_OUT_ERROR = 1004;
    /**
     * 网络异常
     */
    int NET_ERROR = 1005;

    /**
     * http error
     */
    int HTTP_ERROR = 1010;
}
