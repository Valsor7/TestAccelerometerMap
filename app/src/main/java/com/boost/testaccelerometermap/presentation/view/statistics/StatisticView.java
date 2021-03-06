package com.boost.testaccelerometermap.presentation.view.statistics;

import com.boost.testaccelerometermap.data.model.AccelerometerData;
import com.boost.testaccelerometermap.presentation.model.LocationModel;
import com.boost.testaccelerometermap.presentation.view.BaseView;

import java.util.List;

/**
 * Created by yaroslav on 07.06.17.
 */

public interface StatisticView extends BaseView{
    void onAccelerometerResult(List<AccelerometerData> data);
    void onStatisticsByDay(List<LocationModel> data);

    void onLocations(List<LocationModel> data);
}
