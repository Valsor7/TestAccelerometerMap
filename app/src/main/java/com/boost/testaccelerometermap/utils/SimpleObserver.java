package com.boost.testaccelerometermap.utils;


import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

public class SimpleObserver<T> extends DisposableObserver<T> {

    @Override
    public void onNext(@NonNull T t) {

    }

    @Override
    public void onError(@NonNull Throwable e) {

    }

    @Override
    public void onComplete() {

    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
