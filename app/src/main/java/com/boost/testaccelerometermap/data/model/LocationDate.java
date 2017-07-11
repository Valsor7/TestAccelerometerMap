package com.boost.testaccelerometermap.data.model;

import io.realm.RealmObject;

/**
 * Created by yaroslav on 30.05.17.
 */

public class LocationDate extends RealmObject {
    public static final String DAY_FIELD = "dayInMillis";
    public static final String TIMESTAMP = "timestamp";
    private double latitude;
    private double longitude;
    private long timestamp;
    private long dayInMillis;

    public LocationDate() {
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

    public long getDayInMillis() {
        return dayInMillis;
    }

    public void setDayInMillis(long dayInMillis) {
        this.dayInMillis = dayInMillis;
    }
}
