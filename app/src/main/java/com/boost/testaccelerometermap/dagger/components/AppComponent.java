package com.boost.testaccelerometermap.dagger.components;

import com.boost.testaccelerometermap.MyApplication;
import com.boost.testaccelerometermap.dagger.modules.AccelerometerModule;
import com.boost.testaccelerometermap.dagger.modules.AppModule;
import com.boost.testaccelerometermap.dagger.modules.LocationModule;
import com.boost.testaccelerometermap.dagger.modules.MapModule;
import com.boost.testaccelerometermap.dagger.modules.ServiceModule;
import com.boost.testaccelerometermap.dagger.modules.UtilsModule;
import com.boost.testaccelerometermap.dagger.scopes.LocationScope;
import com.boost.testaccelerometermap.dagger.scopes.ServiceScope;
import com.boost.testaccelerometermap.data.Network;
import com.boost.testaccelerometermap.presentation.model.LocationModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by yaroslav on 23.05.17.
 */
@Singleton
@Component(modules = {AppModule.class, UtilsModule.class, AccelerometerModule.class, LocationModule.class})
public interface AppComponent {
    public ServiceComponent plusServiceComponent(ServiceModule serviceModule);
    public MapComponent plusMapComponent(MapModule mapModule);
}
