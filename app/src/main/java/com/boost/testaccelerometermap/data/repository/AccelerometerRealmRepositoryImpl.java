package com.boost.testaccelerometermap.data.repository;

import android.util.Log;

import com.boost.testaccelerometermap.data.Network;
import com.boost.testaccelerometermap.data.repository.specification.RealmSpecification;
import com.boost.testaccelerometermap.data.repository.specification.Specification;
import com.boost.testaccelerometermap.domain.response.Response;
import com.boost.testaccelerometermap.presentation.model.AccelerometerData;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by yaroslav on 24.05.17.
 */
public class AccelerometerRealmRepositoryImpl implements AccelerometerRepository<AccelerometerData> {
    private static final String TAG = "MapRepository";
    private final Network mNetwork;
    private final Realm mRealm;

    @Inject
    public AccelerometerRealmRepositoryImpl(Network network) {
        mNetwork = network;
        mRealm = Realm.getDefaultInstance();
    }

    @Override
    public Observable<Response<AccelerometerData>> getAll() {
        Log.d(TAG, "getAllData: ");
        final RealmResults<AccelerometerData> realmResults = mRealm.where(AccelerometerData.class).findAll();
        return getObservableData(realmResults).map(Response::new);
    }

    @Override
    public Observable<Response<AccelerometerData>> query(Specification specification) {
        RealmSpecification<RealmResults<AccelerometerData>> realmSpecification =
                (RealmSpecification<RealmResults<AccelerometerData>>) specification;
        return getObservableData(realmSpecification.query(mRealm)).map(Response::new);
    }

    private Observable<List<AccelerometerData>> getObservableData(final RealmResults<AccelerometerData> queryResults) {
        return Observable.create(e ->
                queryResults.addChangeListener(accelerometerData -> {
                    Log.d(TAG, "onChange: ");
                    e.onNext(queryResults);
                })
        );

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
