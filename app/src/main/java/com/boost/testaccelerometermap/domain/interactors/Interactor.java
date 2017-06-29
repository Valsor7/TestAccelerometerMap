package com.boost.testaccelerometermap.domain.interactors;


import android.util.Log;

import dagger.internal.Preconditions;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public abstract class Interactor<T, RM> {
    private static final String TAG = "Interactor";
    private final CompositeDisposable disposables = new CompositeDisposable();

    protected abstract Observable<T> buildObservable(RM requestModel);

    public void execute(DisposableObserver<T> observer, RM requestModel) {
        final Observable<T> observable = buildObservable(requestModel)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        addDisposable(observable.subscribeWith(observer));
        Log.d(TAG, "execute: ");
    }

    public void dispose() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    private void addDisposable(Disposable disposable) {
        Preconditions.checkNotNull(disposable);
        Preconditions.checkNotNull(disposables);
        disposables.add(disposable);
    }
}