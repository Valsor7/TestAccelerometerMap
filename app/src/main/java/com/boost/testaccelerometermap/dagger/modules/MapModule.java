package com.boost.testaccelerometermap.dagger.modules;

import android.app.Activity;

import com.boost.testaccelerometermap.dagger.scopes.LocationScope;
import com.boost.testaccelerometermap.data.repository.LocationRepositoryImpl;
import com.boost.testaccelerometermap.data.repository.AccelerometerRepository;
import com.boost.testaccelerometermap.data.repository.specification.location.LocationSpecificationFactory;
import com.boost.testaccelerometermap.data.repository.specification.location.LocationSpecificationFactoryImpl;
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
    public LocationHelper provideLocationHelper(){
        return new LocationHelper(mActivity);
    }

    @LocationScope
    @Provides
    public AccelerometerRepository<LocationModel> provideLocationRepository(LocationRepositoryImpl locationRepository){
        return locationRepository;
    }

    @LocationScope
    @Provides
    public LocationSpecificationFactory prvideLocationSpecificationFactory(){
        return new LocationSpecificationFactoryImpl();
    }
}
