package com.boost.testaccelerometermap.domain.interactors.location;

import com.boost.testaccelerometermap.data.repository.Repository;
import com.boost.testaccelerometermap.data.repository.specification.location.LocationSpecificationFactory;
import com.boost.testaccelerometermap.domain.interactors.Interactor;
import com.boost.testaccelerometermap.presentation.model.LocationModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by yaroslav on 28.06.17.
 */

public class LocationsByDayInteractor extends Interactor<List<LocationModel>, Long> {

    private Repository<LocationModel> mLocationModelRepository;
    private LocationSpecificationFactory mLocationSpecificationFactory;

    public LocationsByDayInteractor(Repository<LocationModel> locationModelRepository, LocationSpecificationFactory factory) {
        mLocationModelRepository = locationModelRepository;
        mLocationSpecificationFactory = factory;
    }

    @Override
    protected Observable<List<LocationModel>> buildObservable(Long dayInMillis) {
        return mLocationModelRepository.query(mLocationSpecificationFactory.createGetLocationById(dayInMillis));
    }
}
