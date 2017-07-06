package com.boost.testaccelerometermap.domain.locationmappers;

import com.boost.testaccelerometermap.data.model.Location;
import com.boost.testaccelerometermap.domain.Mapper;
import com.boost.testaccelerometermap.presentation.model.LocationModel;

import javax.inject.Inject;

public class LocationToLocationModelMapper implements Mapper<Location, LocationModel> {

    @Override
    public LocationModel map(Location location) {
        LocationModel locationModel = new LocationModel();
        locationModel.setLatitude(location.getLatitude());
        locationModel.setLongitude(location.getLongitude());
        locationModel.setDayInMillis(location.getDayInMillis());
        locationModel.setTimestamp(location.getTimestamp());
        return locationModel;
    }
}
