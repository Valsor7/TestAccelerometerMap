package com.boost.testaccelerometermap.dagger.modules;

import com.boost.testaccelerometermap.dagger.scopes.LocationScope;
import com.boost.testaccelerometermap.data.repository.AccelerometerRealmRepositoryImpl;
import com.boost.testaccelerometermap.data.repository.AccelerometerRepository;
import com.boost.testaccelerometermap.data.repository.specification.accelerometer.AccelerometerSpecificationFactory;
import com.boost.testaccelerometermap.data.repository.specification.accelerometer.AccelerometerSpecificationFactoryImpl;
import com.boost.testaccelerometermap.presentation.model.AccelerometerData;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yaroslav on 25.05.17.
 */
@Module
public class AccelerometerModule {

    @LocationScope
    @Provides
    public AccelerometerRepository<AccelerometerData> provideAccelerometerRepository(AccelerometerRealmRepositoryImpl accelerometerRepository){
        return accelerometerRepository;
    }

    @LocationScope
    @Provides
    public AccelerometerSpecificationFactory provideAccelerometerSpecificationFactory(){
        return new AccelerometerSpecificationFactoryImpl();
    }
}
