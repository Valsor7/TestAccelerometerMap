package com.boost.testaccelerometermap.domain.interactors.location;


import android.location.Location;

import com.boost.testaccelerometermap.data.hardware.LocationHelper;
import com.boost.testaccelerometermap.domain.interactors.Interactor;

import io.reactivex.Observable;

public class UpdateLocationsInteractor extends Interactor<Location,Void> {

    private LocationHelper mHelper;

    public UpdateLocationsInteractor(LocationHelper helper) {
        mHelper = helper;
    }

    @Override
    public Observable<Location> execute(Void requestModel) {
        return mHelper.subscribeToLocationEmitter();
    }
}
