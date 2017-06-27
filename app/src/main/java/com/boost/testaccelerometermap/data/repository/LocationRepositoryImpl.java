package com.boost.testaccelerometermap.data.repository;

import android.util.Log;

import com.boost.testaccelerometermap.data.repository.specification.RealmSpecification;
import com.boost.testaccelerometermap.data.repository.specification.Specification;
import com.boost.testaccelerometermap.presentation.model.LocationModel;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Created by yaroslav on 30.05.17.
 */

public class LocationRepositoryImpl implements Repository<LocationModel> {
    private static final String TAG = "LocationRepositoryImpl";

    @Inject
    public LocationRepositoryImpl() {

    }

    @Override
    public void add(final LocationModel item) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(item);
            }
        });
    }

    @Override
    public void addAll(List<LocationModel> items) {

    }

    @Override
    public void remove(LocationModel item) {

    }

    @Override
    public void update(LocationModel item) {

    }

    @Override
    public void getAll(RepositoryCallback<List<LocationModel>> callback) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<LocationModel> realmResults = realm.where(LocationModel.class).findAllAsync();
        getAllData(realmResults, callback);
    }

    @Override
    public void query(Specification specification, RepositoryCallback<List<LocationModel>> callback) {
        if (specification instanceof RealmSpecification) {
            RealmSpecification<RealmResults<LocationModel>> realmSpecification =
                    (RealmSpecification<RealmResults<LocationModel>>) specification;
            getAllData(realmSpecification.query(), callback);
        }
    }

    private void getAllData(final RealmResults<LocationModel> queryResults, final RepositoryCallback<List<LocationModel>> callback){
        queryResults.addChangeListener(new RealmChangeListener<RealmResults<LocationModel>>() {
            @Override
            public void onChange(RealmResults<LocationModel> locationData) {
                Log.d(TAG, "onChange: ");
                if(locationData != null && locationData.isValid() && locationData.isLoaded()) {
                    queryResults.removeAllChangeListeners();
                    callback.onResult(locationData);
                }
            }
        });
    }
}
