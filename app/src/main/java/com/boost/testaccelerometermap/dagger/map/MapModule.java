package com.boost.testaccelerometermap.dagger.map;

import android.app.Activity;

import com.boost.testaccelerometermap.dagger.scopes.LocationScope;
import com.boost.testaccelerometermap.data.repository.LocationRepositoryImpl;
import com.boost.testaccelerometermap.data.repository.Repository;
import com.boost.testaccelerometermap.data.repository.specification.location.LocationSpecificationFactory;
import com.boost.testaccelerometermap.data.repository.specification.location.LocationSpecificationFactoryImpl;
import com.boost.testaccelerometermap.presentation.model.LocationModel;
import com.boost.testaccelerometermap.presentation.view.map.LocationHelper;

import javax.inject.Singleton;

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
    public LocationHelper provideLocationHelper(){
        return new LocationHelper(mActivity);
    }


}
