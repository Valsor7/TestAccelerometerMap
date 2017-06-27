package com.boost.testaccelerometermap.data.repository;

import android.util.Log;

import com.boost.testaccelerometermap.data.Network;
import com.boost.testaccelerometermap.data.repository.specification.RealmSpecification;
import com.boost.testaccelerometermap.data.repository.specification.Specification;
import com.boost.testaccelerometermap.presentation.model.AccelerometerData;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Created by yaroslav on 24.05.17.
 */
public class AccelerometerRealmRepositoryImpl implements Repository<AccelerometerData> {
    private static final String TAG = "MapRepository";
    private final Network mNetwork;

    @Inject
    public AccelerometerRealmRepositoryImpl(Network network) {
        mNetwork = network;
    }

    @Override
    public void getAll(RepositoryCallback<List<AccelerometerData>> callback) {
        Log.d(TAG, "getAllData: ");
        Realm realm = Realm.getDefaultInstance();
        final RealmResults<AccelerometerData> realmResults = realm.where(AccelerometerData.class).findAllAsync();
        getAllData(realmResults, callback);
    }

    @Override
    public void query(Specification specification, RepositoryCallback<List<AccelerometerData>> callback) {
            RealmSpecification<RealmResults<AccelerometerData>> realmSpecification =
                    (RealmSpecification<RealmResults<AccelerometerData>>) specification;
            getAllData(realmSpecification.query(), callback);
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
    public void add(final AccelerometerData item) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(item);
            }
        });
    }

    @Override
    public void addAll(final List<AccelerometerData> items) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(items);
            }
        });
    }

    @Override
    public void remove(AccelerometerData item) {

    }

    @Override
    public void update(AccelerometerData item) {

    }
}
