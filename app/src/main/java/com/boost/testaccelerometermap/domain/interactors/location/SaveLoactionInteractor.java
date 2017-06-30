package com.boost.testaccelerometermap.domain.interactors.location;

import com.boost.testaccelerometermap.data.model.response.SuccessResponse;
import com.boost.testaccelerometermap.data.repository.LocationRepositoryImpl;
import com.boost.testaccelerometermap.data.repository.Repository;
import com.boost.testaccelerometermap.domain.interactors.Interactor;
import com.boost.testaccelerometermap.presentation.model.LocationModel;

import io.reactivex.Observable;

/**
 * Created by yaroslav on 28.06.17.
 */

public class SaveLoactionInteractor extends Interactor<SuccessResponse, LocationModel> {

    private Repository<LocationModel> mLocationRepository;

    public SaveLoactionInteractor(Repository<LocationModel> locationRepository) {
        mLocationRepository = locationRepository;
    }

    @Override
    protected Observable<SuccessResponse> buildObservable(LocationModel requestModel) {
        return mLocationRepository.add(requestModel);
    }
}
