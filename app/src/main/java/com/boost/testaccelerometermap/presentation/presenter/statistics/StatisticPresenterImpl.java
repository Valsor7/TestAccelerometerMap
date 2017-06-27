package com.boost.testaccelerometermap.presentation.presenter.statistics;

import android.util.Log;

import com.boost.testaccelerometermap.data.MyError;
import com.boost.testaccelerometermap.data.repository.Repository;
import com.boost.testaccelerometermap.data.repository.RepositoryCallback;
import com.boost.testaccelerometermap.data.repository.specification.accelerometer.AccelerometerSpecificationFactory;
import com.boost.testaccelerometermap.data.repository.specification.accelerometer.AccelerometerSpecificationFactoryImpl;
import com.boost.testaccelerometermap.data.repository.specification.location.LocationSpecificationFactory;
import com.boost.testaccelerometermap.data.repository.specification.location.LocationSpecificationFactoryImpl;
import com.boost.testaccelerometermap.presentation.model.AccelerometerData;
import com.boost.testaccelerometermap.presentation.model.LocationModel;
import com.boost.testaccelerometermap.presentation.model.TimestampInRange;
import com.boost.testaccelerometermap.presentation.view.BaseView;
import com.boost.testaccelerometermap.presentation.view.statistics.StatisticView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by yaroslav on 07.06.17.
 */

public class StatisticPresenterImpl implements StatisticPresenter {
    private static final String TAG = "StatisticPresenterImpl";

    private StatisticView mStatisticView;
    private Repository<LocationModel> mLocationRepository;
    private Repository<AccelerometerData> mAccelerometerRepository;
    private LocationSpecificationFactory mLocationSpecificationFactory;
    private AccelerometerSpecificationFactory mAccelerometerSpecificationFactory;

    @Inject
    public StatisticPresenterImpl(Repository<LocationModel> locationRepository,
                                  Repository<AccelerometerData> accelerometerRepository,
                                  LocationSpecificationFactory locationSpecificationFactory,
                                  AccelerometerSpecificationFactory accelerometerSpecificationFactory) {

        mLocationRepository = locationRepository;
        mAccelerometerRepository = accelerometerRepository;
        mLocationSpecificationFactory = locationSpecificationFactory;
        mAccelerometerSpecificationFactory = accelerometerSpecificationFactory;
    }

    @Override
    public void onAttachView(BaseView view) {
        mStatisticView = (StatisticView) view;
    }

    @Override
    public void onDetachView() {
        mStatisticView = null;
    }


    @Override
    public void getAccelerometerDataInRange(TimestampInRange timestampInRange) {
        Log.d(TAG, "getAccelerometerDataInRange() called with: timestampInRange = [" + timestampInRange + "]");
        mAccelerometerRepository.query(mAccelerometerSpecificationFactory.createGetInRange(timestampInRange),
                new RepositoryCallback<List<AccelerometerData>>() {
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
        Log.d(TAG, "getStatistics: ");
        mLocationRepository.query(mLocationSpecificationFactory.createGetUniqueLocations(),
                new RepositoryCallback<List<LocationModel>>() {
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
        Log.d(TAG, "getLocations: " + dayInMillis);
        mLocationRepository.query(mLocationSpecificationFactory.createGetLocationById(dayInMillis),
                new RepositoryCallback<List<LocationModel>>() {
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
