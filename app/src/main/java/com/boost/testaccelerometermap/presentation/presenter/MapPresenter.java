package com.boost.testaccelerometermap.presentation.presenter;

import com.google.android.gms.location.LocationRequest;

/**
 * Created by yaroslav on 23.05.17.
 */

public interface MapPresenter extends BasePresenter{

    void getAllData();

    void createLocationRequest();
}
