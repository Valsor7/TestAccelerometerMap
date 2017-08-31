package com.boost.testaccelerometermap.domain.locationmappers;

import android.location.Location;

import com.boost.testaccelerometermap.domain.Mapper;
import com.boost.testaccelerometermap.presentation.model.LatLangDate;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by yaroslav on 07.07.17.
 */

public class LocationToLatLangDateMapper implements Mapper<Location, LatLangDate> {
    @Override
    public LatLangDate map(Location location) {
        return new LatLangDate(
                new LatLng(location.getLatitude(), location.getLongitude()),
                System.currentTimeMillis());
    }
}
