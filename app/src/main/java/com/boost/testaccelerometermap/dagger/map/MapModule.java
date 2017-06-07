package com.boost.testaccelerometermap.dagger.map;

import com.boost.testaccelerometermap.dagger.map.qualifiers.Accelerometer;
import com.boost.testaccelerometermap.dagger.map.qualifiers.Location;
import com.boost.testaccelerometermap.data.db.DBDao;
import com.boost.testaccelerometermap.data.db.realm.RealmAccelerometerDao;
import com.boost.testaccelerometermap.data.db.realm.RealmLocationDao;
import com.boost.testaccelerometermap.data.repository.AccelerometerRepositoryImpl;
import com.boost.testaccelerometermap.data.repository.LocationRepositoryImpl;
import com.boost.testaccelerometermap.data.repository.Repository;
import com.boost.testaccelerometermap.presentation.model.AccelerometerData;
import com.boost.testaccelerometermap.presentation.model.LocationModel;
import com.boost.testaccelerometermap.presentation.view.map.LocationHelper;
import com.boost.testaccelerometermap.presentation.view.map.MapFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yaroslav on 24.05.17.
 */
@Module
public class MapModule {

    private MapFragment mFragment;

    public MapModule(MapFragment fragment) {
        mFragment = fragment;
    }

    @MapScope
    @Provides
    public LocationHelper providesLocationHelper(){
        return new LocationHelper(mFragment.getActivity());
    }

    @MapScope
    @Provides
    @Accelerometer
    public DBDao providesAccelerometerDao(RealmAccelerometerDao realmDao){
        return realmDao;
    }

    @MapScope
    @Provides
    @Location
    public DBDao providesLocationDao(RealmLocationDao realmDao){
        return realmDao;
    }



    @MapScope
    @Provides
    @Accelerometer
    public Repository<AccelerometerData> providesAccelerometerRepository(AccelerometerRepositoryImpl accelerometerRepository){
        return accelerometerRepository;
    }

    @MapScope
    @Provides
    @Location
    public Repository<LocationModel> providesLocationRepository(LocationRepositoryImpl locationRepository){
        return locationRepository;
    }
}
