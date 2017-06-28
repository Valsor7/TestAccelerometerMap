package com.boost.testaccelerometermap.presentation.presenter.statistics;

import com.boost.testaccelerometermap.presentation.model.LocationModel;
import com.boost.testaccelerometermap.presentation.model.TimestampInRange;
import com.boost.testaccelerometermap.presentation.presenter.BasePresenter;

import org.reactivestreams.Subscriber;

/**
 * Created by yaroslav on 07.06.17.
 */

interface StatisticPresenter extends BasePresenter {
    void getStatistics();

    void getLocations(long dayInMillis);

    void getAccelerometerDataInRange(TimestampInRange timestampInRange);
}
