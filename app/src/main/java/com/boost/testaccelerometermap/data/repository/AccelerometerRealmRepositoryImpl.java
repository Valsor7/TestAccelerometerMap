package com.boost.testaccelerometermap.data.repository;

import com.boost.testaccelerometermap.data.Network;
import com.boost.testaccelerometermap.data.model.AccelerometerData;
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
 * Created by yaroslav on 24.05.17.
 */
public class AccelerometerRealmRepositoryImpl implements Repository<AccelerometerData> {
    private static final String TAG = "MapRepository";

    @Inject
    public AccelerometerRealmRepositoryImpl(Network network) {
    }

    @Override
    public Observable<List<AccelerometerData>> getAll() {
        return null;
    }

    @Override
    public Observable<List<AccelerometerData>> query(Specification specification) {
        Realm realm = Realm.getDefaultInstance();
        if (specification instanceof RealmSpecification) {
            RealmSpecification<RealmResults<AccelerometerData>> realmSpecification =
                    (RealmSpecification<RealmResults<AccelerometerData>>) specification;
            RealmResults<AccelerometerData> realmResults = realmSpecification.query(realm);
            return Observable.just(realm.copyFromRealm(realmResults));
        } else
            return Observable.error(new Throwable());
    }

    @Override
    public Observable<SuccessResponse> add(final AccelerometerData item) {
        return Observable.fromCallable(() -> {
            Realm.getDefaultInstance().executeTransaction(realm1 -> realm1.copyToRealm(item));
            return new SuccessResponse();
        });
    }

    @Override
    public Observable<SuccessResponse> addAll(final List<AccelerometerData> items) {
        return Observable.fromCallable(() -> {
            Realm.getDefaultInstance().executeTransaction(realm1 -> realm1.copyToRealm(items));
            return new SuccessResponse();
        });
    }

    @Override
    public Observable<AccelerometerData> remove(AccelerometerData item) {
        return null;
    }

    @Override
    public Observable<AccelerometerData> update(AccelerometerData item) {
        return null;
    }
}
