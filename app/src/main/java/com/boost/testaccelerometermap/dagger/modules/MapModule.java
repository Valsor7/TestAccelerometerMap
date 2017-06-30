package com.boost.testaccelerometermap.dagger.modules;

import android.app.Activity;

import com.boost.testaccelerometermap.dagger.scopes.LocationScope;
import com.boost.testaccelerometermap.data.model.response.SuccessResponse;
import com.boost.testaccelerometermap.data.repository.LocationRepositoryImpl;
import com.boost.testaccelerometermap.data.repository.Repository;
import com.boost.testaccelerometermap.data.repository.specification.location.LocationSpecificationFactory;
import com.boost.testaccelerometermap.data.repository.specification.location.LocationSpecificationFactoryImpl;
import com.boost.testaccelerometermap.domain.interactors.Interactor;
import com.boost.testaccelerometermap.domain.interactors.location.LocationsByDayInteractor;
import com.boost.testaccelerometermap.domain.interactors.location.SaveLoactionInteractor;
import com.boost.testaccelerometermap.domain.interactors.location.UniqueLocationsInteractor;
import com.boost.testaccelerometermap.presentation.model.LocationModel;
import com.boost.testaccelerometermap.presentation.view.map.LocationHelper;

import java.util.List;

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
    public Repository<LocationModel> provideLocationRepository(LocationRepositoryImpl locationRepository){
        return locationRepository;
    }

    @LocationScope
    @Provides
    public LocationSpecificationFactory prvideLocationSpecificationFactory(){
        return new LocationSpecificationFactoryImpl();
    }

    @LocationScope
    @Provides
    public Interactor<List<LocationModel>, Long> prvideLocationsByDayInteractor(
            Repository<LocationModel> locationModelRepository,
            LocationSpecificationFactory locationSpecificationFactory){
        return new LocationsByDayInteractor(locationModelRepository, locationSpecificationFactory);
    }

    @LocationScope
    @Provides
    public Interactor<SuccessResponse, LocationModel> prvideSaveLocationInteractor(Repository<LocationModel> locationModelRepository){
        return new SaveLoactionInteractor(locationModelRepository);
    }

    @LocationScope
    @Provides
    public Interactor<List<LocationModel>, Void> prvideUniqueLocationsInteractor(
            Repository<LocationModel> locationModelRepository,
            LocationSpecificationFactory locationSpecificationFactory){
        return new UniqueLocationsInteractor(locationModelRepository, locationSpecificationFactory);
    }
}
