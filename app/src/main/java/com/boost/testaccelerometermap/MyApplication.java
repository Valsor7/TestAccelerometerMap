package com.boost.testaccelerometermap;

import android.app.Application;

import com.boost.testaccelerometermap.dagger.AppModule;
import com.boost.testaccelerometermap.dagger.DaggerUtilsComponent;
import com.boost.testaccelerometermap.dagger.UtilsComponent;
import com.boost.testaccelerometermap.dagger.UtilsModule;

/**
 * Created by yaroslav on 23.05.17.
 */

public class MyApplication extends Application {
    private UtilsComponent mUtilsComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initAppComponent();
    }

    private void initAppComponent(){
        mUtilsComponent = DaggerUtilsComponent.builder()
                .appModule(new AppModule(this))
                .utilsModule(new UtilsModule())
                .build();
    }

    public UtilsComponent getAppComponent() {
        return mUtilsComponent;
    }
}
