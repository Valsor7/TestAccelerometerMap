package com.boost.testaccelerometermap.domain.interactors.location;

import com.boost.testaccelerometermap.data.model.response.SuccessResponse;
import com.boost.testaccelerometermap.domain.Repository;
import com.boost.testaccelerometermap.domain.interactors.Interactor;
import com.boost.testaccelerometermap.data.model.Location;

import io.reactivex.Observable;

/**
 * Created by yaroslav on 28.06.17.
 */

public class SaveLoactionInteractor extends Interactor<SuccessResponse, Location> {

    private Repository<Location> mLocationRepository;

    public SaveLoactionInteractor(Repository<Location> locationRepository) {
        mLocationRepository = locationRepository;
    }

    @Override
    protected Observable<SuccessResponse> buildObservable(Location requestModel) {
        return mLocationRepository.add(requestModel);
    }
}
