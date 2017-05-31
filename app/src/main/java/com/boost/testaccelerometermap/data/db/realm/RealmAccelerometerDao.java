package com.boost.testaccelerometermap.data.db.realm;

import android.util.Log;

import com.boost.testaccelerometermap.data.db.DBDao;
import com.boost.testaccelerometermap.data.repository.RepositoryCallback;
import com.boost.testaccelerometermap.presentation.model.AccelerometerData;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;

/**
 * Created by yaroslav on 23.05.17.
 */

public class RealmAccelerometerDao implements DBDao<AccelerometerData> {
    private static final String TAG = "RealmDao";

    @Inject
    public RealmAccelerometerDao() {

    }

    @Override
    public void getAllData(final RepositoryCallback<List<AccelerometerData>> callback) {
        Log.d(TAG, "getAllData: ");
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                callback.onResult(realm.where(AccelerometerData.class).findAll());
            }
        });
    }

    @Override
    public void save(final AccelerometerData data) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // TODO: 30.05.17 try to use clone pattern
                AccelerometerData dbData = realm.createObject(AccelerometerData.class);
                dbData.setX(data.getX());
                dbData.setY(data.getY());
                dbData.setZ(data.getZ());
                dbData.setTimestamp(data.getTimestamp());
            }
        });
    }


}