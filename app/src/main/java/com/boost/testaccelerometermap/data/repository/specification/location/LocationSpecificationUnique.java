package com.boost.testaccelerometermap.data.repository.specification.location;


import com.boost.testaccelerometermap.data.model.LocationDate;
import com.boost.testaccelerometermap.data.repository.specification.RealmSpecification;

import io.realm.Realm;
import io.realm.RealmResults;

class LocationSpecificationUnique implements RealmSpecification<RealmResults<LocationDate>> {


    @Override
    public RealmResults<LocationDate> query(Realm realm) {
        return realm.where(LocationDate.class).distinct(LocationDate.DAY_FIELD);
    }
}
