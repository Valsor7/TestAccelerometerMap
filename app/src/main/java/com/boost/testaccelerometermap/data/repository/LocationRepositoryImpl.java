package com.boost.testaccelerometermap.data.repository;

import com.boost.testaccelerometermap.data.model.LocationDate;
import com.boost.testaccelerometermap.data.model.response.SuccessResponse;
import com.boost.testaccelerometermap.data.repository.specification.RealmSpecification;
import com.boost.testaccelerometermap.data.repository.specification.Specification;
import com.boost.testaccelerometermap.domain.Mapper;
import com.boost.testaccelerometermap.domain.Repository;
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
    private Mapper<LocationDate, LocationModel> mLocationToLocationModelMapper;
    private Mapper<LocationModel, LocationDate> mLocationModelToLocationMapper;

    @Inject
    public LocationRepositoryImpl(Mapper<LocationDate, LocationModel> locationToLocationModelMapper,
                                  Mapper<LocationModel, LocationDate> locationModelToLocationMapper) {
        mLocationToLocationModelMapper = locationToLocationModelMapper;
        mLocationModelToLocationMapper = locationModelToLocationMapper;
    }

    @Override
    public Observable<SuccessResponse> add(final LocationModel item) {
        return Observable.just(item)
                .map(mLocationModelToLocationMapper::map)
                .map(location -> {
                    Realm.getDefaultInstance().executeTransaction(realm1 -> realm1.copyToRealm(location));
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
            RealmSpecification<RealmResults<LocationDate>> realmSpecification =
                    (RealmSpecification<RealmResults<LocationDate>>) specification;

        return Observable.just(realm.copyFromRealm(realmSpecification.query(realm)))
                .flatMap(Observable::fromIterable)
                .map(mLocationToLocationModelMapper::map)
                .toList()
                .toObservable();
    }
}
