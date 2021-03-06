package com.boost.testaccelerometermap.dagger.app.module;

import com.boost.testaccelerometermap.MyApplication;
import com.boost.testaccelerometermap.data.Network;
import com.boost.testaccelerometermap.data.hardware.LocationHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yaroslav on 23.05.17.
 */
@Module
public class UtilsModule {
    @Singleton
    @Provides
    public Network provideNetworkChecker(MyApplication application){
        return new Network(application);
    }

    @Singleton
    @Provides
    public LocationHelper provideLocationHelper(MyApplication application){
        return new LocationHelper(application);
    }
}
