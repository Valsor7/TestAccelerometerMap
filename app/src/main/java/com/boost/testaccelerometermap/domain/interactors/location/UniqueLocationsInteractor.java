package com.boost.testaccelerometermap.domain.interactors.location;


import com.boost.testaccelerometermap.data.repository.Repository;
import com.boost.testaccelerometermap.data.repository.specification.location.LocationSpecificationFactory;
import com.boost.testaccelerometermap.domain.interactors.Interactor;
import com.boost.testaccelerometermap.presentation.model.LocationModel;

import java.util.List;

import io.reactivex.Observable;

public class UniqueLocationsInteractor extends Interactor<List<LocationModel>, Void> {
    private Repository<LocationModel> mLocationModelRepository;
    private LocationSpecificationFactory mLocationFactory;

    public UniqueLocationsInteractor(Repository<LocationModel> locationModelRepository, LocationSpecificationFactory factory) {
        mLocationModelRepository = locationModelRepository;
        mLocationFactory = factory;
    }

    @Override
    protected Observable<List<LocationModel>> buildObservable(Void v) {
        return mLocationModelRepository.query(mLocationFactory.createGetUniqueLocations());
    }
}
