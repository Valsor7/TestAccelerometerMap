package com.boost.testaccelerometermap.presentation.presenter.statistics;

import android.util.Log;

import com.boost.testaccelerometermap.dagger.map.qualifiers.Accelerometer;
import com.boost.testaccelerometermap.dagger.map.qualifiers.Location;
import com.boost.testaccelerometermap.data.MyError;
import com.boost.testaccelerometermap.data.repository.Repository;
import com.boost.testaccelerometermap.data.repository.RepositoryCallback;
import com.boost.testaccelerometermap.presentation.model.AccelerometerData;
import com.boost.testaccelerometermap.presentation.model.LocationModel;
import com.boost.testaccelerometermap.presentation.view.BaseView;
import com.boost.testaccelerometermap.presentation.view.statistics.StatisticView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yaroslav on 07.06.17.
 */

public class StatisticPresenterImpl implements StatisticPresenter {
    private static final String TAG = "StatisticPresenterImpl";

    private StatisticView mStatisticView;
    private Repository<LocationModel> mLocationRepository;
    private Repository<AccelerometerData> mAccelerometerRepository;

    public StatisticPresenterImpl(@Location Repository<LocationModel> locationRepository, @Accelerometer Repository<AccelerometerData> accelerometerRepository) {
        mLocationRepository = locationRepository;
        mAccelerometerRepository = accelerometerRepository;
    }

    @Override
    public void onAttachView(BaseView view) {
        mStatisticView = (StatisticView) view;
        mAccelerometerRepository.getInRange(1497958219483L, 1497958219745L, new RepositoryCallback<List<AccelerometerData>>() {
            @Override
            public void onResult(List<AccelerometerData> data) {
                for (AccelerometerData accelerometerData : data) {
                    Log.d(TAG, "onResult:" + accelerometerData);
                }
            }

            @Override
            public void onError(MyError error) {

            }
        });
    }

    @Override
    public void onDetachView() {

    }

    @Override
    public void getAccelerometerDataInRange(long timestampFrom, long timestampTo) {
        Log.d(TAG, "getAccelerometerDataInRange() called with: timestampFrom = [" + timestampFrom + "], timestampTo = [" + timestampTo + "]");
        mAccelerometerRepository.getInRange(timestampFrom, timestampTo, new RepositoryCallback<List<AccelerometerData>>() {
            @Override
            public void onResult(List<AccelerometerData> data) {
                Log.d(TAG, "onAccelerometer data yeah: " + data.size());
                if (mStatisticView == null) return;
                mStatisticView.onAccelerometerResult(data);
            }

            @Override
            public void onError(MyError error) {

            }
        });
    }

    @Override
    public void getStatistics(){
        mLocationRepository.getAllUnique(new RepositoryCallback<List<LocationModel>>() {
            @Override
            public void onResult(List<LocationModel> data) {
                mStatisticView.onStatisticsByDay(data);
            }

            @Override
            public void onError(MyError error) {

            }
        });
    }

    @Override
    public void getLocations(long dayInMillis) {
        mLocationRepository.getAll(new RepositoryCallback<List<LocationModel>>() {
            @Override
            public void onResult(List<LocationModel> data) {
                mStatisticView.onLocations(new ArrayList<>(data));
            }

            @Override
            public void onError(MyError error) {

            }
        });
    }
}
