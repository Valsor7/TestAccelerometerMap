package com.boost.testaccelerometermap;

import android.app.Application;
import android.content.Intent;

import com.boost.testaccelerometermap.dagger.AppModule;
import com.boost.testaccelerometermap.dagger.DaggerUtilsComponent;
import com.boost.testaccelerometermap.dagger.UtilsComponent;
import com.boost.testaccelerometermap.dagger.UtilsModule;
import com.boost.testaccelerometermap.presentation.view.AccelerometerService;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by yaroslav on 23.05.17.
 */

public class MyApplication extends Application {
    private static MyApplication mApp;
    private UtilsComponent mUtilsComponent;
    private boolean mServiceStarted;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        initAppComponent();
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(configuration);
        initAccelerometerService();
    }

    private void initAppComponent(){
        mUtilsComponent = DaggerUtilsComponent.builder()
                .appModule(new AppModule(this))
                .utilsModule(new UtilsModule())
                .build();
    }

    private void initAccelerometerService(){
        startService(new Intent(this, AccelerometerService.class));
    }

    public UtilsComponent getAppComponent() {
        return mUtilsComponent;
    }

    public static MyApplication getApp(){
        return mApp;
    }

    public boolean isServiceStarted() {
        return mServiceStarted;
    }

    public void setServiceStarted(boolean serviceStarted) {
        mServiceStarted = serviceStarted;
    }
}
