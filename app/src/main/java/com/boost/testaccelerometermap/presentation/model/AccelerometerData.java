package com.boost.testaccelerometermap.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

/**
 * Created by yaroslav on 24.05.17.
 */

public class AccelerometerData extends RealmObject implements Parcelable {
    private float x;
    private float y;
    private float z;
    private long timestamp;

    protected AccelerometerData(Parcel in) {
        x = in.readFloat();
        y = in.readFloat();
        z = in.readFloat();
        timestamp = in.readLong();
    }

    public AccelerometerData() {
    }

    public static final Creator<AccelerometerData> CREATOR = new Creator<AccelerometerData>() {
        @Override
        public AccelerometerData createFromParcel(Parcel in) {
            return new AccelerometerData(in);
        }

        @Override
        public AccelerometerData[] newArray(int size) {
            return new AccelerometerData[size];
        }
    };

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "AccelerometerData{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", time=" + timestamp +

                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(x);
        dest.writeFloat(y);
        dest.writeFloat(z);
        dest.writeLong(timestamp);
    }

    public String getTitle(){
        return "x: " + x + "y: " + y + "z: " + z;
    }
}
