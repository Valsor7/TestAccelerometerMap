package com.boost.testaccelerometermap.dagger.app.module;

import com.boost.testaccelerometermap.data.model.LocationDate;
import com.boost.testaccelerometermap.data.repository.LocationRepositoryImpl;
import com.boost.testaccelerometermap.domain.Mapper;
import com.boost.testaccelerometermap.domain.Repository;
import com.boost.testaccelerometermap.data.repository.specification.location.LocationSpecificationFactory;
import com.boost.testaccelerometermap.data.repository.specification.location.LocationSpecificationFactoryImpl;
import com.boost.testaccelerometermap.domain.locationmappers.LocationModelToLocationDateMapper;
import com.boost.testaccelerometermap.domain.locationmappers.LocationDateToLocationModelMapper;
import com.boost.testaccelerometermap.presentation.model.LocationModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LocationModule {

    @Provides
    @Singleton
    public Mapper<LocationDate, LocationModel> provideLocationToLocationModelMapper(){
        return new LocationDateToLocationModelMapper();
    }

    @Provides
    @Singleton
    public Mapper<LocationModel, LocationDate> provideLocationModelToLocationMapper(){
        return new LocationModelToLocationDateMapper();
    }

    @Singleton
    @Provides
    public Repository<LocationModel> provideLocationRepository(LocationRepositoryImpl locationRepository){
        return locationRepository;
    }

    @Singleton
    @Provides
    public LocationSpecificationFactory provideLocationSpecificationFactory(){
        return new LocationSpecificationFactoryImpl();
    }
}
