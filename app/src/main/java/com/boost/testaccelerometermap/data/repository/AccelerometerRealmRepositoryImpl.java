package com.boost.testaccelerometermap.data.repository;

import android.util.Log;

import com.boost.testaccelerometermap.data.Network;
import com.boost.testaccelerometermap.data.repository.specification.RealmSpecification;
import com.boost.testaccelerometermap.data.repository.specification.Specification;
import com.boost.testaccelerometermap.presentation.model.AccelerometerData;

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
    private final Realm mRealm;

    @Inject
    public AccelerometerRealmRepositoryImpl(Network network) {
        mNetwork = network;
        mRealm = Realm.getDefaultInstance();
    }

    @Override
    public Observable<List<AccelerometerData>> getAll() {
        Log.d(TAG, "getAllData: ");
        final RealmResults<AccelerometerData> realmResults = mRealm.where(AccelerometerData.class).findAll();
        return getObservableData(realmResults);
    }

    @Override
    public Observable<List<AccelerometerData>> query(Specification specification) {
        RealmSpecification<RealmResults<AccelerometerData>> realmSpecification =
                (RealmSpecification<RealmResults<AccelerometerData>>) specification;
        return getObservableData(realmSpecification.query(mRealm));
    }

    private Observable<List<AccelerometerData>> getObservableData(final RealmResults<AccelerometerData> queryResults) {
        return Observable.create(subscriber ->
                queryResults.addChangeListener(accelerometerData -> {
                    Log.d(TAG, "onChange: ");
                    subscriber.onNext(accelerometerData);
                })
        );

    }

    @Override
    public Observable<AccelerometerData> add(final AccelerometerData item) {
        return Observable.create(subscriber -> mRealm.copyToRealm(item));
    }

    @Override
    public Observable<AccelerometerData> addAll(final List<AccelerometerData> items) {
        return Observable.create(subscriber ->
                mRealm.executeTransaction(realm -> realm.copyToRealm(items))
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

    @Override
    public void cleanResources(){
        if (mRealm != null) mRealm.close();
    }
}
