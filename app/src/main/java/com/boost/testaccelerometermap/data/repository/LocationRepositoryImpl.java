package com.boost.testaccelerometermap.data.repository;

import android.location.Location;

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
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Cancellable;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmObject;
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
        RealmResults<LocationDate> realmResults = realmSpecification.query(realm);

        return Observable.create(new RealmResultsObservable<>(realmResults))
                        .flatMap(Observable::fromIterable)
                        .map(mLocationToLocationModelMapper::map)
                        .toList()
                        .toObservable();
    }

    public static class RealmResultsObservable<T extends RealmObject> implements ObservableOnSubscribe<RealmResults<T>> {

        public static <T extends RealmObject> Observable<RealmResults<T>> from(RealmResults<T> realmResults) {
            return Observable.create(new RealmResultsObservable<>(realmResults));
        }

        private final RealmResults<T> realmResults;

        private RealmResultsObservable(RealmResults<T> realmResults) {
            this.realmResults = realmResults;
        }

        @Override
        public void subscribe(ObservableEmitter<RealmResults<T>> emitter) throws Exception {
            // Initial element
            emitter.onNext(realmResults);

            RealmChangeListener<RealmResults<T>> changeListener = emitter::onNext;

            realmResults.addChangeListener(changeListener);

            emitter.setCancellable(() -> realmResults.removeChangeListener(changeListener));
        }
    }
}
