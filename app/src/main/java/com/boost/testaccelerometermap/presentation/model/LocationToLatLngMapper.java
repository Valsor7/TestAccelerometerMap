package com.boost.testaccelerometermap.presentation.model;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by yaroslav on 30.05.17.
 */

public class LocationToLatLngMapper {
    public static LatLng convertToLatLng(Location location){
        return new LatLng(location.getLatitude(), location.getLongitude());
    }

    public static LocationModel convertToLatLngLocation(Location location){
        return new LocationModel(location.getLatitude(), location.getLongitude());
    }
}
