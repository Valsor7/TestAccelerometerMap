package com.boost.testaccelerometermap.presentation.model;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by yaroslav on 07.07.17.
 */

public class LatLangDate {
    private LatLng latLng;
    private long timeStamp;

    public LatLangDate(LatLng latLng, long timeStamp) {
        this.latLng = latLng;
        this.timeStamp = timeStamp;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
