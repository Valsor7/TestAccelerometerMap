package com.boost.testaccelerometermap.domain.interactors.location;


import android.location.Location;

import com.boost.testaccelerometermap.data.hardware.LocationHelper;
import com.boost.testaccelerometermap.data.model.response.SuccessResponse;
import com.boost.testaccelerometermap.domain.Mapper;
import com.boost.testaccelerometermap.domain.Repository;
import com.boost.testaccelerometermap.domain.interactors.Interactor;
import com.boost.testaccelerometermap.presentation.model.LatLangDate;
import com.boost.testaccelerometermap.presentation.model.LocationModel;

import io.reactivex.Observable;

public class UpdateLocationsInteractor extends Interactor<SuccessResponse,Void> {

    private Repository<LocationModel> mLocationRepositiory;
    private LocationHelper mHelper;
    private Mapper<Location, LocationModel> mLocationToLocationModelMapper;

    public UpdateLocationsInteractor(Repository<LocationModel> locationRepositiory,
                                     LocationHelper helper,
                                     Mapper<Location, LocationModel> locationToLocationModelMapper) {
        mLocationRepositiory = locationRepositiory;
        mHelper = helper;
        mLocationToLocationModelMapper = locationToLocationModelMapper;
        mLocationRepositiory = locationRepositiory;
    }

    @Override
    public Observable<SuccessResponse> execute(Void requestModel) {
        return mHelper.subscribeToLocationEmitter().map(mLocationToLocationModelMapper::map)
                .flatMap(locationModel -> mLocationRepositiory.add(locationModel));
    }
}
