package com.boost.testaccelerometermap.data.db;

import android.util.Log;

import com.boost.testaccelerometermap.data.repository.RepositoryCallback;
import com.boost.testaccelerometermap.presentation.model.AccelerometerData;

import java.util.List;

import javax.inject.Inject;

import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by yaroslav on 23.05.17.
 */

public class RealmDao implements DBDao<AccelerometerData>{
    private static final String TAG = "RealmDao";
    private RepositoryCallback<List<AccelerometerData>> mRepositoryCallback;

    private OrderedRealmCollectionChangeListener<RealmResults<AccelerometerData>> mRealmQueryCallback = new OrderedRealmCollectionChangeListener<RealmResults<AccelerometerData>>() {
        @Override
        public void onChange(RealmResults<AccelerometerData> accelerometerData, OrderedCollectionChangeSet changeSet) {
            Log.d(TAG, "onChange: " + accelerometerData.size());
            if (changeSet == null){

            } else {
                mRepositoryCallback.onResult(accelerometerData);
            }
        }
    };

    @Inject
    public RealmDao() {

    }

    @Override
    public void getAllData(RepositoryCallback<List<AccelerometerData>> callback){
        Log.d(TAG, "getAllData: ");
        mRepositoryCallback = callback;
        callback.onResult(Realm.getDefaultInstance().where(AccelerometerData.class).findAll());
//        RealmQuery<AccelerometerData> query = Realm.getDefaultInstance().where(AccelerometerData.class);
//        RealmResults<AccelerometerData> realmResults = query.findAllAsync();
//        realmResults.addChangeListener(mRealmQueryCallback);
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
