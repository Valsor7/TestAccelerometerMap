package com.boost.testaccelerometermap.dagger.map;

import android.app.Activity;

import com.boost.testaccelerometermap.dagger.scopes.LocationScope;
import com.boost.testaccelerometermap.data.hardware.LocationHelper;

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
