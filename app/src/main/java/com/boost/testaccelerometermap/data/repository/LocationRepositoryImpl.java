package com.boost.testaccelerometermap.data.repository;

import android.util.Log;
import android.view.View;

import com.boost.testaccelerometermap.data.model.response.SuccessResponse;
import com.boost.testaccelerometermap.data.repository.specification.RealmSpecification;
import com.boost.testaccelerometermap.data.repository.specification.Specification;
import com.boost.testaccelerometermap.presentation.model.AccelerometerData;
import com.boost.testaccelerometermap.presentation.model.LocationModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

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
    public Observable<SuccessResponse> add(final LocationModel item) {
        return Observable.fromCallable(() -> {
            Realm.getDefaultInstance().executeTransaction(realm1 -> realm1.copyToRealm(item));
            return new SuccessResponse();
        });
    }

    @Override
    public Observable<SuccessResponse> addAll(List<LocationModel> items) {
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
            return Observable.just(realm.copyFromRealm(realmSpecification.query(realm)));
    }
}
