package com.boost.testaccelerometermap.presentation.presenter.location;

import com.boost.testaccelerometermap.data.model.Location;
import com.boost.testaccelerometermap.data.model.response.SuccessResponse;
import com.boost.testaccelerometermap.domain.interactors.Interactor;
import com.boost.testaccelerometermap.presentation.model.LocationModel;
import com.boost.testaccelerometermap.presentation.model.LocationToLatLngMapper;
import com.boost.testaccelerometermap.presentation.view.BaseView;
import com.boost.testaccelerometermap.presentation.view.map.GoogleMapView;
import com.boost.testaccelerometermap.presentation.view.map.LocationHelper;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by yaroslav on 23.05.17.
 */

public class MapPresenterImpl implements MapPresenter {
    private static final String TAG = "MapPresenterImpl";

    private Interactor<SuccessResponse, Location> mLocationInteractor;

    private Interactor<List<LatLng>, List<LocationModel>> mParseLocationInteractor;
    private LocationHelper mLocationHelper;
    private GoogleMapView mMapView;
    private CompositeDisposable mDisposables = new CompositeDisposable();

    @Inject
    public MapPresenterImpl(Interactor<SuccessResponse, Location> interactor,
                            Interactor<List<LatLng>, List<LocationModel>> parseLocationInteractor,
                            LocationHelper locationHelper) {
        mParseLocationInteractor = parseLocationInteractor;
        mLocationHelper = locationHelper;
        mLocationInteractor = interactor;
    }

    @Override
    public void createLocationRequest() {
        mLocationHelper.requestLocation(mMapView::onLocationTriggered);
    }

    @Override
    public void saveLocation(Location location) {
        Disposable disposable = mLocationInteractor.execute(location)
                .subscribe(successResponse -> {});
        mDisposables.add(disposable);
    }


    @Override
    public void onAttachView(BaseView view) {
        mMapView = (GoogleMapView) view;
    }

    public void parseLocationsModel(List<LocationModel> locationModels) {
        Disposable disposable = mParseLocationInteractor.execute(locationModels)
                .subscribe(mMapView::onLocationParsed);
        mDisposables.add(disposable);
    }

    @Override
    public void onDetachView() {
        mLocationHelper.removeLocationListener();
        dispose();
        mMapView = null;
    }

    private void dispose() {
        if (!mDisposables.isDisposed()) {
            mDisposables.dispose();
        }
    }
}

