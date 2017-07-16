package com.boost.testaccelerometermap.dagger.service;

import com.boost.testaccelerometermap.dagger.scopes.ServiceScope;
import com.boost.testaccelerometermap.data.hardware.LocationHelper;
import com.boost.testaccelerometermap.data.model.response.SuccessResponse;
import com.boost.testaccelerometermap.domain.Repository;
import com.boost.testaccelerometermap.domain.interactors.Interactor;
import com.boost.testaccelerometermap.domain.interactors.accelerometer.AddAccelerometerDataInteractor;
import com.boost.testaccelerometermap.data.model.AccelerometerData;
import com.boost.testaccelerometermap.domain.interactors.location.UpdateLocationsInteractor;
import com.boost.testaccelerometermap.domain.locationmappers.LocationToLocationModelMapper;
import com.boost.testaccelerometermap.presentation.model.LocationModel;
import com.boost.testaccelerometermap.presentation.model.LocationToLatLngMapper;

import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yaroslav on 03.07.17.
 */
@Module
public class ServiceModule {

    @ServiceScope
    @Provides
    public Interactor<SuccessResponse, AccelerometerData> provideAccelerometerListInteractor(
            Repository<AccelerometerData> accelerometerRepository) {

        return new AddAccelerometerDataInteractor(accelerometerRepository);
    }

    @ServiceScope
    @Provides
    public Interactor<SuccessResponse, Void> provideUpdateLocationsInteractor(
            Repository<LocationModel> locationRepository,
            LocationHelper locationHelper) {

        return new UpdateLocationsInteractor(locationRepository, locationHelper, new LocationToLocationModelMapper());
    }




}
