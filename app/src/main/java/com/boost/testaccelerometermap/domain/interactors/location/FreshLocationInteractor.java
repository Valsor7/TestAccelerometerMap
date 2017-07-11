package com.boost.testaccelerometermap.domain.interactors.location;

import com.boost.testaccelerometermap.data.repository.specification.location.LocationSpecificationFactory;
import com.boost.testaccelerometermap.domain.Repository;
import com.boost.testaccelerometermap.domain.interactors.Interactor;
import com.boost.testaccelerometermap.presentation.model.LocationModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;

/**
 * Created by yaroslav on 11.07.17.
 */

public class FreshLocationInteractor extends Interactor<List<LocationModel>,Long> {

    private Repository<LocationModel> mLocationModelRepository;
    private LocationSpecificationFactory mLocationSpecificationFactory;
    private ObservableTransformer<List<LocationModel>, List<LocationModel>> mAsyncTransformer;

    public FreshLocationInteractor(Repository<LocationModel> locationModelRepository,
                                    LocationSpecificationFactory factory,
                                    ObservableTransformer<List<LocationModel>, List<LocationModel>> asyncTransformer) {
        mLocationModelRepository = locationModelRepository;
        mLocationSpecificationFactory = factory;
        mAsyncTransformer = asyncTransformer;
    }
    @Override
    public Observable<List<LocationModel>> execute(Long requestModel) {
        return mLocationModelRepository.query(mLocationSpecificationFactory.createGetFreshLocations(requestModel));
    }
}
