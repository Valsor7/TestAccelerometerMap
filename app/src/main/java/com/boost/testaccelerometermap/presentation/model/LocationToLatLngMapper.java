package com.boost.testaccelerometermap.presentation.model;

import com.boost.testaccelerometermap.data.model.Location;
import com.boost.testaccelerometermap.domain.Mapper;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yaroslav on 30.05.17.
 */

public class LocationToLatLngMapper implements Mapper<LocationModel, LatLng>{
    public static LatLng convertToLatLng(android.location.Location location){
        return new LatLng(location.getLatitude(), location.getLongitude());
    }

    public static List<LatLng> convertToLatLngList(List<Location> locationModels) {
        List<LatLng> latLngList = new ArrayList<>();
        if (locationModels == null){
            return latLngList;
        }

        for (Location locationModel : locationModels) {
            latLngList.add(new LatLng(locationModel.getLatitude(), locationModel.getLongitude()));
        }
        return latLngList;
    }

    @Override
    public LatLng map(LocationModel model) {
        return new LatLng(model.getLatitude(), model.getLongitude());
    }
}
