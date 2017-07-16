package com.boost.testaccelerometermap;

import android.app.Application;
import android.content.Intent;

import com.boost.testaccelerometermap.dagger.app.AppComponent;
import com.boost.testaccelerometermap.dagger.app.DaggerAppComponent;
import com.boost.testaccelerometermap.dagger.app.module.AppModule;
import com.boost.testaccelerometermap.dagger.app.module.UtilsModule;
import com.boost.testaccelerometermap.presentation.service.AccelerometerService;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.rx.RealmObservableFactory;

/**
 * Created by yaroslav on 23.05.17.
 */

public class MyApplication extends Application {
    private static MyApplication mApp;
    private AppComponent mAppComponent;
    private Intent mIntent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        initAppComponent();
        Realm.init(this);
    }

    private void initAppComponent(){
        mAppComponent = DaggerAppComponent.builder()
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

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public static MyApplication getApp(){
        return mApp;
    }
}
