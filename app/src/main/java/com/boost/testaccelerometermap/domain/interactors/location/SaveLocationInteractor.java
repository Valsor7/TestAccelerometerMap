package com.boost.testaccelerometermap.domain.interactors.location;

import com.boost.testaccelerometermap.data.model.response.SuccessResponse;
import com.boost.testaccelerometermap.domain.Repository;
import com.boost.testaccelerometermap.domain.interactors.Interactor;
import com.boost.testaccelerometermap.data.model.Location;
import com.boost.testaccelerometermap.presentation.model.LocationModel;

import io.reactivex.Observable;

/**
 * Created by yaroslav on 28.06.17.
 */

public class SaveLocationInteractor extends Interactor<Object, LocationModel> {

    private Repository<LocationModel> mLocationRepository;

    public SaveLocationInteractor(Repository<LocationModel> locationRepository) {
        mLocationRepository = locationRepository;
    }

    @Override
    public Observable<Object> execute(LocationModel requestModel) {
        return mLocationRepository.add(requestModel).toObservable();
    }
}
