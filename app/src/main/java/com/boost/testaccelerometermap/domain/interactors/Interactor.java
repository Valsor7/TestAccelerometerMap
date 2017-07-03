package com.boost.testaccelerometermap.domain.interactors;


import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

public abstract class Interactor<T, RM> {
    private final CompositeDisposable disposables = new CompositeDisposable();

    public abstract Observable<T> execute(RM requestModel);


}