package com.boost.testaccelerometermap.domain.locationmappers;

import android.util.Log;

import com.boost.testaccelerometermap.data.model.LocationDate;
import com.boost.testaccelerometermap.domain.Mapper;
import com.boost.testaccelerometermap.presentation.model.LocationModel;

public class LocationDateToLocationModelMapper implements Mapper<LocationDate, LocationModel> {
    private static final String TAG = "LocationDateToLocationM";
    @Override
    public LocationModel map(LocationDate locationDate) {

        LocationModel locationModel = new LocationModel();
        locationModel.setLatitude(locationDate.getLatitude());
        locationModel.setLongitude(locationDate.getLongitude());
        locationModel.setDayInMillis(locationDate.getDayInMillis());
        locationModel.setTimestamp(locationDate.getTimestamp());
        return locationModel;
    }
}
