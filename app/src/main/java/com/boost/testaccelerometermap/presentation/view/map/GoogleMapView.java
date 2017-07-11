package com.boost.testaccelerometermap.presentation.view.map;

import android.location.Location;

import com.boost.testaccelerometermap.data.model.AccelerometerData;
import com.boost.testaccelerometermap.presentation.model.LatLangDate;
import com.boost.testaccelerometermap.presentation.model.LocationModel;
import com.boost.testaccelerometermap.presentation.view.BaseView;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by yaroslav on 23.05.17.
 */

public interface GoogleMapView extends BaseView {
    void onLocationTriggered(List<LocationModel> location);

    public void onLocationParsed(List<LatLng> latLngList);
}
