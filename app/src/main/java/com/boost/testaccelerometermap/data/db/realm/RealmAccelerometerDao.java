package com.boost.testaccelerometermap.data.db.realm;

import android.util.Log;

import com.boost.testaccelerometermap.data.db.DBDao;
import com.boost.testaccelerometermap.data.repository.RepositoryCallback;
import com.boost.testaccelerometermap.presentation.model.AccelerometerData;
import com.boost.testaccelerometermap.presentation.model.LocationModel;

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

    @Inject
    public RealmAccelerometerDao() {

    }

    @Override
    public void getAllData(final RepositoryCallback<List<AccelerometerData>> callback) {
        Log.d(TAG, "getAllData: ");
        Realm realm = Realm.getDefaultInstance();
        final RealmResults<AccelerometerData> realmResults = realm.where(AccelerometerData.class).findAllAsync();
        getAllData(realmResults, callback);
    }

    @Override
    public void getAllById(long id, RepositoryCallback<List<AccelerometerData>> callback) {

    }

    @Override
    public void getInRange(final long from, final long to, final RepositoryCallback<List<AccelerometerData>> callback) {
        Realm realm = Realm.getDefaultInstance();
        final RealmResults<AccelerometerData> realmResults = realm.where(AccelerometerData.class).between("timestamp", from, to).findAllAsync();
        getAllData(realmResults, callback);
    }

    @Override
    public void getAllUnique(RepositoryCallback<List<AccelerometerData>> callback) {
        Log.d(TAG, "getAllUnique: ");
    }

    private void getAllData(final RealmResults<AccelerometerData> queryResults, final RepositoryCallback<List<AccelerometerData>> callback){
        queryResults.addChangeListener(new RealmChangeListener<RealmResults<AccelerometerData>>() {
            @Override
            public void onChange(RealmResults<AccelerometerData> accelerometerData) {
                Log.d(TAG, "onChange: ");
                if(accelerometerData != null && accelerometerData.isValid() && accelerometerData.isLoaded()) {
                    queryResults.removeAllChangeListeners();
                    callback.onResult(accelerometerData);
                }
            }
        });
    }

    @Override
    public void saveAll(final List<AccelerometerData> items) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(items);
            }
        });
    }

    @Override
    public void save(final AccelerometerData item) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(item);
            }
        });
    }


}
