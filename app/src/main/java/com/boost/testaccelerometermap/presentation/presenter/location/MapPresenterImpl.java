package com.boost.testaccelerometermap.presentation.presenter.location;

import com.boost.testaccelerometermap.data.model.response.SuccessResponse;
import com.boost.testaccelerometermap.domain.interactors.Interactor;
import com.boost.testaccelerometermap.presentation.model.LocationModel;
import com.boost.testaccelerometermap.presentation.view.BaseView;
import com.boost.testaccelerometermap.presentation.view.map.GoogleMapView;
import com.boost.testaccelerometermap.presentation.view.map.LocationHelper;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by yaroslav on 23.05.17.
 */

public class MapPresenterImpl implements MapPresenter {
    private static final String TAG = "MapPresenterImpl";

    private Interactor<SuccessResponse, LocationModel> mLocationInteractor;

    private LocationHelper mLocationHelper;
    private GoogleMapView mMapView;

    @Inject
    public MapPresenterImpl(Interactor<SuccessResponse, LocationModel> interactor,
                            LocationHelper locationHelper) {
        mLocationHelper = locationHelper;
        mLocationInteractor = interactor;
    }

    @Override
    public void createLocationRequest() {
        mLocationHelper.requestLocation(mMapView::onLocationTriggered);
    }

    @Override
    public void saveLocation(LocationModel location) {
        mLocationInteractor.execute(new DisposableObserver<SuccessResponse>() {
            @Override
            public void onNext(@NonNull SuccessResponse successResponse) {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        }, location);
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
}
