package com.boost.testaccelerometermap.presentation.model;


import com.boost.testaccelerometermap.data.model.Location;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.ArrayList;
import java.util.List;

public class LocationGroup extends ExpandableGroup<ExpandableElement> {
    private Location locationModel;

    public LocationGroup(Location locationModel, String title) {
        super(title, setListWithEmptyTitle());
        this.locationModel = locationModel;
    }

    public static String makeTitle(Location model, String pattern) {
        return String.format(pattern, model.getLatitude(), model.getLongitude());
    }

    private static List<ExpandableElement> setListWithEmptyTitle(){
        List<ExpandableElement> emptyTitleList = new ArrayList<>();
        emptyTitleList.add(new EmptyTitleExpandableElement());
        return emptyTitleList;
    }

    public Location getLocationModel() {
        return locationModel;
    }

    public static ArrayList<LocationGroup> parseFromLocationsList(List<Location> locationModels, String pattern) {
        ArrayList<LocationGroup> locationGroups = new ArrayList<>();
        for (Location locationModel : locationModels) {
            locationGroups.add(new LocationGroup(locationModel, makeTitle(locationModel, pattern)));
        }
        return locationGroups;
    }
}
