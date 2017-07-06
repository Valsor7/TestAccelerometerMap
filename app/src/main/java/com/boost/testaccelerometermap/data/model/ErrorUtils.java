package com.boost.testaccelerometermap.data.model;


import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.location.LocationSettingsStatusCodes;

public class ErrorUtils {
    public static Throwable parseError(Exception e) {
        int statusCode = ((ApiException)e).getStatusCode();
        switch (statusCode) {
            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                return new Throwable();
            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                return new Throwable();
        }
        return new IllegalArgumentException();
    }
}
