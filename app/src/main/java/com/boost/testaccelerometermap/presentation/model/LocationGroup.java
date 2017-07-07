package com.boost.testaccelerometermap.presentation.model;


import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.ArrayList;
import java.util.List;

public class LocationGroup extends ExpandableGroup<ExpandableElement> {
    private LocationModel locationModel;

    public LocationGroup(LocationModel locationModel, String title) {
        super(title, setListWithEmptyTitle());
        this.locationModel = locationModel;
    }

    public static String makeTitle(LocationModel model, String pattern) {
        return String.format(pattern, model.getLatitude(), model.getLongitude());
    }

    private static List<ExpandableElement> setListWithEmptyTitle(){
        List<ExpandableElement> emptyTitleList = new ArrayList<>();
        emptyTitleList.add(new EmptyTitleExpandableElement());
        return emptyTitleList;
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
