package com.boost.testaccelerometermap.data.repository.specification.location;


import com.boost.testaccelerometermap.data.model.Location;
import com.boost.testaccelerometermap.data.repository.specification.RealmSpecification;

import io.realm.Realm;
import io.realm.RealmResults;

class LocationSpecificationUnique implements RealmSpecification<RealmResults<Location>> {


    @Override
    public RealmResults<Location> query(Realm realm) {
        return realm.where(Location.class).distinct(Location.DAY_FIELD);
    }
}
