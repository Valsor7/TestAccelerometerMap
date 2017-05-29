package com.boost.testaccelerometermap.presentation.presenter;

import android.location.Location;
import android.util.Log;

import com.boost.testaccelerometermap.data.repository.Repository;
import com.boost.testaccelerometermap.data.repository.RepositoryCallback;
import com.boost.testaccelerometermap.presentation.model.AccelerometerData;
import com.boost.testaccelerometermap.presentation.view.BaseView;
import com.boost.testaccelerometermap.presentation.view.map.GoogleMapView;
import com.boost.testaccelerometermap.presentation.view.map.LocationHelper;
import com.google.android.gms.location.LocationRequest;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by yaroslav on 23.05.17.
 */

public class MapPresenterImpl implements MapPresenter {
    private static final String TAG = "MapPresenterImpl";
    Repository<AccelerometerData> mRepository;

    private LocationHelper mLocationHelper;
    private GoogleMapView mMapView;

    @Inject
    public MapPresenterImpl(Repository repository, LocationHelper locationHelper) {
        mRepository = repository;
        mLocationHelper = locationHelper;
    }

    @Override
    public void getAllData() {
        mRepository.getAll(new RepositoryCallback<List<AccelerometerData>>() {
            @Override
            public void onResult(List data) {
                Log.d(TAG, "onResult yeah: " + data.size());
                mMapView.showAll(data);
            }

            @Override
            public void onError(Error error) {

            }
        });
    }

    @Override
    public void createLocationRequest() {
        mLocationHelper.requestLocation(new LocationHelper.LocationCallback() {
            @Override
            public void onLocation(Location location) {
                mMapView.onLocationTriggered(location);
            }
        });
    }


    @Override
    public void onAttachView(BaseView view) {
        mMapView = (GoogleMapView) view;
    }

    @Override
    public void onDetachView() {
        mMapView = null;
        mLocationHelper.removeLocationListener();
    }
}
