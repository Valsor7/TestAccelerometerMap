package com.boost.testaccelerometermap.domain;


import com.boost.testaccelerometermap.data.repository.specification.Specification;

import dagger.internal.Preconditions;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public abstract class Interactor<T> {
    private final CompositeDisposable disposables = new CompositeDisposable();

    abstract Observable<T> buildUseCaseObservable(Specification specification);

    public void execute(DisposableObserver<T> observer, Specification specification) {
        final Observable<T> observable = buildUseCaseObservable(specification)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        addDisposable(observable.subscribeWith(observer));
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