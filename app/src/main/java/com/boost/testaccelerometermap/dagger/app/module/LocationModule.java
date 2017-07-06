package com.boost.testaccelerometermap.dagger.app.module;

import com.boost.testaccelerometermap.data.model.Location;
import com.boost.testaccelerometermap.data.repository.LocationRepositoryImpl;
import com.boost.testaccelerometermap.domain.Mapper;
import com.boost.testaccelerometermap.domain.Repository;
import com.boost.testaccelerometermap.data.repository.specification.location.LocationSpecificationFactory;
import com.boost.testaccelerometermap.data.repository.specification.location.LocationSpecificationFactoryImpl;
import com.boost.testaccelerometermap.domain.locationmappers.LocationModelToLocationMapper;
import com.boost.testaccelerometermap.domain.locationmappers.LocationToLocationModelMapper;
import com.boost.testaccelerometermap.presentation.model.LocationModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LocationModule {

    @Provides
    @Singleton
    public Mapper<Location, LocationModel> provideLocationToLocationModelMapper(){
        return new LocationToLocationModelMapper();
    }

    @Provides
    @Singleton
    public Mapper<LocationModel, Location> provideLocationModelToLocationMapper(){
        return new LocationModelToLocationMapper();
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
//
//    @Singleton
//    @Provides
//    public LocationHelper provideLocationHelper(Context context){
//        return new LocationHelper(context);
//    }


}
