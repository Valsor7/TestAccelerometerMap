package com.boost.testaccelerometermap.domain.locationmappers;


import com.boost.testaccelerometermap.domain.Mapper;
import com.boost.testaccelerometermap.presentation.model.LatLangDate;
import com.boost.testaccelerometermap.presentation.model.LocationModel;
import com.boost.testaccelerometermap.presentation.utils.TimeUtils;

public class LatLangDateToLocationModel implements Mapper<LatLangDate, LocationModel> {
    @Override
    public LocationModel map(LatLangDate latLangDate) {
        LocationModel model = new LocationModel();
        model.setLatitude(latLangDate.getLatLng().latitude);
        model.setLongitude(latLangDate.getLatLng().longitude);
        model.setTimestamp(latLangDate.getTimeStamp());
        model.setDayInMillis(TimeUtils.getResetedDayInMillis());
        return model;
    }
}
