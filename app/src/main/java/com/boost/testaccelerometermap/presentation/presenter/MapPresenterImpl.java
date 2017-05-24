package com.boost.testaccelerometermap.presentation.presenter;

import com.boost.testaccelerometermap.data.repository.Repository;
import com.boost.testaccelerometermap.data.repository.RepositoryCallback;
import com.boost.testaccelerometermap.presentation.view.BaseView;
import com.boost.testaccelerometermap.presentation.view.map.GoogleMapView;

import java.util.Collections;

import javax.inject.Inject;

/**
 * Created by yaroslav on 23.05.17.
 */

public class MapPresenterImpl implements MapPresenter {

    Repository mRepository;
    private GoogleMapView mMapView;

    @Inject
    public MapPresenterImpl(Repository repository) {
        mRepository = repository;
    }

    @Override
    public void getAllData(){
        mRepository.getAll(new RepositoryCallback<String>() {
            @Override
            public void onResult(String data) {
                mMapView.showAll(Collections.singletonList(data));
            }

            @Override
            public void onError(Error error) {

            }
        });
    }

    @Override
    public void onAttachView(BaseView view) {
        mMapView = (GoogleMapView) view;
    }

    @Override
    public void onDetachView() {
        mMapView = null;
    }
}
