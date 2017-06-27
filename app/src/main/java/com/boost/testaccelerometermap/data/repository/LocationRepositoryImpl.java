package com.boost.testaccelerometermap.data.repository;

import com.boost.testaccelerometermap.data.repository.specification.RealmSpecification;
import com.boost.testaccelerometermap.data.repository.specification.Specification;
import com.boost.testaccelerometermap.presentation.model.LocationModel;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by yaroslav on 30.05.17.
 */

public class LocationRepositoryImpl implements AccelerometerRepository<LocationModel> {
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
    public List<LocationModel> getAll() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(LocationModel.class).findAll();
    }

    @Override
    public List<LocationModel> query(Specification specification) {
        if (specification instanceof RealmSpecification) {
            RealmSpecification<RealmResults<LocationModel>> realmSpecification =
                    (RealmSpecification<RealmResults<LocationModel>>) specification;
            return realmSpecification.query();
        } else {
            return null;
        }
    }
}
