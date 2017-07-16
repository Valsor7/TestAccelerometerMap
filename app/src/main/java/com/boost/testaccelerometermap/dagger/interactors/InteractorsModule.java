package com.boost.testaccelerometermap.dagger.interactors;

import com.boost.testaccelerometermap.Constants;
import com.boost.testaccelerometermap.dagger.scopes.DomainScope;
import com.boost.testaccelerometermap.data.hardware.LocationHelper;
import com.boost.testaccelerometermap.data.model.response.SuccessResponse;
import com.boost.testaccelerometermap.domain.Repository;
import com.boost.testaccelerometermap.data.repository.specification.accelerometer.AccelerometerSpecificationFactory;
import com.boost.testaccelerometermap.data.repository.specification.location.LocationSpecificationFactory;
import com.boost.testaccelerometermap.domain.interactors.Interactor;
import com.boost.testaccelerometermap.domain.interactors.accelerometer.AccelerometerGetInRangeInteractor;
import com.boost.testaccelerometermap.domain.interactors.location.FreshLocationInteractor;
import com.boost.testaccelerometermap.domain.interactors.location.LocationsByDayInteractor;
import com.boost.testaccelerometermap.domain.interactors.location.ParseLocationInteractor;
import com.boost.testaccelerometermap.domain.interactors.location.SaveLocationInteractor;
import com.boost.testaccelerometermap.domain.interactors.location.UniqueLocationsInteractor;
import com.boost.testaccelerometermap.data.model.AccelerometerData;
import com.boost.testaccelerometermap.domain.interactors.location.UpdateLocationsInteractor;
import com.boost.testaccelerometermap.domain.locationmappers.LatLangDateToLocationModel;
import com.boost.testaccelerometermap.domain.locationmappers.LocationToLatLangDateMapper;
import com.boost.testaccelerometermap.presentation.model.LatLangDate;
import com.boost.testaccelerometermap.presentation.model.LocationModel;
import com.boost.testaccelerometermap.presentation.model.LocationToLatLngMapper;
import com.boost.testaccelerometermap.presentation.model.TimestampInRange;
import com.boost.testaccelerometermap.utils.RxUtils;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import javax.inject.Named;
import javax.inject.Qualifier;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yaroslav on 02.07.17.
 */
@Module
public class InteractorsModule {

    @DomainScope
    @Provides
    @Named(Constants.QUALIFIER_BY_DAY)
    public Interactor<List<LocationModel>, Long> provideLocationsByDayInteractor(
            Repository<LocationModel> locationModelRepository,
            LocationSpecificationFactory locationSpecificationFactory){
        return new LocationsByDayInteractor(locationModelRepository, locationSpecificationFactory, RxUtils.async());
    }

    @DomainScope
    @Provides
    public Interactor<SuccessResponse, LatLangDate> provideSaveLocationInteractor(Repository<LocationModel> locationModelRepository){
        return new SaveLocationInteractor(locationModelRepository, new LatLangDateToLocationModel());
    }

    @DomainScope
    @Provides
    public Interactor<List<LocationModel>, Void> provideUniqueLocationsInteractor(
            Repository<LocationModel> locationModelRepository,
            LocationSpecificationFactory locationSpecificationFactory){
        return new UniqueLocationsInteractor(locationModelRepository, locationSpecificationFactory);
    }

    @DomainScope
    @Provides
    public Interactor<List<AccelerometerData>, TimestampInRange> provideAccelerometerInRangeInteractor(
            Repository<AccelerometerData> accelerometerRepository,
            AccelerometerSpecificationFactory accelerometerSpecificationFactory) {
        return new AccelerometerGetInRangeInteractor(accelerometerRepository, accelerometerSpecificationFactory);
    }

    @DomainScope
    @Provides
    public Interactor<List<LatLng>, List<LocationModel>> provideParseLocationInteractor() {
        return new ParseLocationInteractor(new LocationToLatLngMapper(), RxUtils.async());
    }

    @DomainScope
    @Provides
    @Named(Constants.QUALIFIER_FRESH)
    public Interactor<List<LocationModel>,Long> provideFreshLocationsInteractor(Repository<LocationModel> locationRepository,
                                                                         LocationSpecificationFactory locationSpecificationFactory) {

        return new FreshLocationInteractor(locationRepository, locationSpecificationFactory, RxUtils.onUI());
    }
}
