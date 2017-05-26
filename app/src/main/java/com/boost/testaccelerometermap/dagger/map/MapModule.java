package com.boost.testaccelerometermap.dagger.map;

import android.app.Activity;

import com.boost.testaccelerometermap.data.db.DBDao;
import com.boost.testaccelerometermap.data.db.RealmDao;
import com.boost.testaccelerometermap.data.repository.MapRepository;
import com.boost.testaccelerometermap.data.repository.Repository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yaroslav on 24.05.17.
 */
@Module
public class MapModule {

    @MapScope
    @Provides
    public DBDao providesDao(RealmDao realmDao){
        return realmDao;
    }

    @MapScope
    @Provides
    public Repository providesRepository(MapRepository mapRepository){
        return mapRepository;
    }
}
