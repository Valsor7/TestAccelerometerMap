package com.boost.testaccelerometermap.data.repository;

import android.util.Log;

import com.boost.testaccelerometermap.data.repository.specification.RealmSpecification;
import com.boost.testaccelerometermap.data.repository.specification.Specification;
import com.boost.testaccelerometermap.presentation.model.AccelerometerData;
import com.boost.testaccelerometermap.presentation.model.LocationModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by yaroslav on 30.05.17.
 */

public class LocationRepositoryImpl implements Repository<LocationModel> {
    private static final String TAG = "LocationRepositoryImpl";
    Realm mRealm;
    @Inject
    public LocationRepositoryImpl() {
        mRealm = Realm.getDefaultInstance();
    }

    @Override
    public Observable<LocationModel> add(final LocationModel item) {
        Log.d(TAG, "add: ");
        Realm realm = Realm.getDefaultInstance();
        return Observable.create(subscriber -> {
            Log.d(TAG, "add: subscribed");
            realm.copyToRealm(item);
            Log.d(TAG, "add: saved " + item);
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
        return getObservableData(mRealm.where(LocationModel.class).findAll());
    }

    @Override
    public Observable<List<LocationModel>> query(Specification specification) {
        if (specification instanceof RealmSpecification) {
            RealmSpecification<RealmResults<LocationModel>> realmSpecification =
                    (RealmSpecification<RealmResults<LocationModel>>) specification;
            return getObservableData(realmSpecification.query(mRealm));
        } else {
            return null;
        }
    }

    // TODO: 28.06.17 figure out somehow how to create only one listener and also close it
    private Observable<List<LocationModel>> getObservableData(final RealmResults<LocationModel> queryResults) {
        return Observable.create(subscriber -> {
            Log.d(TAG, "getObservableData: " );
            queryResults.addChangeListener(locations -> {
                Log.d(TAG, "onChange: ");
                subscriber.onNext(locations);
            });
        });

    }

    @Override
    public void cleanResources() {
        mRealm.close();
    }
}
