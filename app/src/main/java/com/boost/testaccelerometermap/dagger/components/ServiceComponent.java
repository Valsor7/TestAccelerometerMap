package com.boost.testaccelerometermap.dagger.components;

import com.boost.testaccelerometermap.dagger.modules.AccelerometerModule;
import com.boost.testaccelerometermap.dagger.scopes.LocationScope;
import com.boost.testaccelerometermap.presentation.view.AccelerometerService;

import dagger.Component;

/**
 * Created by yaroslav on 21.06.17.
 */
@LocationScope
@Component(dependencies = UtilsComponent.class, modules = AccelerometerModule.class)
public interface ServiceComponent {
    void inject(AccelerometerService service);
}
