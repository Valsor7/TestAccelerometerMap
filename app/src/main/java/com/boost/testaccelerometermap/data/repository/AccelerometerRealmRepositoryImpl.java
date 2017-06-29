package com.boost.testaccelerometermap.data.repository;

import android.util.Log;

import com.boost.testaccelerometermap.data.Network;
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
 * Created by yaroslav on 24.05.17.
 */
public class AccelerometerRealmRepositoryImpl implements Repository<AccelerometerData> {
    private static final String TAG = "MapRepository";
    private final Network mNetwork;
//    private final Realm mRealm;

    @Inject
    public AccelerometerRealmRepositoryImpl(Network network) {
        mNetwork = network;
//        mRealm = Realm.getDefaultInstance();
    }

    @Override
    public Observable<List<AccelerometerData>> getAll() {
        return null;
    }

    @Override
    public Observable<List<AccelerometerData>> query(Specification specification) {
        Realm realm = Realm.getDefaultInstance();
        RealmSpecification<RealmResults<AccelerometerData>> realmSpecification =
                (RealmSpecification<RealmResults<AccelerometerData>>) specification;

        RealmResults<AccelerometerData> realmResults = realmSpecification.query(realm);
        return Observable.just(realm.copyFromRealm(realmResults));
    }

    @Override
    public Observable<AccelerometerData> add(final AccelerometerData item) {
        return Observable.create(subscriber -> {
                    Realm realm = Realm.getDefaultInstance();
                    realm.executeTransaction(realm1 -> realm.copyToRealm(item));
                }
        );
    }

    @Override
    public Observable<AccelerometerData> addAll(final List<AccelerometerData> items) {
        return Observable.create(subscriber -> {
                    Realm realm = Realm.getDefaultInstance();
                    realm.executeTransaction(realm1 -> realm.copyToRealm(items));
                }
        );
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
