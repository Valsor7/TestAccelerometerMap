package com.boost.testaccelerometermap.presentation.model;


import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LocationGroup extends ExpandableGroup<AccelerometerData> {
    private LocationModel locationModel;

    public LocationGroup(LocationModel locationModel, String title) {
        super(title, new ArrayList<AccelerometerData>());
        this.locationModel = locationModel;
    }

    public static String makeTitle(LocationModel model, String pattern) {
        return String.format(pattern, model.getLatitude(), model.getLongitude());
    }

    public LocationModel getLocationModel() {
        return locationModel;
    }

    public static ArrayList<LocationGroup> parseFromLocationsList(List<LocationModel> locationModels, String pattern) {
        ArrayList<LocationGroup> locationGroups = new ArrayList<>();
        for (LocationModel locationModel : locationModels) {
            locationGroups.add(new LocationGroup(locationModel, makeTitle(locationModel, pattern)));
        }
        return locationGroups;
    }
}
