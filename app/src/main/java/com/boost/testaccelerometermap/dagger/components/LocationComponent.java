package com.boost.testaccelerometermap.dagger.components;

import com.boost.testaccelerometermap.dagger.modules.AccelerometerModule;
import com.boost.testaccelerometermap.dagger.modules.InteractorsModule;
import com.boost.testaccelerometermap.dagger.modules.MapModule;
import com.boost.testaccelerometermap.dagger.scopes.LocationScope;
import com.boost.testaccelerometermap.presentation.view.AccelerometerService;
import com.boost.testaccelerometermap.presentation.view.map.MapFragment;
import com.boost.testaccelerometermap.presentation.view.statistics.DataStatisticFragment;

import dagger.Component;

/**
 * Created by yaroslav on 24.05.17.
 */
@LocationScope
@Component(dependencies = UtilsComponent.class, modules = {MapModule.class, AccelerometerModule.class})
public interface LocationComponent {
   public DomainComponent plusDomain(InteractorsModule interactorsModule);
}
