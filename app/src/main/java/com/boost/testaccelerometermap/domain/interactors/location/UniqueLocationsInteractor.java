package com.boost.testaccelerometermap.domain.interactors.location;


import android.util.Log;

import com.boost.testaccelerometermap.domain.Repository;
import com.boost.testaccelerometermap.data.repository.specification.location.LocationSpecificationFactory;
import com.boost.testaccelerometermap.domain.interactors.Interactor;
import com.boost.testaccelerometermap.presentation.model.LocationModel;

import java.util.List;

import io.reactivex.Observable;

public class UniqueLocationsInteractor extends Interactor<List<LocationModel>, Void> {
    private static final String TAG = "UniqueLocationsInteract";
    private Repository<LocationModel> mLocationModelRepository;
    private LocationSpecificationFactory mLocationFactory;

    public UniqueLocationsInteractor(Repository<LocationModel> locationModelRepository, LocationSpecificationFactory factory) {
        mLocationModelRepository = locationModelRepository;
        mLocationFactory = factory;
    }

    @Override
    public Observable<List<LocationModel>> execute(Void requestModel) {
        Log.d(TAG, "buildObservable: " + mLocationModelRepository);
        return mLocationModelRepository.query(mLocationFactory.createGetUniqueLocations());
    }
}
