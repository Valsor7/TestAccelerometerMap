package com.boost.testaccelerometermap.presentation.presenter.statistics;

import com.boost.testaccelerometermap.presentation.model.LocationModel;
import com.boost.testaccelerometermap.presentation.presenter.BasePresenter;

/**
 * Created by yaroslav on 07.06.17.
 */

interface StatisticPresenter extends BasePresenter {
    void getAccelerometerDataInRange(long timestampFrom, long timestampTo);

    void getStatistics();

    void getLocations(long dayInMillis);
}
