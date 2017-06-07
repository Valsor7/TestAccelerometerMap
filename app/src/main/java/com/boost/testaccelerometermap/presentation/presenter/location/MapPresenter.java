package com.boost.testaccelerometermap.presentation.presenter.location;

import com.boost.testaccelerometermap.presentation.model.LocationModel;
import com.boost.testaccelerometermap.presentation.presenter.BasePresenter;

import java.util.List;

/**
 * Created by yaroslav on 23.05.17.
 */

public interface MapPresenter extends BasePresenter {

    void getAllAccelerometerData();

    void getAllLocationData();

    void createLocationRequest();

    void saveLocation(LocationModel location);
}
