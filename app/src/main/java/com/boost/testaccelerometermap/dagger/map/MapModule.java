package com.boost.testaccelerometermap.dagger.map;

import com.boost.testaccelerometermap.data.db.DBDao;
import com.boost.testaccelerometermap.data.db.realm.RealmAccelerometerDao;
import com.boost.testaccelerometermap.data.repository.AccelerometerRepositoryImpl;
import com.boost.testaccelerometermap.data.repository.Repository;
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
    public DBDao providesDao(RealmAccelerometerDao realmDao){
        return realmDao;
    }

    @MapScope
    @Provides
    public Repository providesRepository(AccelerometerRepositoryImpl mapRepository){
        return mapRepository;
    }
}
