package com.boost.testaccelerometermap.data.db.realm;

import android.util.Log;

import com.boost.testaccelerometermap.data.db.DBDao;
import com.boost.testaccelerometermap.data.repository.RepositoryCallback;
import com.boost.testaccelerometermap.presentation.model.LocationModel;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Created by yaroslav on 30.05.17.
 */

public class RealmLocationDao implements DBDao<LocationModel> {
    private static final String TAG = "RealmLocationDao";

    @Inject
    public RealmLocationDao() {
    }

    @Override
    public void getAllData(final RepositoryCallback<List<LocationModel>> callback) {
        Log.d(TAG, "getAllData: query");
        Realm realm = Realm.getDefaultInstance();
        RealmResults<LocationModel> realmResults = realm.where(LocationModel.class).findAllAsync();
        getAllData(realmResults, callback);
    }

    @Override
    public void getAllById(long id, RepositoryCallback<List<LocationModel>> callback) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<LocationModel> realmResults = realm.where(LocationModel.class).equalTo(LocationModel.DAY_FIELD, id).findAllAsync();
        getAllData(realmResults, callback);
    }

    @Override
    public void getInRange(long from, long to, RepositoryCallback<List<LocationModel>> callback) {

    }

    @Override
    public void getAllUnique(final RepositoryCallback<List<LocationModel>> callback) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<LocationModel> realmResults = realm.where(LocationModel.class).distinctAsync(LocationModel.DAY_FIELD);
        getAllData(realmResults, callback);
    }

    private void getAllData(final RealmResults<LocationModel> queryResults, final RepositoryCallback<List<LocationModel>> callback){
        Log.d(TAG, "getAllData: ");
        queryResults.addChangeListener(new RealmChangeListener<RealmResults<LocationModel>>() {
            @Override
            public void onChange(RealmResults<LocationModel> locationModels) {
                Log.d(TAG, "onChange: ");
                if(locationModels != null && locationModels.isValid() && locationModels.isLoaded()) {
                    Log.d(TAG, "onChange: models" + locationModels.size());
                    queryResults.removeAllChangeListeners();
                    callback.onResult(locationModels);
                }
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
            }
        });
    }
}
