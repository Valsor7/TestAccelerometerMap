package com.boost.testaccelerometermap.presentation.model;

import com.boost.testaccelerometermap.data.model.LocationDate;
import com.boost.testaccelerometermap.domain.Mapper;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yaroslav on 30.05.17.
 */

public class LocationToLatLngMapper implements Mapper<LocationModel, LatLng>{

    @Override
    public LatLng map(LocationModel model) {
        return new LatLng(model.getLatitude(), model.getLongitude());
    }
}
