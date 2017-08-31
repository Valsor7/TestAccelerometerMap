package com.boost.testaccelerometermap.data.model;


import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.location.LocationSettingsStatusCodes;

public class ErrorUtils {

    public static Exception parseLocationError(Exception e) {
        int statusCode = ((ApiException) e).getStatusCode();
        switch (statusCode) {
            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                return new ResolutionException();
            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                return new SettingsChangeException();
        }
        return new IllegalArgumentException();
    }

    private static class ResolutionException extends Exception {
    }

    private static class SettingsChangeException extends Exception {
    }
}

//Log.i(TAG, "LocationDate settings are not satisfied. Attempting to upgrade " +
//        "location settings ");///////////////
//        ResolvableApiException rae = (ResolvableApiException) e;
////                            rae.startResolutionForResult(MainActivity.this, REQUEST_CHECK_SETTINGS);

//         String errorMessage = "LocationDate settings are inadequate, and cannot be " +
//                 "fixed here. Fix in Settings.";
//                 Log.e(TAG, errorMessage);