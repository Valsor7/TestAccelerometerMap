package com.boost.testaccelerometermap.domain.interactors.location;


import android.location.Location;

import com.boost.testaccelerometermap.data.hardware.LocationHelper;
import com.boost.testaccelerometermap.domain.Mapper;
import com.boost.testaccelerometermap.domain.interactors.Interactor;
import com.boost.testaccelerometermap.presentation.model.LatLangDate;

import io.reactivex.Observable;

public class UpdateLocationsInteractor extends Interactor<LatLangDate,Void> {

    private LocationHelper mHelper;
    private Mapper<Location, LatLangDate> mLatLangDateMapper;

    public UpdateLocationsInteractor(LocationHelper helper, Mapper<Location, LatLangDate> latLangDateMapper) {
        mHelper = helper;
        mLatLangDateMapper = latLangDateMapper;
    }

    @Override
    public Observable<LatLangDate> execute(Void requestModel) {
        return mHelper.subscribeToLocationEmitter().map(mLatLangDateMapper::map);
    }
}
