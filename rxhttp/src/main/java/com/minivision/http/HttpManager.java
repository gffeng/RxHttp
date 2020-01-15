package com.minivision.http;

import android.text.TextUtils;
import com.minivision.http.interceptor.HttpCommonInterceptor;
import com.minivision.http.interceptor.SSLSocketClient;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * http管理这
 *
 * @author gaofeng
 * @date 2019/10/22
 */
public class HttpManager {

    public static String baseUrl;
    public static long timeOut = 15;
    public static HttpCommonInterceptor httpCommonInterceptor;
    private volatile Retrofit retrofit;
    private static HttpManager instance;

    private static OkHttpClient okHttpClient;

    static {
        initOkHttpClient();
    }

    private HttpManager() {
        if (TextUtils.isEmpty(baseUrl)) {
            new Throwable("baseUrl is Empty").printStackTrace();
        }

        /*
         * 创建refrofit实力每增加Gson转换
         *  Rxjava适配器
         */
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .build();
    }

    /**
     * 初始话OkHttpClient
     */
    private static void initOkHttpClient() {
        /*
         * 配置okhttpclient参数
         * 增加拦截器
         * 读取超时时间
         */
        if (okHttpClient == null) {
            synchronized (OkHttpClient.class) {
                if (okHttpClient == null) {
                    okHttpClient = new OkHttpClient.Builder()
                            .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())
                            .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
                            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                            .readTimeout(timeOut, TimeUnit.SECONDS)
                            .connectTimeout(timeOut, TimeUnit.SECONDS)
                            .writeTimeout(timeOut, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
    }

    public static synchronized HttpManager getInstance() {
        if (instance == null) {
            synchronized (HttpManager.class) {
                if (instance == null) {
                    instance = new HttpManager();
                }
            }
        }
        return instance;
    }

    public <T> T service(final Class<T> service) {
        return retrofit.create(service);
    }
}
