package com.everday.useapp.network;

import com.everday.useapp.utils.NetWorkUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
/**
 * 观察者
 */
public class CustomObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {
        boolean available = NetWorkUtils.isAvailable();
    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
