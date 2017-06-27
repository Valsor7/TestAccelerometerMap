package com.boost.testaccelerometermap.presentation.presenter.statistics;

import android.util.Log;

import com.boost.testaccelerometermap.data.MyError;
import com.boost.testaccelerometermap.data.repository.RepositoryCallback;
import com.boost.testaccelerometermap.domain.AccelerometerInteractor;
import com.boost.testaccelerometermap.domain.Interactor;
import com.boost.testaccelerometermap.domain.response.Response;
import com.boost.testaccelerometermap.presentation.model.AccelerometerData;
import com.boost.testaccelerometermap.presentation.model.LocationModel;
import com.boost.testaccelerometermap.presentation.model.TimestampInRange;
import com.boost.testaccelerometermap.presentation.view.BaseView;
import com.boost.testaccelerometermap.presentation.view.statistics.StatisticView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by yaroslav on 07.06.17.
 */

public class StatisticPresenterImpl extends DisposableObserver<Response<AccelerometerData>> implements StatisticPresenter {
    private static final String TAG = "StatisticPresenterImpl";

    private StatisticView mStatisticView;
    private Interactor<Response<AccelerometerData>, TimestampInRange> mAccelerometerInteractor;


    @Inject
    public StatisticPresenterImpl(AccelerometerInteractor accelerometerInteractor) {
        mAccelerometerInteractor = accelerometerInteractor;
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
        mAccelerometerInteractor.execute(this, timestampInRange);

                new RepositoryCallback<List<AccelerometerData>>() {
            @Override
            public void onResult(List<AccelerometerData> data) {
                Log.d(TAG, "onAccelerometer data yeah: " + data.size());
                if (mStatisticView == null) return;

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

    @Override
    public void onNext(@NonNull Response response) {
        mStatisticView.onAccelerometerResult(response.dataList);
    }

    @Override
    public void onError(@NonNull Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
