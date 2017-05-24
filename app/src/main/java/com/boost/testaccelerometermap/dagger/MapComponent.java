package com.boost.testaccelerometermap.dagger;

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
