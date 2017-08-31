package com.boost.testaccelerometermap.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yaroslav on 25.06.17.
 */

public class TimestampInRange implements Parcelable{
    private long fromTimestamp;
    private long toTimestamp;

    public TimestampInRange(long fromTimestamp, long toTimestamp) {
        this.fromTimestamp = fromTimestamp;
        this.toTimestamp = toTimestamp;
    }

    @Override
    public String toString() {
        return "TimestampInRange{" +
                "fromTimestamp=" + fromTimestamp +
                ", toTimestamp=" + toTimestamp +
                '}';
    }

    protected TimestampInRange(Parcel in) {
        fromTimestamp = in.readLong();
        toTimestamp = in.readLong();
    }

    public static final Creator<TimestampInRange> CREATOR = new Creator<TimestampInRange>() {
        @Override
        public TimestampInRange createFromParcel(Parcel in) {
            return new TimestampInRange(in);
        }

        @Override
        public TimestampInRange[] newArray(int size) {
            return new TimestampInRange[size];
        }
    };

    public long getFromTimestamp() {
        return fromTimestamp;
    }

    public void setFromTimestamp(long fromTimestamp) {
        this.fromTimestamp = fromTimestamp;
    }

    public long getToTimestamp() {
        return toTimestamp;
    }

    public void setToTimestamp(long toTimestamp) {
        this.toTimestamp = toTimestamp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(fromTimestamp);
        dest.writeLong(toTimestamp);
    }
}
