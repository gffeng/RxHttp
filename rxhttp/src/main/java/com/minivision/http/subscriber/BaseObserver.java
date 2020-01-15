package com.minivision.http.subscriber;

import com.minivision.http.exception.ApiException;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 订阅者基类
 *
 * @author gaofeng
 * @date 2019/10/22
 */
public abstract class BaseObserver<T> implements Observer<T> {

    @Override public void onSubscribe(Disposable d) {

    }

    @Override public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        ApiException apiException = (ApiException) e;
        onError(apiException);
    }

    /**
     * 异常回调
     * @param e 错误的一个回调
     */
    protected abstract void onError(ApiException e);
}
