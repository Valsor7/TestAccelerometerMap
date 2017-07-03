package com.boost.testaccelerometermap.domain.interactors.location;


import com.boost.testaccelerometermap.domain.interactors.Interactor;
import com.boost.testaccelerometermap.presentation.model.LocationModel;
import com.boost.testaccelerometermap.presentation.model.LocationToLatLngMapper;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;

public class ParseLocationInteractor extends Interactor<List<LatLng>, List<LocationModel>> {
    private LocationToLatLngMapper mLocationToLatLngMapper;
    private ObservableTransformer<List<LatLng>, List<LatLng>> mAsyncTransformer;

    public ParseLocationInteractor(LocationToLatLngMapper locationToLatLngMapper, ObservableTransformer<List<LatLng>, List<LatLng>> asyncTransformer) {
        mLocationToLatLngMapper = locationToLatLngMapper;
        mAsyncTransformer = asyncTransformer;
    }

    @Override
    public Observable<List<LatLng>> execute(List<LocationModel> requestModel) {
        return Observable.fromIterable(requestModel)
                .map(mLocationToLatLngMapper::map)
                .toList()
                .toObservable()
                .compose(mAsyncTransformer);
    }
}
