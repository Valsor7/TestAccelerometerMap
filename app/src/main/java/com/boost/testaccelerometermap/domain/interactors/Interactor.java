package com.boost.testaccelerometermap.domain.interactors;


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
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        addDisposable(observable.subscribeWith(observer));
    }

    public void dispose() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    public void addDisposable(Disposable disposable) {
        disposables.add(disposable);
    }
}