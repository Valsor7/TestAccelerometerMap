package com.boost.testaccelerometermap.dagger.components;

import com.boost.testaccelerometermap.dagger.modules.AccelerometerModule;
import com.boost.testaccelerometermap.dagger.modules.InteractorsModule;
import com.boost.testaccelerometermap.dagger.modules.MapModule;
import com.boost.testaccelerometermap.dagger.scopes.LocationScope;

import dagger.Component;
import dagger.Subcomponent;

/**
 * Created by yaroslav on 24.05.17.
 */
@LocationScope
@Subcomponent(modules = MapModule.class)
public interface MapComponent {
    DomainComponent plusDomain(InteractorsModule interactorsModule);
}
