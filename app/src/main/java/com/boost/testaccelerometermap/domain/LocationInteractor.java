package com.boost.testaccelerometermap.domain;


import com.boost.testaccelerometermap.data.repository.AccelerometerRepository;
import com.boost.testaccelerometermap.data.repository.specification.location.LocationSpecificationFactory;
import com.boost.testaccelerometermap.presentation.model.LocationModel;

import io.reactivex.Observable;

public class LocationInteractor extends Interactor<LocationModel, LocationModel>{
    private AccelerometerRepository<LocationModel> mLocationModelRepository;
    private LocationSpecificationFactory mLocationFactory;

    public LocationInteractor(AccelerometerRepository<LocationModel> locationModelRepository, LocationSpecificationFactory factory) {
        mLocationModelRepository = locationModelRepository;
        mLocationFactory = factory;
    }

    @Override
    Observable<LocationModel> buildObservable(final LocationModel model) {
        return Observable.create(e -> mLocationModelRepository.add(model));
    }
}
