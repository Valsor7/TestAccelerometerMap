package com.boost.testaccelerometermap.dagger.service;

import com.boost.testaccelerometermap.dagger.scopes.ServiceScope;
import com.boost.testaccelerometermap.presentation.service.AccelerometerService;

import dagger.Subcomponent;

/**
 * Created by yaroslav on 21.06.17.
 */
@ServiceScope
@Subcomponent(modules = ServiceModule.class)
public interface ServiceComponent {
    void inject(AccelerometerService service);
}
