package com.boost.testaccelerometermap.dagger;

import android.app.Activity;

import com.boost.testaccelerometermap.data.db.DBDao;
import com.boost.testaccelerometermap.data.db.RealmDao;
import com.boost.testaccelerometermap.data.repository.MapRepository;
import com.boost.testaccelerometermap.data.repository.Repository;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;

/**
 * Created by yaroslav on 24.05.17.
 */
@Module
public class MapModule {
    private Activity mActivity;

    public MapModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    @MapScope
    public Activity providesActivity(){
        return mActivity;
    }

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
