package com.boost.testaccelerometermap.data.repository.specification.location;


import com.boost.testaccelerometermap.data.model.Location;
import com.boost.testaccelerometermap.data.repository.specification.RealmSpecification;

import io.realm.Realm;
import io.realm.RealmResults;

public class LocationSpecificationById implements RealmSpecification<RealmResults<Location>> {

    private long mId;

    public LocationSpecificationById(long id) {
        mId = id;
    }

    @Override
    public RealmResults<Location> query(Realm realm) {
        return realm.where(Location.class).equalTo(Location.DAY_FIELD, mId).findAll();
    }
}
