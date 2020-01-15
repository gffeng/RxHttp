package com.minivision.http.transformer;

import com.minivision.http.bean.BaseHttpResult;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * Rxjava管理线程工具
 *
 * @author gaofeng
 * @date 2019/10/23
 */
public class RxSchedulers {

    /**
     * 处理HTTP异常的管理线程
     * 订阅在IO
     * 处理在Main
     *
     * @param <T> Class
     *
     * @return ObservableTransformer<T, T>
     */
    public static <T> ObservableTransformer<T, T> applyHttpSchedulers() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream
                        .onErrorResumeNext(new HttpResultFunction<T>())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 处理HTTP和Service异常的管理线程
     * 订阅在IO
     * 处理在Main
     * oOnSubscribe(new Consumer<Disposable>()
     * doOnTerminate(new Action()
     *
     * @param <T> Class
     *
     * @return ObservableTransformer<BaseHttpResult<T>, T>
     */
    public static <T> ObservableTransformer<BaseHttpResult<T>, T> applySchedulers() {

        return new ObservableTransformer<BaseHttpResult<T>, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<BaseHttpResult<T>> upstream) {
                return upstream
                        .map(new ServerResultFunction<T>())
                        .onErrorResumeNext(new HttpResultFunction<T>())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
