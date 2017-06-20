package com.boost.testaccelerometermap.dagger.modules;

import android.content.Context;

import com.boost.testaccelerometermap.MyApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yaroslav on 23.05.17.
 */
@Module
public class AppModule {

    private MyApplication mApp;

    public AppModule(MyApplication application) {
        mApp = application;
    }

    @Provides
    @Singleton
    public MyApplication provideApplication(){
        return mApp;
    }


}
