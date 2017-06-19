package com.boost.testaccelerometermap.data.db.realm;

import android.util.Log;

import com.boost.testaccelerometermap.data.db.DBDao;
import com.boost.testaccelerometermap.data.repository.RepositoryCallback;
import com.boost.testaccelerometermap.presentation.model.AccelerometerData;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Created by yaroslav on 23.05.17.
 */

public class RealmAccelerometerDao implements DBDao<AccelerometerData> {
    private static final String TAG = "RealmAccelerometerDao";

    private Realm mRealm;

    @Inject
    public RealmAccelerometerDao() {
        mRealm = Realm.getDefaultInstance();
    }

    @Override
    public void getAllData(final RepositoryCallback<List<AccelerometerData>> callback) {
        Log.d(TAG, "getAllData: ");
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                callback.onResult(realm.where(AccelerometerData.class).findAll());
            }
        });
    }

    @Override
    public void getInRange(final long from, final long to, final RepositoryCallback<List<AccelerometerData>> callback) {
        final RealmResults<AccelerometerData> realmResults = mRealm.where(AccelerometerData.class).between("timestamp", from, to).findAllAsync();
        realmResults.addChangeListener(new RealmChangeListener<RealmResults<AccelerometerData>>() {
            @Override
            public void onChange(RealmResults<AccelerometerData> accelerometerData) {
                Log.d(TAG, "onChange: ");
                if(accelerometerData != null && accelerometerData.isValid() && accelerometerData.isLoaded()) {
                    realmResults.removeAllChangeListeners();
                    callback.onResult(accelerometerData);
                }
            }
        });
    }

    @Override
    public void getAllUnique(RepositoryCallback<List<AccelerometerData>> callback) {

    }

    @Override
    public void saveAll(final List<AccelerometerData> items) {
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                for (AccelerometerData item : items) {
                    AccelerometerData dbData = realm.createObject(AccelerometerData.class);
                    dbData.setX(item.getX());
                    dbData.setY(item.getY());
                    dbData.setZ(item.getZ());
                    dbData.setTimestamp(item.getTimestamp());
                }
            }
        });
    }

    @Override
    public void save(AccelerometerData item) {

    }


}
