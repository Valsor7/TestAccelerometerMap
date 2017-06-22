package com.boost.testaccelerometermap.presentation.model;


import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.ArrayList;
import java.util.List;

public class LocationsGroup extends ExpandableGroup<AccelerometerData> {
    private LocationModel locationModel;

    public LocationsGroup(List<LocationModel> models) {
        super(title, new ArrayList<AccelerometerData>());
    }
}
