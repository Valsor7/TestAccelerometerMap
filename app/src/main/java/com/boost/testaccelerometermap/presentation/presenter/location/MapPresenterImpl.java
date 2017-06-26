package com.boost.testaccelerometermap.presentation.presenter.location;

import com.boost.testaccelerometermap.data.repository.Repository;
import com.boost.testaccelerometermap.presentation.model.LocationModel;
import com.boost.testaccelerometermap.presentation.view.BaseView;
import com.boost.testaccelerometermap.presentation.view.map.GoogleMapView;
import com.boost.testaccelerometermap.presentation.view.map.LocationHelper;

import javax.inject.Inject;

/**
 * Created by yaroslav on 23.05.17.
 */

public class MapPresenterImpl implements MapPresenter {
    private static final String TAG = "MapPresenterImpl";

    Repository<LocationModel> mLocationRepository;

    private LocationHelper mLocationHelper;
    private GoogleMapView mMapView;

    @Inject
    public MapPresenterImpl(Repository<LocationModel> locationRepository,
                            LocationHelper locationHelper) {
        mLocationRepository = locationRepository;
        mLocationHelper = locationHelper;
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
        mLocationRepository.add(location);
    }


    @Override
    public void onAttachView(BaseView view) {
        mMapView = (GoogleMapView) view;
    }

    @Override
    public void onDetachView() {
        mLocationHelper.removeLocationListener();
        mMapView = null;
    }
}
