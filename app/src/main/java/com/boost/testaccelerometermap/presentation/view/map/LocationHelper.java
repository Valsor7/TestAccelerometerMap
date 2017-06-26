package com.boost.testaccelerometermap.presentation.view.map;

import android.Manifest;
import android.app.Activity;
import android.content.IntentSender;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.boost.testaccelerometermap.Constants;
import com.google.android.gms.common.ConnectionResult;
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

/**
 * Created by yaroslav on 29.05.17.
 */

public class LocationHelper implements LocationListener, ResultCallback, PermissionListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private static final String TAG = "LocationHelper";
    private static final long UPDATE_LOCATION_DELAY = 1000;
    private static final long MINIMUM_UPDATE_LOCATION_DELAY = 100;
    private GoogleApiClient mGoogleApiClient;
    private Activity mActivity;
    private LocationRequest mLocationRequest;
    private LocationCallback mLocationCallback;
    private LocationSettingsRequest mLocationSettings;

    public LocationHelper(Activity activity) {
        mGoogleApiClient = new GoogleApiClient.Builder(activity)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        mActivity = activity;
    }

    public void requestLocation(LocationCallback callback) {
        mLocationCallback = callback;

        if (mGoogleApiClient.isConnected())
            initRequest();
        else
            mGoogleApiClient.connect();
    }

    private void initRequest() {
        initLocationRequestIfNull();
        initLocationSettingsIfNull(mLocationRequest);

        LocationServices.SettingsApi
                .checkLocationSettings(mGoogleApiClient, mLocationSettings)
                .setResultCallback(this);
    }

    private void initLocationRequestIfNull() {
        Log.d(TAG, "initLocationRequestIfNull: ");
        if (mLocationRequest == null) {
            mLocationRequest = new LocationRequest()
                    .setInterval(UPDATE_LOCATION_DELAY)
                    .setFastestInterval(MINIMUM_UPDATE_LOCATION_DELAY)
                    .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        }
    }

    //set parameter just to point that this method need to be called after mLocationRequest is initialized
    private void initLocationSettingsIfNull(LocationRequest request) {
        Log.d(TAG, "initLocationSettingsIfNull: ");
        if (mLocationSettings == null) {
            mLocationSettings = new LocationSettingsRequest.Builder()
                    .addLocationRequest(request)
                    .build();
        }
    }


    @Override
    public void onResult(@NonNull Result result) {
        Status status = result.getStatus();
        Log.d(TAG, "onStatisticsByDay: status " + status.getStatusCode());
        switch (status.getStatusCode()) {
            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                try {
                    status.startResolutionForResult(mActivity, Constants.REQUEST_CHECK_LOCATION_SETTINGS);
                } catch (IntentSender.SendIntentException e) {
                    e.printStackTrace();
                }
                break;
            case LocationSettingsStatusCodes.SUCCESS:
                Log.d(TAG, "onStatisticsByDay: success setting changed");
                checkPermission(Manifest.permission.ACCESS_FINE_LOCATION);
                break;
            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                Log.d(TAG, "onStatisticsByDay: Change unavailable" + LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE);
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
        Log.d(TAG, "onPermissionGranted: ");
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onPermissionDenied(PermissionDeniedResponse response) {
        // TODO: 29.05.17 implement it
    }

    @Override
    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
        token.continuePermissionRequest();
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        initRequest();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult result) {
        if (result.hasResolution()) {
            try {
                // TODO: 29.05.17 handle this in activity
                result.startResolutionForResult(mActivity, Constants.REQUEST_RESOLVE_ERROR);
            } catch (IntentSender.SendIntentException e) {
                Log.e(TAG, "onConnectionFailed: " + e.getMessage());
            }
        } else {
            Log.d(TAG, "onConnectionFailed: " + result.getErrorMessage());
        }
    }

    public void removeLocationListener() {
        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }

    public interface LocationCallback {
        public void onLocation(Location location);
    }
}
