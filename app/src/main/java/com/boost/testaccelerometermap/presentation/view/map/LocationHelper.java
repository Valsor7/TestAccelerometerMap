package com.boost.testaccelerometermap.presentation.view.map;

import android.Manifest;
import android.app.Activity;
import android.content.IntentSender;
import android.location.Location;
import android.support.annotation.NonNull;
import android.util.Log;

import com.boost.testaccelerometermap.Constants;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import static android.content.ContentValues.TAG;

/**
 * Created by yaroslav on 29.05.17.
 */

public class LocationHelper implements LocationListener, ResultCallback, PermissionListener {

    private static final long UPDATE_LOCATION_DELAY = 1000;
    private static final long MINIMUM_UPDATE_LOCATION_DELAY = 500;
    private GoogleApiClient mGoogleApiClient;
    private Activity mActivity;
    private LocationRequest mLocationRequest;
    private LocationCallback mLocationCallback;

    public LocationHelper(GoogleApiClient googleApiClient, Activity activity) {
        mGoogleApiClient = googleApiClient;

        mActivity = activity;
    }

    public void connect() {
        mGoogleApiClient.connect();
    }


    public void requestLocation(LocationCallback callback) {
        mLocationCallback = callback;
        mLocationRequest = createLocationRequest();

        checkConfiguredSettings();
    }

    private void checkConfiguredSettings() {
        LocationSettingsRequest settingsRequest = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest)
                .build();

        LocationServices.SettingsApi
                .checkLocationSettings(mGoogleApiClient, settingsRequest)
                .setResultCallback(this);
    }

    private LocationRequest createLocationRequest() {
        return new LocationRequest()
                .setInterval(UPDATE_LOCATION_DELAY)
                .setFastestInterval(MINIMUM_UPDATE_LOCATION_DELAY)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    @Override
    public void onResult(@NonNull Result result) {
        Status status = result.getStatus();
        Log.d(TAG, "onResult: status " + status.getStatusCode());
        switch (status.getStatusCode()) {
            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                try {
                    status.startResolutionForResult(mActivity, Constants.REQUEST_CHECK_LOCATION_SETTINGS);
                } catch (IntentSender.SendIntentException e) {
                    e.printStackTrace();
                }
                break;
            case LocationSettingsStatusCodes.SUCCESS:
                Log.d(TAG, "onResult: checked permissions");
                checkPermission(Manifest.permission.ACCESS_FINE_LOCATION);
                break;
            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                Log.d(TAG, "onResult: Change unavailable" + LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE);
                break;
        }
    }

    public void checkPermission(String permission) {
        Dexter.withActivity(mActivity).withPermission(permission)
                .withListener(this)
                .check();
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "onLocationChanged: " + location);
        mLocationCallback.onLocation(location);
    }

    @Override
    public void onPermissionGranted(PermissionGrantedResponse response) {
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onPermissionDenied(PermissionDeniedResponse response) {

    }

    @Override
    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

    }

    public interface LocationCallback{
        public void onLocation(Location location);
    }
}
