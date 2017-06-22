package com.boost.testaccelerometermap.dagger.modules;

import android.app.Activity;

import com.boost.testaccelerometermap.dagger.scopes.LocationScope;
import com.boost.testaccelerometermap.data.db.DBDao;
import com.boost.testaccelerometermap.data.db.realm.RealmLocationDao;
import com.boost.testaccelerometermap.data.repository.LocationRepositoryImpl;
import com.boost.testaccelerometermap.data.repository.Repository;
import com.boost.testaccelerometermap.presentation.model.LocationModel;
import com.boost.testaccelerometermap.presentation.view.map.LocationHelper;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yaroslav on 24.05.17.
 */
@Module
public class MapModule {

    private Activity mActivity;

    public MapModule(Activity activity) {
        mActivity = activity;
    }

    @LocationScope
    @Provides
    public LocationHelper providesLocationHelper(){
        return new LocationHelper(mActivity);
    }

    @LocationScope
    @Provides
    public DBDao<LocationModel> providesLocationDao(RealmLocationDao realmDao){
        return realmDao;
    }

    @LocationScope
    @Provides
    public Repository<LocationModel> providesLocationRepository(LocationRepositoryImpl locationRepository){
        return locationRepository;
    }
}
