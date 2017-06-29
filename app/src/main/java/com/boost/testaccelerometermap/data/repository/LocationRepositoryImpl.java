package com.boost.testaccelerometermap.data.repository;

import android.util.Log;

import com.boost.testaccelerometermap.data.repository.specification.RealmSpecification;
import com.boost.testaccelerometermap.data.repository.specification.Specification;
import com.boost.testaccelerometermap.presentation.model.AccelerometerData;
import com.boost.testaccelerometermap.presentation.model.LocationModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.realm.Realm;
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
    public Observable<LocationModel> add(final LocationModel item) {
        return Observable.create(subscriber -> {
            Realm realm = Realm.getDefaultInstance();
            realm.executeTransaction(realm1 -> realm.copyToRealm(item));
            Log.d(TAG, "add: ");
        });
    }

    @Override
    public Observable<LocationModel> addAll(List<LocationModel> items) {
        return null;
    }

    @Override
    public Observable<LocationModel> remove(LocationModel item) {
        return null;
    }

    @Override
    public Observable<LocationModel> update(LocationModel item) {
        return null;
    }

    @Override
    public Observable<List<LocationModel>> getAll() {
      return null;
    }

    @Override
    public Observable<List<LocationModel>> query(Specification specification) {
        Realm realm = Realm.getDefaultInstance();
        RealmSpecification<RealmResults<LocationModel>> realmSpecification =
                (RealmSpecification<RealmResults<LocationModel>>) specification;
        RealmResults<LocationModel> realmResults = realmSpecification.query(realm);

        return Observable.just(realm.copyFromRealm(realmResults));
    }
}
