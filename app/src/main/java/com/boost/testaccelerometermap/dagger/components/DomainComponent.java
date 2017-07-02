package com.boost.testaccelerometermap.dagger.components;

import com.boost.testaccelerometermap.dagger.modules.InteractorsModule;
import com.boost.testaccelerometermap.dagger.scopes.DomainScope;
import com.boost.testaccelerometermap.presentation.view.map.MapFragment;
import com.boost.testaccelerometermap.presentation.view.statistics.DataStatisticFragment;

import dagger.Subcomponent;

/**
 * Created by yaroslav on 02.07.17.
 */
@DomainScope
@Subcomponent(modules = InteractorsModule.class)
public interface DomainComponent {

    public void inject(MapFragment mapFragment);
    public void inject(DataStatisticFragment statisticFragment);
}
