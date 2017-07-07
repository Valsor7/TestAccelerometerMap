package com.boost.testaccelerometermap.data.repository.specification.location;


import com.boost.testaccelerometermap.data.model.LocationDate;
import com.boost.testaccelerometermap.data.repository.specification.RealmSpecification;

import io.realm.Realm;
import io.realm.RealmResults;

public class LocationSpecificationById implements RealmSpecification<RealmResults<LocationDate>> {

    private long mId;

    public LocationSpecificationById(long id) {
        mId = id;
    }

    @Override
    public RealmResults<LocationDate> query(Realm realm) {
        return realm.where(LocationDate.class).equalTo(LocationDate.DAY_FIELD, mId).findAll();
    }
}
