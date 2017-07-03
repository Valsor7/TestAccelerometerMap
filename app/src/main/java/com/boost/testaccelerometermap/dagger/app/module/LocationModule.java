package com.boost.testaccelerometermap.dagger.app.module;

import com.boost.testaccelerometermap.data.model.Location;
import com.boost.testaccelerometermap.data.repository.LocationRepositoryImpl;
import com.boost.testaccelerometermap.domain.Repository;
import com.boost.testaccelerometermap.data.repository.specification.location.LocationSpecificationFactory;
import com.boost.testaccelerometermap.data.repository.specification.location.LocationSpecificationFactoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LocationModule {
    @Singleton
    @Provides
    public Repository<Location> provideLocationRepository(LocationRepositoryImpl locationRepository){
        return locationRepository;
    }

    @Singleton
    @Provides
    public LocationSpecificationFactory provideLocationSpecificationFactory(){
        return new LocationSpecificationFactoryImpl();
    }
}
