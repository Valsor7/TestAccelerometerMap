package com.boost.testaccelerometermap.dagger.modules;

import com.boost.testaccelerometermap.dagger.scopes.LocationScope;
import com.boost.testaccelerometermap.data.db.DBDao;
import com.boost.testaccelerometermap.data.db.realm.RealmAccelerometerDao;
import com.boost.testaccelerometermap.data.repository.AccelerometerRealmRepositoryImpl;
import com.boost.testaccelerometermap.data.repository.Repository;
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
    public DBDao<AccelerometerData> providesDao(RealmAccelerometerDao realmDao){
        return realmDao;
    }

    @LocationScope
    @Provides
    public Repository<AccelerometerData> provideAccelerometerRepository(AccelerometerRealmRepositoryImpl accelerometerRepository){
        return accelerometerRepository;
    }
}
