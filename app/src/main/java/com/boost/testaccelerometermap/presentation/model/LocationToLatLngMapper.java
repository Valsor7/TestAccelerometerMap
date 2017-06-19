package com.boost.testaccelerometermap.presentation.model;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public static List<LatLng> convertToLatLngList(List<LocationModel> locationModels) {
        List<LatLng> latLngList = new ArrayList<>();
        if (locationModels == null){
            return latLngList;
        }

        for (LocationModel locationModel : locationModels) {
            latLngList.add(new LatLng(locationModel.getLatitude(), locationModel.getLongitude()));
        }
        return latLngList;
    }
}
