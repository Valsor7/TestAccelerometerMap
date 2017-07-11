package com.boost.testaccelerometermap.presentation.presenter.location;

import com.boost.testaccelerometermap.data.model.response.SuccessResponse;
import com.boost.testaccelerometermap.domain.interactors.Interactor;
import com.boost.testaccelerometermap.presentation.model.LatLangDate;
import com.boost.testaccelerometermap.presentation.model.LocationModel;
import com.boost.testaccelerometermap.presentation.view.BaseView;
import com.boost.testaccelerometermap.presentation.view.map.GoogleMapView;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by yaroslav on 23.05.17.
 */

public class MapPresenterImpl implements MapPresenter {
    private static final String TAG = "MapPresenterImpl";

    private Interactor<List<LatLng>, List<LocationModel>> mParseLocationInteractor;
    private Interactor<List<LocationModel>, Long> mLocationByDayInteractor;
    private GoogleMapView mMapView;
    private CompositeDisposable mDisposables;

    @Inject
    public MapPresenterImpl(Interactor<List<LatLng>, List<LocationModel>> parseLocationInteractor,
                            Interactor<List<LocationModel>, Long> locationByDayInteractor) {
        mParseLocationInteractor = parseLocationInteractor;
        mLocationByDayInteractor = locationByDayInteractor;
    }

    @Override
    public void createLocationRequest() {
        Disposable disposable = mLocationByDayInteractor.execute(System.currentTimeMillis())
                .subscribe(mMapView::onLocationTriggered, mMapView::onError);
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

