package com.boost.testaccelerometermap.data.repository;

import com.boost.testaccelerometermap.data.model.Location;
import com.boost.testaccelerometermap.data.model.response.SuccessResponse;
import com.boost.testaccelerometermap.data.repository.specification.RealmSpecification;
import com.boost.testaccelerometermap.data.repository.specification.Specification;
import com.boost.testaccelerometermap.domain.Repository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by yaroslav on 30.05.17.
 */

public class LocationRepositoryImpl implements Repository<Location> {
    private static final String TAG = "LocationRepositoryImpl";

    @Inject
    public LocationRepositoryImpl() {

    }

    @Override
    public Observable<SuccessResponse> add(final Location item) {
        return Observable.fromCallable(() -> {
            Realm.getDefaultInstance().executeTransaction(realm1 -> realm1.copyToRealm(item));
            return new SuccessResponse();
        });
    }

    @Override
    public Observable<SuccessResponse> addAll(List<Location> items) {
        return null;
    }

    @Override
    public Observable<Location> remove(Location item) {
        return null;
    }

    @Override
    public Observable<Location> update(Location item) {
        return null;
    }

    @Override
    public Observable<List<Location>> getAll() {
        return null;
    }

    @Override
    public Observable<List<Location>> query(Specification specification) {
        Realm realm = Realm.getDefaultInstance();
            RealmSpecification<RealmResults<Location>> realmSpecification =
                    (RealmSpecification<RealmResults<Location>>) specification;
            return Observable.just(realm.copyFromRealm(realmSpecification.query(realm)));
    }
}
