package com.boost.testaccelerometermap.dagger.modules;

import com.boost.testaccelerometermap.dagger.scopes.ServiceScope;
import com.boost.testaccelerometermap.data.model.response.SuccessResponse;
import com.boost.testaccelerometermap.data.repository.AccelerometerRealmRepositoryImpl;
import com.boost.testaccelerometermap.data.repository.Repository;
import com.boost.testaccelerometermap.domain.interactors.Interactor;
import com.boost.testaccelerometermap.domain.interactors.accelerometer.AddAccelerometerDataInteractor;
import com.boost.testaccelerometermap.presentation.model.AccelerometerData;

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
}
