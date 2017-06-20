package com.boost.testaccelerometermap.dagger.statistics;


import com.boost.testaccelerometermap.dagger.accelerometer.AccelerometerComponent;
import com.boost.testaccelerometermap.dagger.map.MapComponent;
import com.boost.testaccelerometermap.presentation.view.statistics.DataStatisticFragment;

import dagger.Component;

@StatisticsScope
@Component()
public interface StatisticsComponent {
    void inject(DataStatisticFragment statisticFragment);
}
