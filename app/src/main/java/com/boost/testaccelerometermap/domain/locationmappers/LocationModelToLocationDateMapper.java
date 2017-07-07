package com.boost.testaccelerometermap.domain.locationmappers;


import com.boost.testaccelerometermap.data.model.LocationDate;
import com.boost.testaccelerometermap.domain.Mapper;
import com.boost.testaccelerometermap.presentation.model.LocationModel;

public class LocationModelToLocationDateMapper implements Mapper<LocationModel, LocationDate> {

    @Override
    public LocationDate map(LocationModel model) {
        LocationDate locationDate = new LocationDate();
        locationDate.setLatitude(model.getLatitude());
        locationDate.setLongitude(model.getLongitude());
        locationDate.setDayInMillis(model.getDayInMillis());
        locationDate.setTimestamp(model.getTimestamp());
        return locationDate;
    }
}
