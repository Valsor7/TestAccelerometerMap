package com.boost.testaccelerometermap.dagger.map;

import com.boost.testaccelerometermap.dagger.UtilsComponent;
import com.boost.testaccelerometermap.presentation.view.map.MapFragment;

import dagger.Component;

/**
 * Created by yaroslav on 24.05.17.
 */
@MapScope
@Component(dependencies = UtilsComponent.class, modules = MapModule.class)
public interface MapComponent {
    void inject(MapFragment fragment);
}
