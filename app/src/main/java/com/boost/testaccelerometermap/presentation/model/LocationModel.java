package com.boost.testaccelerometermap.presentation.model;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

import com.boost.testaccelerometermap.presentation.utils.TimeUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by yaroslav on 30.05.17.
 */

public class LocationModel extends RealmObject implements Parcelable {
    public static final String DAY_FIELD = "dayInMillis";
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

    protected LocationModel(Parcel in) {
        latitude = in.readDouble();
        longitude = in.readDouble();
        timestamp = in.readLong();
        dayInMillis = in.readLong();
    }

    public static final Creator<LocationModel> CREATOR = new Creator<LocationModel>() {
        @Override
        public LocationModel createFromParcel(Parcel in) {
            return new LocationModel(in);
        }

        @Override
        public LocationModel[] newArray(int size) {
            return new LocationModel[size];
        }
    };

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

    public static List<LocationModel> parseRealmResultsToList(RealmResults<LocationModel> realmResults){
        return new ArrayList<>(realmResults);
    }

    @Override
    public String toString() {
        return "LocationModel{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", timestamp=" + timestamp +
                ", dayInMillis=" + dayInMillis +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeLong(timestamp);
        dest.writeLong(dayInMillis);
    }
}
