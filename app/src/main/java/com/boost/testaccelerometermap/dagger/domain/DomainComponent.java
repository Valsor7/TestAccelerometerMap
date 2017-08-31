package com.boost.testaccelerometermap.dagger.domain;

import com.boost.testaccelerometermap.presentation.view.map.MapFragment;
import com.boost.testaccelerometermap.presentation.view.statistics.DataStatisticFragment;

import dagger.Subcomponent;

/**
 * Created by yaroslav on 02.07.17.
 */
@DomainScope
@Subcomponent(modules = DomainModule.class)
public interface DomainComponent {
    public void inject(MapFragment mapFragment);
    public void inject(DataStatisticFragment statisticFragment);
}
