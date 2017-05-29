package com.boost.testaccelerometermap.presentation.view.map;

import android.location.Location;

import com.boost.testaccelerometermap.presentation.model.AccelerometerData;
import com.boost.testaccelerometermap.presentation.view.BaseView;
import com.karumi.dexter.listener.PermissionGrantedResponse;

import java.util.List;

/**
 * Created by yaroslav on 23.05.17.
 */

public interface GoogleMapView extends BaseView {
    public void showAll(List<AccelerometerData> markers);

    void onLocationTriggered(Location location);
}
