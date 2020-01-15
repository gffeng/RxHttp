package com.minivision.http.transformer;

import com.minivision.http.exception.ExceptionEngine;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * http错误信息转ApiException
 *
 * @author gaofeng
 * @date 2018/6/1
 */
public class HttpResultFunction<T> implements Function<Throwable, Observable<T>> {
    @Override
    public Observable<T> apply(@NonNull Throwable throwable) throws Exception {
        //打印具体错误
        return Observable.error(ExceptionEngine.handleException(throwable));
    }
}