package com.minivision.http.exception;

public interface ErrorText {

    /**
     * 系统错误
     */
    String SYS_ERR ="100000";
    /**
     * 参数校验错误
     */
    String PARAMETER_ERR ="100005";
    /**
     * 不需要升级
     */
    String NONEED_ERR ="400000";
    /**
     * 获取文件下载地址错误
     */
    String GETURL_ERR ="100024";

    /**
     * 版本不存在
     */
    String VERSION_ERR ="100006";
}
