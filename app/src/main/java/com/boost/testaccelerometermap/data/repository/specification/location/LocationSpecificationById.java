package com.boost.testaccelerometermap.data.repository.specification.location;


import com.boost.testaccelerometermap.data.repository.specification.RealmSpecification;
import com.boost.testaccelerometermap.presentation.model.LocationModel;

import io.realm.Realm;
import io.realm.RealmResults;

public class LocationSpecificationById implements RealmSpecification<RealmResults<LocationModel>> {

    private long mId;

    public LocationSpecificationById(long id) {
        mId = id;
    }

    @Override
    public RealmResults<LocationModel> query() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(LocationModel.class).equalTo(LocationModel.DAY_FIELD, mId).findAllAsync();
    }
}
