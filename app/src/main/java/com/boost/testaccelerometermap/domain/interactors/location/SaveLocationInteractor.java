package com.boost.testaccelerometermap.domain.interactors.location;

import com.boost.testaccelerometermap.data.model.response.SuccessResponse;
import com.boost.testaccelerometermap.domain.Mapper;
import com.boost.testaccelerometermap.domain.Repository;
import com.boost.testaccelerometermap.domain.interactors.Interactor;
import com.boost.testaccelerometermap.presentation.model.LatLangDate;
import com.boost.testaccelerometermap.presentation.model.LocationModel;

import io.reactivex.Observable;

/**
 * Created by yaroslav on 28.06.17.
 */

public class SaveLocationInteractor extends Interactor<SuccessResponse, LatLangDate> {

    private Repository<LocationModel> mLocationRepository;
    private Mapper<LatLangDate, LocationModel> mLatLangMapper;

    public SaveLocationInteractor(Repository<LocationModel> locationRepository, Mapper<LatLangDate, LocationModel> latLangMapper) {
        mLocationRepository = locationRepository;
        mLatLangMapper = latLangMapper;
    }

    @Override
    public Observable<SuccessResponse> execute(LatLangDate requestModel) {
        return mLocationRepository.add(mLatLangMapper.map(requestModel));
    }
}
