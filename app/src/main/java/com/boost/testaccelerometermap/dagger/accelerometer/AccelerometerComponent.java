package com.boost.testaccelerometermap.dagger.accelerometer;

import android.app.Service;

import com.boost.testaccelerometermap.dagger.UtilsComponent;
import com.boost.testaccelerometermap.presentation.view.AccelerometerService;

import dagger.Component;
import dagger.Module;

/**
 * Created by yaroslav on 25.05.17.
 */

@AccelerometerScope
@Component(dependencies = UtilsComponent.class, modules = AccelerometerModule.class)
public interface AccelerometerComponent {
    void inject(AccelerometerService service);
}
