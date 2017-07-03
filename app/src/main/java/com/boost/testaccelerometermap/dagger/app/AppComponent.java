package com.boost.testaccelerometermap.dagger.app;

import com.boost.testaccelerometermap.dagger.app.module.AccelerometerModule;
import com.boost.testaccelerometermap.dagger.app.module.AppModule;
import com.boost.testaccelerometermap.dagger.app.module.LocationModule;
import com.boost.testaccelerometermap.dagger.app.module.UtilsModule;
import com.boost.testaccelerometermap.dagger.map.MapComponent;
import com.boost.testaccelerometermap.dagger.service.ServiceComponent;
import com.boost.testaccelerometermap.dagger.map.MapModule;
import com.boost.testaccelerometermap.dagger.service.ServiceModule;

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
