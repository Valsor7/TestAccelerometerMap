package com.boost.testaccelerometermap.presentation.presenter.location;

import com.boost.testaccelerometermap.presentation.model.LocationModel;
import com.boost.testaccelerometermap.presentation.presenter.BasePresenter;

/**
 * Created by yaroslav on 23.05.17.
 */

public interface MapPresenter extends BasePresenter {

    void createLocationRequest();

    void saveLocation(LocationModel location);
}
