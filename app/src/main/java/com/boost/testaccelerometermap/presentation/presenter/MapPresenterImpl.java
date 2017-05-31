package com.boost.testaccelerometermap.presentation.presenter;

import android.util.Log;

import com.boost.testaccelerometermap.dagger.map.qualifiers.Accelerometer;
import com.boost.testaccelerometermap.dagger.map.qualifiers.Location;
import com.boost.testaccelerometermap.data.repository.Repository;
import com.boost.testaccelerometermap.data.repository.RepositoryCallback;
import com.boost.testaccelerometermap.presentation.model.AccelerometerData;
import com.boost.testaccelerometermap.presentation.model.LatLngLocation;
import com.boost.testaccelerometermap.presentation.view.BaseView;
import com.boost.testaccelerometermap.presentation.view.map.GoogleMapView;
import com.boost.testaccelerometermap.presentation.view.map.LocationHelper;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by yaroslav on 23.05.17.
 */

public class MapPresenterImpl implements MapPresenter {
    private static final String TAG = "MapPresenterImpl";
    Repository<AccelerometerData> mAccelerometerRepository;
    Repository<LatLngLocation> mLocationRepository;

    private LocationHelper mLocationHelper;
    private GoogleMapView mMapView;

    @Inject
    public MapPresenterImpl(@Location Repository<LatLngLocation> locationRepository,
                            @Accelerometer Repository<AccelerometerData> accelerometerRepository,
                            LocationHelper locationHelper) {
        mLocationRepository = locationRepository;
        mAccelerometerRepository = accelerometerRepository;
        mLocationHelper = locationHelper;
    }

    @Override
    public void getAllAccelerometerData() {
        mAccelerometerRepository.getAll(new RepositoryCallback<List<AccelerometerData>>() {
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
    public void getAllLocationData() {
        mAccelerometerRepository.getAll(new RepositoryCallback<List<AccelerometerData>>() {
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
            public void onLocation(android.location.Location location) {
                mMapView.onLocationTriggered(location);
            }
        });
    }

    @Override
    public void saveLocations(List<LatLngLocation> location) {

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
