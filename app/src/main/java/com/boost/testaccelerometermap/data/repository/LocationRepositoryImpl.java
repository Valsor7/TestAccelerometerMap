package com.boost.testaccelerometermap.data.repository;

import com.boost.testaccelerometermap.data.model.Location;
import com.boost.testaccelerometermap.data.model.response.SuccessResponse;
import com.boost.testaccelerometermap.data.repository.specification.RealmSpecification;
import com.boost.testaccelerometermap.data.repository.specification.Specification;
import com.boost.testaccelerometermap.domain.Mapper;
import com.boost.testaccelerometermap.domain.Repository;
import com.boost.testaccelerometermap.presentation.model.LocationModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by yaroslav on 30.05.17.
 */

public class LocationRepositoryImpl implements Repository<LocationModel> {
    private static final String TAG = "LocationRepositoryImpl";
    private Mapper<Location, LocationModel> mLocationToLocationModelMapper;
    private Mapper<LocationModel, Location> mLocationModelToLocationMapper;

    @Inject
    public LocationRepositoryImpl(Mapper<Location, LocationModel> locationToLocationModelMapper,
                                  Mapper<LocationModel, Location> locationModelToLocationMapper) {
        mLocationToLocationModelMapper = locationToLocationModelMapper;
        mLocationModelToLocationMapper = locationModelToLocationMapper;
    }

    @Override
    public Completable add(final LocationModel item) {
        return Observable.just(item)
                .map(mLocationModelToLocationMapper::map)
                .flatMapCompletable(location -> {
                            Realm.getDefaultInstance().executeTransaction(realm1 -> realm1.copyToRealm(location));
                            return Completable.complete();
                        }
                );
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
            RealmSpecification<RealmResults<Location>> realmSpecification =
                    (RealmSpecification<RealmResults<Location>>) specification;

        return Observable.just(realm.copyFromRealm(realmSpecification.query(realm)))
                .flatMap(Observable::fromIterable)
                .map(mLocationToLocationModelMapper::map)
                .toList()
                .toObservable();
    }
}
