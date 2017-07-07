package com.boost.testaccelerometermap.data.hardware;

import android.content.Context;
import android.location.Location;
import android.os.Looper;
import android.util.Log;

import com.boost.testaccelerometermap.data.model.ErrorUtils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by yaroslav on 29.05.17.
 */

public class LocationHelper {
    private static final String TAG = "LocationHelper";
    private static final long UPDATE_LOCATION_DELAY = 1000;
    private static final long MINIMUM_UPDATE_LOCATION_DELAY = 100;
    private SettingsClient mSettingsClient = null;
    private FusedLocationProviderClient mFusedLocationClient = null;
    private LocationRequest mLocationRequest;
    private LocationSettingsRequest mLocationSettingsRequest;

    private ObservableEmitter<Location> mLocationEmitter;

    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            mLocationEmitter.onNext(locationResult.getLastLocation());
        }
    };

    private ObservableOnSubscribe<Location> mLocationObservableOnSubscribe = emitter -> {
        mLocationEmitter = emitter;

        mSettingsClient.checkLocationSettings(mLocationSettingsRequest)
                .addOnSuccessListener(result ->
                        mFusedLocationClient.requestLocationUpdates(
                                mLocationRequest, mLocationCallback, Looper.myLooper()
                        ))
                .addOnFailureListener(exception -> emitter.onError(ErrorUtils.parseLocationError(exception)));

        emitter.setCancellable(() -> {
            Log.d(TAG, "remove callback");
            mFusedLocationClient.removeLocationUpdates(mLocationCallback);
        });
    };


    public LocationHelper(Context context) {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
        mSettingsClient = LocationServices.getSettingsClient(context);
        initLocationRequest();
        initLocationSettings(mLocationRequest);
    }

    private void initLocationRequest() {
        mLocationRequest = new LocationRequest()
                .setInterval(UPDATE_LOCATION_DELAY)
                .setFastestInterval(MINIMUM_UPDATE_LOCATION_DELAY)
                .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
    }

    //set parameter just to point that this method need to be called after mLocationRequest is initialized
    private void initLocationSettings(LocationRequest request) {
        mLocationSettingsRequest = new LocationSettingsRequest.Builder()
                .addLocationRequest(request)
                .build();
    }

    public Observable<Location> subscribeToLocationEmitter() {
        Log.d(TAG, "subscribeToLocationEmitter: ");
        return Observable.create(mLocationObservableOnSubscribe);
    }
}
