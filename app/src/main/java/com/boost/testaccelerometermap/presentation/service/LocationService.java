package com.boost.testaccelerometermap.presentation.service;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.boost.testaccelerometermap.MyApplication;
import com.boost.testaccelerometermap.dagger.service.ServiceModule;
import com.boost.testaccelerometermap.domain.Repository;
import com.boost.testaccelerometermap.domain.interactors.location.UpdateLocationsInteractor;
import com.boost.testaccelerometermap.presentation.model.LocationModel;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class LocationService extends Service {
    private static final String TAG = "LocationService";

    @Inject
    UpdateLocationsInteractor mUpdatesInteractor;

    CompositeDisposable mDisposibles;

    @Override
    public void onCreate() {
        super.onCreate();
        initDI();
    }

    private void initDI() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: service started");
        subscribeToLocationsProvider();
        return super.onStartCommand(intent, flags, startId);
    }

    private void subscribeToLocationsProvider() {
        Disposable disposable = mUpdatesInteractor.execute(null).subscribe(response -> Log.d(TAG, "subscribeToLocationsProvider: "));
        mDisposibles.add(disposable);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDisposibles.dispose();
    }
}
