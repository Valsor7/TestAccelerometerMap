package com.boost.testaccelerometermap.utils;


import android.support.annotation.NonNull;

import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public final class RxUtils {

    private RxUtils() {
    }

    @NonNull
    public static <T> ObservableTransformer<T, T> async() {
        return upstream -> upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @NonNull
    public static <T> ObservableTransformer<T, T> async(@NonNull final Scheduler background,
                                                        @NonNull final Scheduler main) {
        return upstream -> upstream
                .subscribeOn(background)
                .observeOn(main);
    }

}
