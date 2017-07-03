package com.boost.testaccelerometermap.dagger.modules;

import com.boost.testaccelerometermap.dagger.scopes.LocationScope;
import com.boost.testaccelerometermap.data.repository.AccelerometerRealmRepositoryImpl;
import com.boost.testaccelerometermap.data.repository.Repository;
import com.boost.testaccelerometermap.data.repository.specification.accelerometer.AccelerometerSpecificationFactory;
import com.boost.testaccelerometermap.data.repository.specification.accelerometer.AccelerometerSpecificationFactoryImpl;
import com.boost.testaccelerometermap.presentation.model.AccelerometerData;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yaroslav on 25.05.17.
 */
@Module
public class AccelerometerModule {

    @Singleton
    @Provides
    public Repository<AccelerometerData> provideAccelerometerRepository(AccelerometerRealmRepositoryImpl accelerometerRepository) {
        return accelerometerRepository;
    }

    @Singleton
    @Provides
    public AccelerometerSpecificationFactory provideAccelerometerSpecificationFactory() {
        return new AccelerometerSpecificationFactoryImpl();
    }
}
