package com.boost.testaccelerometermap.data.repository.specification.location;

import com.boost.testaccelerometermap.data.model.LocationDate;
import com.boost.testaccelerometermap.data.repository.specification.RealmSpecification;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by yaroslav on 11.07.17.
 */

public class LocationSpecificationGetFreshLocation implements RealmSpecification<RealmResults<LocationDate>> {

    private long mTimestamp;

    public LocationSpecificationGetFreshLocation(long timestamp) {
        mTimestamp = timestamp;
    }

    @Override
    public RealmResults<LocationDate> query(Realm realm) {
        return realm.where(LocationDate.class).greaterThan(LocationDate.TIMESTAMP, mTimestamp).findAllAsync();
    }
}
