package com.boost.testaccelerometermap.presentation.presenter.location;

import android.util.Log;

import com.boost.testaccelerometermap.Constants;
import com.boost.testaccelerometermap.data.model.response.SuccessResponse;
import com.boost.testaccelerometermap.domain.interactors.Interactor;
import com.boost.testaccelerometermap.presentation.model.LatLangDate;
import com.boost.testaccelerometermap.presentation.model.LocationModel;
import com.boost.testaccelerometermap.presentation.view.BaseView;
import com.boost.testaccelerometermap.presentation.view.map.GoogleMapView;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yaroslav on 23.05.17.
 */

public class MapPresenterImpl implements MapPresenter {
    private static final String TAG = "MapPresenterImpl";

    private Interactor<List<LatLng>, List<LocationModel>> mParseLocationInteractor;
    private Interactor<List<LocationModel>, Long> mFreshLocationsInteractor;
    private GoogleMapView mMapView;
    private CompositeDisposable mDisposables;

    @Inject
    public MapPresenterImpl(Interactor<List<LatLng>, List<LocationModel>> parseLocationInteractor,
                            @Named(Constants.QUALIFIER_FRESH)
                                    Interactor<List<LocationModel>, Long> freshLocationsInteractor) {
        mParseLocationInteractor = parseLocationInteractor;
        mFreshLocationsInteractor = freshLocationsInteractor;
    }

    @Override
    public void createLocationRequest() {
        Disposable disposable = mFreshLocationsInteractor.execute(System.currentTimeMillis())
                .subscribeWith(new DisposableObserver<List<LocationModel>>() {

                    @Override
                    public void onNext(@NonNull List<LocationModel> locationModels) {
                        Log.d(TAG, "onNext: " + Thread.currentThread().getName());
                        Log.d(TAG, "createLocationRequest: result " + locationModels.size());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "createLocationRequest: ");
                    }
                });
        mDisposables.add(disposable);
    }

    @Override
    public void onAttachView(BaseView view) {
        mDisposables = new CompositeDisposable();
        mMapView = (GoogleMapView) view;
    }

    public void parseLocationsModel(List<LocationModel> locationModels) {
        Disposable disposable = mParseLocationInteractor.execute(locationModels)
                .subscribe(mMapView::onLocationParsed);
        mDisposables.add(disposable);
    }

    @Override
    public void onDetachView() {
        dispose();
        mMapView = null;
    }

    private void dispose() {
        if (!mDisposables.isDisposed()) {
            mDisposables.dispose();
        }
    }
}

