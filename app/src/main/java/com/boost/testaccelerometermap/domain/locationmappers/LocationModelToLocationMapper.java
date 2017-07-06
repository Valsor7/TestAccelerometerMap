package com.boost.testaccelerometermap.domain.locationmappers;


import com.boost.testaccelerometermap.data.model.Location;
import com.boost.testaccelerometermap.domain.Mapper;
import com.boost.testaccelerometermap.presentation.model.LocationModel;

import javax.inject.Inject;

public class LocationModelToLocationMapper implements Mapper<LocationModel, Location> {

    @Override
    public Location map(LocationModel model) {
        Location location = new Location();
        location.setLatitude(model.getLatitude());
        location.setLongitude(model.getLongitude());
        location.setDayInMillis(model.getDayInMillis());
        location.setTimestamp(model.getTimestamp());
        return location;
    }
}
