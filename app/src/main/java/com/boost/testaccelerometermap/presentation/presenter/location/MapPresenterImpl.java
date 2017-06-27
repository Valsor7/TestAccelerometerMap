package com.boost.testaccelerometermap.presentation.presenter.location;

import com.boost.testaccelerometermap.domain.Interactor;
import com.boost.testaccelerometermap.presentation.model.LocationModel;
import com.boost.testaccelerometermap.presentation.view.BaseView;
import com.boost.testaccelerometermap.presentation.view.map.GoogleMapView;
import com.boost.testaccelerometermap.presentation.view.map.LocationHelper;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by yaroslav on 23.05.17.
 */

public class MapPresenterImpl extends DisposableObserver<LocationModel>
        implements MapPresenter {
    private static final String TAG = "MapPresenterImpl";

    private Interactor<LocationModel, LocationModel> mLocationInteractor;

    private LocationHelper mLocationHelper;
    private GoogleMapView mMapView;

    @Inject
    public MapPresenterImpl(Interactor<LocationModel, LocationModel> interactor,
                            LocationHelper locationHelper) {
        mLocationHelper = locationHelper;
        mLocationInteractor = interactor;
    }

    @Override
    public void createLocationRequest() {
        mLocationHelper.requestLocation(new LocationHelper.LocationCallback() {
            @Override
            public void onLocation(android.location.Location location) {
                if (mMapView == null) return;
                mMapView.onLocationTriggered(location);
            }
        });
    }

    @Override
    public void saveLocation(LocationModel location) {
        mLocationInteractor.execute(this, location);
    }


    @Override
    public void onAttachView(BaseView view) {
        mMapView = (GoogleMapView) view;
    }

    @Override
    public void onDetachView() {
        mLocationHelper.removeLocationListener();
        mLocationInteractor.dispose();
        mMapView = null;
    }

    @Override
    public void onNext(@NonNull LocationModel model) {

    }

    @Override
    public void onError(@NonNull Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
