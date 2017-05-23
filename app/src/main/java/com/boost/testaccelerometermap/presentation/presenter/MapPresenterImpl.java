package com.boost.testaccelerometermap.presentation.presenter;

import com.boost.testaccelerometermap.data.repository.Repository;
import com.boost.testaccelerometermap.data.repository.RepositoryCallback;
import com.boost.testaccelerometermap.presentation.model.Marker;
import com.boost.testaccelerometermap.presentation.view.BaseView;
import com.boost.testaccelerometermap.presentation.view.map.MapView;

import java.util.List;

/**
 * Created by yaroslav on 23.05.17.
 */

public class MapPresenterImpl implements MapPresenter {

    Repository repository;
    private MapView mMapView;

    @Override
    public void getAllData(){
        repository.getAllData(new RepositoryCallback<List<Marker>>() {
            @Override
            public void onResult(List<Marker> data) {
                
            }

            @Override
            public void onError(Error error) {

            }
        });
    }

    @Override
    public void onAttachView(BaseView view) {
        mMapView = (MapView) view;
    }

    @Override
    public void onDetachView() {
        mMapView = null;
    }
}
