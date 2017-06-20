package com.boost.testaccelerometermap.presentation.model;

import io.realm.RealmObject;

/**
 * Created by yaroslav on 24.05.17.
 */

public class AccelerometerData extends RealmObject {
    private float x;
    private float y;
    private float z;
    private long timestamp;

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
}
