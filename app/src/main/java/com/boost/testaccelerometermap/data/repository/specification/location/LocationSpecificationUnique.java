package com.boost.testaccelerometermap.data.repository.specification.location;


import com.boost.testaccelerometermap.data.repository.specification.RealmSpecification;
import com.boost.testaccelerometermap.presentation.model.LocationModel;

import io.realm.Realm;
import io.realm.RealmResults;

class LocationSpecificationUnique implements RealmSpecification<RealmResults<LocationModel>> {


    @Override
    public RealmResults<LocationModel> query(Realm realm) {
        RealmResults<LocationModel> realmResults = realm.where(LocationModel.class).distinct(LocationModel.DAY_FIELD);
        return realmResults;
    }
}
