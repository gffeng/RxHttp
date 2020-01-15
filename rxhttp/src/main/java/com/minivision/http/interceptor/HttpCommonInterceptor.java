package com.minivision.http.interceptor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * HTTP公共拦截器
 *
 * @author gaofeng
 * @date 2019/10/23
 */
public class HttpCommonInterceptor implements Interceptor {
    private Map<String, String> headerMap = new HashMap<>();

    private HttpCommonInterceptor() {

    }

    @Override public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();
        Request.Builder builder = oldRequest.newBuilder();
        builder.method(oldRequest.method(), oldRequest.body());
        //添加header
        if (headerMap.size() > 0) {
            for (Map.Entry<String, String> params : headerMap.entrySet()) {
                builder.header(params.getKey(), params.getValue());
            }
        }
        Request newRequest = builder.build();
        return chain.proceed(newRequest);
    }

    /**
     * 构造器
     */
    public static class Builder {
        HttpCommonInterceptor httpCommonInterceptor;

        public Builder() {
            httpCommonInterceptor = new HttpCommonInterceptor();
        }

        public Builder addHeaderParams(String key, String value) {
            httpCommonInterceptor.headerMap.put(key, value);
            return this;
        }

        public Builder addHeaderParams(String key, int value) {
            return addHeaderParams(key, String.valueOf(value));
        }

        public Builder addHeaderParams(String key, float value) {
            return addHeaderParams(key, String.valueOf(value));
        }

        public Builder addHeaderParams(String key, long value) {
            return addHeaderParams(key, String.valueOf(value));
        }

        public Builder addHeaderParams(String key, double value) {
            return addHeaderParams(key, String.valueOf(value));
        }

        public HttpCommonInterceptor build() {
            return httpCommonInterceptor;
        }
    }
}
