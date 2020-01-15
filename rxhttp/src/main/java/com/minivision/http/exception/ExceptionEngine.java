package com.minivision.http.exception;

import android.net.ParseException;
import com.google.gson.JsonParseException;
import com.google.gson.stream.MalformedJsonException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import org.json.JSONException;
import retrofit2.HttpException;

/**
 * 解析http异常
 *
 * @author gf
 * @date 2019/10/22
 */
public class ExceptionEngine {
    //对应HTTP的状态码
    private static final int FAIL = 0;
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static ApiException handleException(Throwable e) {
        ApiException ex;

        if (e instanceof HttpException) {
            //HTTP错误
            HttpException httpException = (HttpException) e;
            ex = new ApiException(e, httpException.code());
            switch (httpException.code()) {
                case UNAUTHORIZED:
                    ex.message = "请求要求身份验证";
                    break;
                case FORBIDDEN:
                    ex.message = "服务器拒绝请求";
                    break;
                case NOT_FOUND:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                    ex.message = "服务器异常";
                    break;
                case REQUEST_TIMEOUT:
                    ex.message = "请求超时";
                    break;
                case GATEWAY_TIMEOUT:
                    ex.message = "网关超时";
                    break;
                default:
                    ex.message = "网络错误";
                    break;
            }
        } else if (e instanceof ServerException) {
            //服务器返回的错误
            ServerException resultException = (ServerException) e;
            ex = new ApiException(resultException, resultException.code);
            ex.message = resultException.message;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException || e instanceof MalformedJsonException) {
            //均视为解析错误
            ex = new ApiException(e, ErrorType.PARSE_ERROR);
            ex.message = "解析错误";
        } else if (e instanceof ConnectException) {
            /*
             * 连接网络错误
             */
            ex = new ApiException(e, ErrorType.CONNECT_ERROR);
            ex.message = "连接失败";
        } else if (e instanceof SocketTimeoutException) {
            /*
             * 网络超时
             */
            ex = new ApiException(e, ErrorType.TIME_OUT_ERROR);
            ex.message = "网络超时";
        } else if (e instanceof UnknownHostException) {
            /*
             * 解析域名错误
             */
            ex = new ApiException(e, ErrorType.NET_ERROR);
            ex.message = "网络异常";
        } else {
            ex = new ApiException(e, ErrorType.UNKONW);
            ex.message = "未知错误";
        }
        return ex;
    }
}

