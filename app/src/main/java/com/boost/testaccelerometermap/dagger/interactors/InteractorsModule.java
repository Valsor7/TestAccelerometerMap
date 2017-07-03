package com.boost.testaccelerometermap.dagger.interactors;

import com.boost.testaccelerometermap.dagger.scopes.DomainScope;
import com.boost.testaccelerometermap.data.model.response.SuccessResponse;
import com.boost.testaccelerometermap.data.repository.Repository;
import com.boost.testaccelerometermap.data.repository.specification.accelerometer.AccelerometerSpecificationFactory;
import com.boost.testaccelerometermap.data.repository.specification.location.LocationSpecificationFactory;
import com.boost.testaccelerometermap.domain.interactors.Interactor;
import com.boost.testaccelerometermap.domain.interactors.accelerometer.AccelerometerGetInRangeInteractor;
import com.boost.testaccelerometermap.domain.interactors.location.LocationsByDayInteractor;
import com.boost.testaccelerometermap.domain.interactors.location.SaveLoactionInteractor;
import com.boost.testaccelerometermap.domain.interactors.location.UniqueLocationsInteractor;
import com.boost.testaccelerometermap.presentation.model.AccelerometerData;
import com.boost.testaccelerometermap.presentation.model.LocationModel;
import com.boost.testaccelerometermap.presentation.model.TimestampInRange;

import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yaroslav on 02.07.17.
 */
@Module
public class InteractorsModule {

    @DomainScope
    @Provides
    public Interactor<List<LocationModel>, Long> provideLocationsByDayInteractor(
            Repository<LocationModel> locationModelRepository,
            LocationSpecificationFactory locationSpecificationFactory){
        return new LocationsByDayInteractor(locationModelRepository, locationSpecificationFactory);
    }

    @DomainScope
    @Provides
    public Interactor<SuccessResponse, LocationModel> provideSaveLocationInteractor(Repository<LocationModel> locationModelRepository){
        return new SaveLoactionInteractor(locationModelRepository);
    }

    @DomainScope
    @Provides
    public Interactor<List<LocationModel>, Void> provideUniqueLocationsInteractor(
            Repository<LocationModel> locationModelRepository,
            LocationSpecificationFactory locationSpecificationFactory){
        return new UniqueLocationsInteractor(locationModelRepository, locationSpecificationFactory);
    }

    @DomainScope
    @Provides
    public Interactor<List<AccelerometerData>, TimestampInRange> provideAccelerometerInRangeInteractor(
            Repository<AccelerometerData> accelerometerRepository,
            AccelerometerSpecificationFactory accelerometerSpecificationFactory) {
        return new AccelerometerGetInRangeInteractor(accelerometerRepository, accelerometerSpecificationFactory);
    }
}
