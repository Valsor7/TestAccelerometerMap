package com.boost.testaccelerometermap.dagger.map;

import android.app.Activity;

import com.boost.testaccelerometermap.data.db.DBDao;
import com.boost.testaccelerometermap.data.db.RealmDao;
import com.boost.testaccelerometermap.data.repository.MapRepository;
import com.boost.testaccelerometermap.data.repository.Repository;
import com.boost.testaccelerometermap.presentation.view.map.LocationHelper;
import com.boost.testaccelerometermap.presentation.view.map.MapFragment;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

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
    public GoogleApiClient providesGApiClient(){
        return new GoogleApiClient.Builder(mFragment.getActivity())
                .addApi(LocationServices.API)
                .addConnectionCallbacks(mFragment)
                .addOnConnectionFailedListener(mFragment)
                .build();
    }

    @MapScope
    @Provides
    public LocationHelper providesLocationHelper(GoogleApiClient client){
        return new LocationHelper(client, mFragment.getActivity());
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
