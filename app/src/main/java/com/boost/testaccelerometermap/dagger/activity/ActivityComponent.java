package com.boost.testaccelerometermap.dagger.activity;

import com.boost.testaccelerometermap.dagger.interactors.DomainComponent;
import com.boost.testaccelerometermap.dagger.interactors.InteractorsModule;
import com.boost.testaccelerometermap.dagger.scopes.PerActivityScope;
import com.boost.testaccelerometermap.presentation.view.HomeActivity;

import dagger.Subcomponent;

/**
 * Created by yaroslav on 24.05.17.
 */
@PerActivityScope
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {
    public void inject(HomeActivity activity);
    DomainComponent plusDomain(InteractorsModule interactorsModule);
}
