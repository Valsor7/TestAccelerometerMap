package com.boost.testaccelerometermap.data.db.realm;

import com.boost.testaccelerometermap.data.db.DBDao;
import com.boost.testaccelerometermap.data.repository.RepositoryCallback;
import com.boost.testaccelerometermap.presentation.model.AccelerometerData;
import com.boost.testaccelerometermap.presentation.model.LocationModel;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;

/**
 * Created by yaroslav on 30.05.17.
 */

public class RealmLocationDao implements DBDao<LocationModel> {

    @Inject
    public RealmLocationDao() {
    }

    @Override
    public void getAllData(RepositoryCallback<List<LocationModel>> callback) {
        Realm realm = Realm.getDefaultInstance();
    }

    @Override
    public void getAllUnique(final RepositoryCallback<List<LocationModel>> callback) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                callback.onResult(realm.where(LocationModel.class).distinct("dayInMillis"));
            }
        });
    }

    @Override
    public void saveAll(List<LocationModel> items) {

    }

    @Override
    public void save(final LocationModel data) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(data);
//                LocationModel dbData = realm.createObject(LocationModel.class);
//                dbData.setLatitude(data.getLatitude());
//                dbData.setLongitude(data.getLongitude());
//                dbData.setTimestamp(data.getTimestamp());
            }
        });
    }
}
