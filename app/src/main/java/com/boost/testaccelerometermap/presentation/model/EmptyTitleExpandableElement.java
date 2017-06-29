package com.boost.testaccelerometermap.presentation.model;


import android.os.Parcel;

public class EmptyTitleExpandableElement implements ExpandableElement{
    private String title;

    public EmptyTitleExpandableElement() {
    }

    public EmptyTitleExpandableElement(Parcel in) {
        title = in.readString();
    }

    public static final Creator<EmptyTitleExpandableElement> CREATOR = new Creator<EmptyTitleExpandableElement>() {
        @Override
        public EmptyTitleExpandableElement createFromParcel(Parcel in) {
            return new EmptyTitleExpandableElement(in);
        }

        @Override
        public EmptyTitleExpandableElement[] newArray(int size) {
            return new EmptyTitleExpandableElement[size];
        }
    };

    @Override
    public String getTitle(String pattern) {
        return pattern;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
    }
}
