package com.boost.testaccelerometermap.presentation.view;


import android.app.Activity;

import com.boost.testaccelerometermap.MyApplication;
import com.boost.testaccelerometermap.dagger.accelerometer.AccelerometerComponent;
import com.boost.testaccelerometermap.dagger.accelerometer.AccelerometerModule;
import com.boost.testaccelerometermap.dagger.accelerometer.DaggerAccelerometerComponent;
import com.boost.testaccelerometermap.dagger.map.DaggerMapComponent;
import com.boost.testaccelerometermap.dagger.map.MapComponent;
import com.boost.testaccelerometermap.dagger.map.MapModule;

public class DaggerBuildManager {

    public static AccelerometerComponent buildAccelerometerComponent(){
        return DaggerAccelerometerComponent.builder()
                .utilsComponent(MyApplication.getApp().getAppComponent())
                .accelerometerModule(new AccelerometerModule())
                .build();
    }

    public static MapComponent buildMapComponent(Activity activity){
        return DaggerMapComponent.builder()
                .utilsComponent(MyApplication.getApp().getAppComponent())
                .mapModule(new MapModule(activity))
                .build();
    }
}
