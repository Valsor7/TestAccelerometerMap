package com.boost.testaccelerometermap.presentation.presenter.location;

import com.boost.testaccelerometermap.data.model.Location;
import com.boost.testaccelerometermap.presentation.model.LocationModel;
import com.boost.testaccelerometermap.presentation.presenter.BasePresenter;
import com.boost.testaccelerometermap.presentation.view.map.GoogleMapView;
import com.google.android.gms.maps.MapView;

/**
 * Created by yaroslav on 23.05.17.
 */

public interface MapPresenter extends BasePresenter<GoogleMapView> {

    void createLocationRequest();

    void saveLocation(LocationModel location);
}
