package com.boost.testaccelerometermap.presentation.presenter;

import android.location.Location;

import com.boost.testaccelerometermap.presentation.model.LatLngLocation;
import com.google.android.gms.location.LocationRequest;

import java.util.List;

/**
 * Created by yaroslav on 23.05.17.
 */

public interface MapPresenter extends BasePresenter{

    void getAllAccelerometerData();

    void getAllLocationData();

    void createLocationRequest();

    void saveLocations(List<LatLngLocation> locations);
}
