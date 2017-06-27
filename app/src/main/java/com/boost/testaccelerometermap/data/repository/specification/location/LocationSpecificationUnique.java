package com.boost.testaccelerometermap.data.repository.specification.location;


import com.boost.testaccelerometermap.data.repository.specification.RealmSpecification;
import com.boost.testaccelerometermap.presentation.model.LocationModel;

import io.realm.Realm;

class LocationSpecificationUnique implements RealmSpecification {


    @Override
    public Object query() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(LocationModel.class).distinctAsync(LocationModel.DAY_FIELD);
    }
}
