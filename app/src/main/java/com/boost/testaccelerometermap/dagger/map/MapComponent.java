package com.boost.testaccelerometermap.dagger.map;

import com.boost.testaccelerometermap.dagger.interactors.DomainComponent;
import com.boost.testaccelerometermap.dagger.interactors.InteractorsModule;
import com.boost.testaccelerometermap.dagger.scopes.LocationScope;

import dagger.Subcomponent;

/**
 * Created by yaroslav on 24.05.17.
 */
@LocationScope
@Subcomponent(modules = MapModule.class)
public interface MapComponent {
    DomainComponent plusDomain(InteractorsModule interactorsModule);
}
