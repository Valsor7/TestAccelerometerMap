package com.boost.testaccelerometermap.presentation.presenter.statistics;

import android.util.Log;

import com.boost.testaccelerometermap.data.repository.Repository;
import com.boost.testaccelerometermap.data.repository.RepositoryCallback;
import com.boost.testaccelerometermap.presentation.model.LocationModel;
import com.boost.testaccelerometermap.presentation.view.BaseView;
import com.boost.testaccelerometermap.presentation.view.statistics.StatisticView;

import java.util.List;

/**
 * Created by yaroslav on 07.06.17.
 */

public class StatisticPresenterImpl implements StatisticPresenter {
    private static final String TAG = "StatisticPresenterImpl";

    StatisticView mStatisticView;
    Repository<LocationModel> mLocationRepository;

    public StatisticPresenterImpl(Repository<LocationModel> locationRepository) {
        mLocationRepository = locationRepository;
    }

    @Override
    public void onAttachView(BaseView view) {
        mStatisticView = (StatisticView) view;
    }

    @Override
    public void onDetachView() {

    }

    @Override
    public void getStatistics(){
        mLocationRepository.getAllUnique(new RepositoryCallback<List<LocationModel>>() {
            @Override
            public void onResult(List<LocationModel> data) {
                Log.d(TAG, "onResult: " + data);
            }

            @Override
            public void onError(Error error) {

            }
        });
    }
}
