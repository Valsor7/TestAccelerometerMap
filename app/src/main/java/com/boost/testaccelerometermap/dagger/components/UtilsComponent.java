package com.boost.testaccelerometermap.dagger.components;

import com.boost.testaccelerometermap.MyApplication;
import com.boost.testaccelerometermap.dagger.modules.AppModule;
import com.boost.testaccelerometermap.dagger.modules.UtilsModule;
import com.boost.testaccelerometermap.data.Network;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by yaroslav on 23.05.17.
 */
@Singleton
@Component(modules = {AppModule.class, UtilsModule.class})
public interface UtilsComponent {
    MyApplication application();
    Network networkChecker();
}
