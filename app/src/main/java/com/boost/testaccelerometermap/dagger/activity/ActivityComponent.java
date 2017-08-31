package com.boost.testaccelerometermap.dagger.activity;

import com.boost.testaccelerometermap.dagger.domain.DomainComponent;
import com.boost.testaccelerometermap.dagger.domain.DomainModule;

import dagger.Subcomponent;

/**
 * Created by yaroslav on 24.05.17.
 */
@PerActivityScope
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {
    DomainComponent plusDomain(DomainModule domainModule);
}
