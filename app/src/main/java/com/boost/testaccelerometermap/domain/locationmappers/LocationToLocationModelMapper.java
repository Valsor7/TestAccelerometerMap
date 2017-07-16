package com.boost.testaccelerometermap.domain.locationmappers;

import android.location.Location;

import com.boost.testaccelerometermap.domain.Mapper;
import com.boost.testaccelerometermap.presentation.model.LocationModel;

import java.util.List;

/**
 * Created by yaroslav on 16.07.17.
 */

public class LocationToLocationModelMapper implements Mapper<Location, LocationModel> {
    @Override
    public LocationModel map(Location location) {
        return new LocationModel(location.getLatitude(), location.getLongitude());
    }
}
