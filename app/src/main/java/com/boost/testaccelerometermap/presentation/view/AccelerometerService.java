package com.boost.testaccelerometermap.presentation.view;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener2;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.boost.testaccelerometermap.MyApplication;
import com.boost.testaccelerometermap.dagger.components.DaggerServiceComponent;
import com.boost.testaccelerometermap.data.repository.AccelerometerRepository;
import com.boost.testaccelerometermap.presentation.model.AccelerometerData;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by yaroslav on 24.05.17.
 */

public class AccelerometerService extends Service implements SensorEventListener2 {
    private static final String TAG = "AccelerometerService";
    private static final int THRESHOLD_SIZE = 20;
    private SensorManager mSensorManager;
    private List<AccelerometerData> mAccelerometerDataList = new ArrayList<>();
    @Inject
    AccelerometerRepository<AccelerometerData> mRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        initDI();
        initAccelerometer();
    }

    private void initDI() {
        DaggerServiceComponent.builder()
                .utilsComponent(MyApplication.getApp().getAppComponent())
                .build()
                .inject(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: service started");
        return super.onStartCommand(intent, flags, startId);
    }

    private void initAccelerometer() {
        Log.d(TAG, "initAccelerometer: ");
        mSensorManager = (SensorManager) getApplicationContext().getSystemService(Context.SENSOR_SERVICE);
        Sensor accelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if (accelerometerSensor != null) {
            mSensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onFlushCompleted(Sensor sensor) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        AccelerometerData data = new AccelerometerData();
        data.setX(event.values[0]);
        data.setY(event.values[1]);
        data.setZ(event.values[2]);
        data.setTimestamp(System.currentTimeMillis());
        mAccelerometerDataList.add(data);
        if (mAccelerometerDataList.size() > THRESHOLD_SIZE) {
            mRepository.addAll(new ArrayList<>(mAccelerometerDataList));
            mAccelerometerDataList.clear();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public List<AccelerometerData> getData() {
        return mAccelerometerDataList;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "service stopped");
        if (mSensorManager != null) {
            mSensorManager.unregisterListener(this);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
