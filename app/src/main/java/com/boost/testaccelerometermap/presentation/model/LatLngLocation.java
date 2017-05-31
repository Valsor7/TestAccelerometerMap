package com.boost.testaccelerometermap.presentation.model;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import io.realm.RealmObject;

/**
 * Created by yaroslav on 30.05.17.
 */

public class LatLngLocation extends RealmObject {
    private double latitude;
    private double longitude;
    private long timestamp;

    public LatLngLocation() {
    }

    public LatLngLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = System.currentTimeMillis();
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public static LatLngLocation convertToLatLng(Location location){
        return new LatLngLocation(location.getLatitude(), location.getLongitude());
    }
}
