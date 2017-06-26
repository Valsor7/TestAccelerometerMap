package com.boost.testaccelerometermap;

import android.app.Application;
import android.content.Intent;


import com.boost.testaccelerometermap.dagger.components.DaggerUtilsComponent;
import com.boost.testaccelerometermap.dagger.components.UtilsComponent;
import com.boost.testaccelerometermap.dagger.modules.AppModule;
import com.boost.testaccelerometermap.dagger.modules.UtilsModule;
import com.boost.testaccelerometermap.presentation.view.AccelerometerService;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by yaroslav on 23.05.17.
 */

public class MyApplication extends Application {
    private static MyApplication mApp;
    private UtilsComponent mUtilsComponent;
    private Intent mIntent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        initAppComponent();
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(configuration);
    }

    private void initAppComponent(){
        mUtilsComponent = DaggerUtilsComponent.builder()
                .appModule(new AppModule(this))
                .utilsModule(new UtilsModule())
                .build();
    }

    public void startService(){
        if (mIntent == null) {
            mIntent = new Intent(this, AccelerometerService.class);
            startService(mIntent);
        }
    }

    public void stopService(){
        if (mIntent != null){
            stopService(mIntent);
            mIntent = null;
        }
    }

    public UtilsComponent getAppComponent() {
        return mUtilsComponent;
    }

    public static MyApplication getApp(){
        return mApp;
    }
}
