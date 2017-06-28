package com.boost.testaccelerometermap.dagger.modules;

import com.boost.testaccelerometermap.dagger.scopes.LocationScope;
import com.boost.testaccelerometermap.data.repository.AccelerometerRealmRepositoryImpl;
import com.boost.testaccelerometermap.data.repository.Repository;
import com.boost.testaccelerometermap.data.repository.specification.accelerometer.AccelerometerSpecificationFactory;
import com.boost.testaccelerometermap.data.repository.specification.accelerometer.AccelerometerSpecificationFactoryImpl;
import com.boost.testaccelerometermap.domain.interactors.Interactor;
import com.boost.testaccelerometermap.domain.interactors.accelerometer.AccelerometerGetInRangeInteractor;
import com.boost.testaccelerometermap.domain.interactors.accelerometer.AddAccelerometerDataListInteractor;
import com.boost.testaccelerometermap.presentation.model.AccelerometerData;
import com.boost.testaccelerometermap.presentation.model.TimestampInRange;

import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yaroslav on 25.05.17.
 */
@Module
public class AccelerometerModule {

    @LocationScope
    @Provides
    public Repository<AccelerometerData> provideAccelerometerRepository(AccelerometerRealmRepositoryImpl accelerometerRepository) {
        return accelerometerRepository;
    }

    @LocationScope
    @Provides
    public AccelerometerSpecificationFactory provideAccelerometerSpecificationFactory() {
        return new AccelerometerSpecificationFactoryImpl();
    }

    @LocationScope
    @Provides
    public Interactor<List<AccelerometerData>, TimestampInRange> provideAccelerometerInRangeInteractor(
            Repository<AccelerometerData> accelerometerRepository,
            AccelerometerSpecificationFactory accelerometerSpecificationFactory) {
        return new AccelerometerGetInRangeInteractor(accelerometerRepository, accelerometerSpecificationFactory);
    }

    @LocationScope
    @Provides
    public Interactor<AccelerometerData, List<AccelerometerData>> provideAccelerometerListInteractor(
            Repository<AccelerometerData> accelerometerRepository) {
        return new AddAccelerometerDataListInteractor(accelerometerRepository);
    }
}
