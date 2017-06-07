package com.boost.testaccelerometermap.presentation.model;

import android.location.Location;

import com.boost.testaccelerometermap.presentation.utils.TimeUtils;

import io.realm.RealmObject;

/**
 * Created by yaroslav on 30.05.17.
 */

public class LocationModel extends RealmObject {
    private double latitude;
    private double longitude;
    private long timestamp;
    private long dayInMillis;

    public LocationModel() {
    }

    public LocationModel(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = System.currentTimeMillis();
        this.dayInMillis = TimeUtils.getResetedDayInMillis();
    }

    public LocationModel(Location location) {
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
        this.timestamp = System.currentTimeMillis();
        this.dayInMillis = TimeUtils.getResetedDayInMillis();
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

    public static LocationModel convertToLatLng(Location location){
        return new LocationModel(location.getLatitude(), location.getLongitude());
    }

    public long getDayInMillis() {
        return dayInMillis;
    }

    public void setDayInMillis(long dayInMillis) {
        this.dayInMillis = dayInMillis;
    }
}
