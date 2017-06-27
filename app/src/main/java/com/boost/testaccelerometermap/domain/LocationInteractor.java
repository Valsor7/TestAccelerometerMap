package com.boost.testaccelerometermap.domain;


import com.boost.testaccelerometermap.data.repository.Repository;
import com.boost.testaccelerometermap.data.repository.specification.Specification;
import com.boost.testaccelerometermap.presentation.model.LocationModel;

import io.reactivex.Observable;

public class LocationInteractor extends Interactor<LocationModel>{
    private Repository<LocationModel> mLocationModelRepository;

    public LocationInteractor(Repository<LocationModel> locationModelRepository) {
        mLocationModelRepository = locationModelRepository;
    }

    @Override
    Observable<LocationModel> buildUseCaseObservable(Specification specification) {
        return null;
    }
}
