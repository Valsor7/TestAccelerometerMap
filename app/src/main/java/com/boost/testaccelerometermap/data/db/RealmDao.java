package com.boost.testaccelerometermap.data.db;

import android.util.Log;

import com.boost.testaccelerometermap.data.repository.RepositoryCallback;
import com.boost.testaccelerometermap.presentation.model.AccelerometerData;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;

/**
 * Created by yaroslav on 23.05.17.
 */

public class RealmDao implements DBDao<AccelerometerData>{
    private static final String TAG = "RealmDao";

    @Inject
    public RealmDao() {

    }

    @Override
    public void getAllData(final RepositoryCallback<List<AccelerometerData>> callback){
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
                AccelerometerData dbData = realm.createObject(AccelerometerData.class);
                dbData.setX(data.getX());
                dbData.setY(data.getY());
                dbData.setZ(data.getZ());
                Log.d(TAG, "execute: ");
            }
        });
    }


}
