package com.boost.testaccelerometermap.presentation.presenter.statistics;

import android.util.Log;

import com.boost.testaccelerometermap.domain.interactors.Interactor;
import com.boost.testaccelerometermap.presentation.model.AccelerometerData;
import com.boost.testaccelerometermap.presentation.model.LocationModel;
import com.boost.testaccelerometermap.presentation.model.TimestampInRange;
import com.boost.testaccelerometermap.presentation.view.BaseView;
import com.boost.testaccelerometermap.presentation.view.statistics.StatisticView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by yaroslav on 07.06.17.
 */

public class StatisticPresenterImpl implements StatisticPresenter {
    private static final String TAG = "StatisticPresenterImpl";
    private StatisticView mStatisticView;

    private Interactor<List<AccelerometerData>, TimestampInRange> mAccelerometerInteractor;
    private Interactor<List<LocationModel>, Long> mLocationByDayInteractor;
    private Interactor<List<LocationModel>, Void> mUniqueLocationInteractor;


    @Inject
    public StatisticPresenterImpl(Interactor<List<AccelerometerData>, TimestampInRange> accelerometerInteractor,
                                  Interactor<List<LocationModel>, Long> locationByDayInteractor,
                                  Interactor<List<LocationModel>, Void> uniqueLocationsInteractor) {
        mAccelerometerInteractor = accelerometerInteractor;
        mUniqueLocationInteractor = uniqueLocationsInteractor;
        mLocationByDayInteractor = locationByDayInteractor;
    }

    @Override
    public void onAttachView(BaseView view) {
        mStatisticView = (StatisticView) view;
    }

    @Override
    public void onDetachView() {
        mStatisticView = null;
        // TODO: 29.06.17 dispose interactors
    }


    @Override
    public void getAccelerometerDataInRange(TimestampInRange timestampInRange) {
        Log.d(TAG, "getAccelerometerDataInRange() called with: timestampInRange = [" + timestampInRange + "]");
        mAccelerometerInteractor.execute(new DisposableObserver<List<AccelerometerData>>() {
            @Override
            public void onNext(@NonNull List<AccelerometerData> accelerometerList) {
                Log.d(TAG, "onNext: size " + accelerometerList.size());
                mStatisticView.onAccelerometerResult(accelerometerList);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        }, timestampInRange);
    }

    @Override
    public void getStatistics(){
        Log.d(TAG, "getStatistics: ");
        mUniqueLocationInteractor.execute(new DisposableObserver<List<LocationModel>>() {
            @Override
            public void onNext(@NonNull List<LocationModel> locationModels) {
                Log.d(TAG, "onNext: ");
                mStatisticView.onStatisticsByDay(locationModels);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        }, null);
    }

    @Override
    public void getLocations(long dayInMillis) {
        Log.d(TAG, "getLocations: " + dayInMillis);
        mLocationByDayInteractor.execute(new DisposableObserver<List<LocationModel>>() {
            @Override
            public void onNext(@NonNull List<LocationModel> locationModels) {
                mStatisticView.onLocations(locationModels);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        }, dayInMillis);
    }
}
