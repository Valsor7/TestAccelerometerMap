package com.boost.testaccelerometermap.dagger.components;

import com.boost.testaccelerometermap.dagger.modules.ServiceModule;
import com.boost.testaccelerometermap.dagger.scopes.ServiceScope;
import com.boost.testaccelerometermap.presentation.view.AccelerometerService;

import dagger.Subcomponent;

/**
 * Created by yaroslav on 21.06.17.
 */
@ServiceScope
@Subcomponent(modules = ServiceModule.class)
public interface ServiceComponent {
    void inject(AccelerometerService service);
}
