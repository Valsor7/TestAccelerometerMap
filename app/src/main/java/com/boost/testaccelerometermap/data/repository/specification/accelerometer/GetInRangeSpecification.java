package com.boost.testaccelerometermap.data.repository.specification.accelerometer;


import com.boost.testaccelerometermap.data.repository.specification.RealmSpecification;
import com.boost.testaccelerometermap.data.model.AccelerometerData;
import com.boost.testaccelerometermap.presentation.model.TimestampInRange;

import io.realm.Realm;
import io.realm.RealmResults;

public class GetInRangeSpecification implements RealmSpecification<RealmResults<AccelerometerData>> {
    private static final String FIELD_TIMESTAMP = "timestamp";
    private TimestampInRange mTimestampInRange;

    public GetInRangeSpecification(TimestampInRange timestampInRange) {
        mTimestampInRange = timestampInRange;
    }

    public RealmResults<AccelerometerData> query(Realm realm) {
        return realm.where(AccelerometerData.class).between(
                FIELD_TIMESTAMP,
                mTimestampInRange.getFromTimestamp(),
                mTimestampInRange.getToTimestamp()
        ).findAllAsync();
    }
}
