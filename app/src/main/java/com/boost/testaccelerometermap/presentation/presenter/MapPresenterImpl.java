package com.boost.testaccelerometermap.presentation.presenter;

import android.util.Log;

import com.boost.testaccelerometermap.data.repository.Repository;
import com.boost.testaccelerometermap.data.repository.RepositoryCallback;
import com.boost.testaccelerometermap.presentation.model.AccelerometerData;
import com.boost.testaccelerometermap.presentation.view.BaseView;
import com.boost.testaccelerometermap.presentation.view.map.GoogleMapView;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by yaroslav on 23.05.17.
 */

public class MapPresenterImpl implements MapPresenter {
    private static final String TAG = "MapPresenterImpl";
    Repository<AccelerometerData> mRepository;
    private GoogleMapView mMapView;

    @Inject
    public MapPresenterImpl(Repository repository) {
        mRepository = repository;
    }

    @Override
    public void getAllData(){
        mRepository.getAll(new RepositoryCallback<List<AccelerometerData>>() {
            @Override
            public void onResult(List data) {
                Log.d(TAG, "onResult: " + data.size());
                mMapView.showAll(data);
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
