package com.boost.testaccelerometermap.presentation.presenter.location;

import com.boost.testaccelerometermap.domain.interactors.Interactor;
import com.boost.testaccelerometermap.presentation.model.LocationModel;
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

    private Interactor<Object, LocationModel> mSaveLocationInteractor;

    private Interactor<List<LatLng>, List<LocationModel>> mParseLocationInteractor;
    private Interactor<android.location.Location, Void> mLocationUpdatesInteractor;
    private GoogleMapView mMapView;
    private CompositeDisposable mDisposables = new CompositeDisposable();

    @Inject
    public MapPresenterImpl(Interactor<Object, LocationModel> interactor,
                            Interactor<List<LatLng>, List<LocationModel>> parseLocationInteractor,
                            Interactor<android.location.Location, Void> LocationUpdatesInteractor) {
        mParseLocationInteractor = parseLocationInteractor;
        mLocationUpdatesInteractor = LocationUpdatesInteractor;
        mSaveLocationInteractor = interactor;
    }

    @Override
    public void createLocationRequest() {
        Disposable disposable = mLocationUpdatesInteractor.execute(null)
                .subscribe(mMapView::onLocationTriggered, mMapView::onError);
        mDisposables.add(disposable);
    }

    @Override
    public void saveLocation(LocationModel location) {
        Disposable disposable = mSaveLocationInteractor.execute(location)
                .subscribe(empty -> mMapView.successfullySaved(), mMapView::onError);
        mDisposables.add(disposable);
    }

    public void parseLocationsModel(List<LocationModel> locationModels) {
        Disposable disposable = mParseLocationInteractor.execute(locationModels)
                .subscribe(mMapView::onLocationParsed, mMapView::onError);
        mDisposables.add(disposable);
    }

    @Override
    public void onAttachView(GoogleMapView view) {
        mMapView = view;
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

