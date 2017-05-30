package com.boost.testaccelerometermap.data.db.realm;

import com.boost.testaccelerometermap.data.db.DBDao;
import com.boost.testaccelerometermap.data.repository.RepositoryCallback;
import com.boost.testaccelerometermap.presentation.model.LatLngLocation;

import java.util.List;

import io.realm.Realm;

/**
 * Created by yaroslav on 30.05.17.
 */

public class RealmMapDao implements DBDao<LatLngLocation> {

    @Override
    public void getAllData(RepositoryCallback<List<LatLngLocation>> callback) {

    }

    @Override
    public void save(final LatLngLocation data) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // TODO: 30.05.17 try to use clone pattern
                LatLngLocation dbData = realm.createObject(LatLngLocation.class);
                dbData.setLatitude(data.getLatitude());
                dbData.setLongitude(data.getLongitude());
                dbData.setTimestamp(data.getTimestamp());
            }
        });
    }
}
