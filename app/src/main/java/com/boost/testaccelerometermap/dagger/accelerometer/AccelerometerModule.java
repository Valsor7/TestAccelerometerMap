package com.boost.testaccelerometermap.dagger.accelerometer;

import com.boost.testaccelerometermap.dagger.map.qualifiers.Accelerometer;
import com.boost.testaccelerometermap.data.db.DBDao;
import com.boost.testaccelerometermap.data.db.realm.RealmAccelerometerDao;
import com.boost.testaccelerometermap.data.repository.AccelerometerRepositoryImpl;
import com.boost.testaccelerometermap.data.repository.Repository;
import com.boost.testaccelerometermap.presentation.model.AccelerometerData;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yaroslav on 25.05.17.
 */
@Module
public class AccelerometerModule {

    @AccelerometerScope
    @Provides
    public DBDao<AccelerometerData> providesDao(RealmAccelerometerDao realmDao){
        return realmDao;
    }

    @AccelerometerScope
    @Provides
    public Repository<AccelerometerData> provideAccelerometerRepository(AccelerometerRepositoryImpl mapRepository){
        return mapRepository;
    }
}
