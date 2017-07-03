package com.boost.testaccelerometermap.presentation.presenter.statistics;

import android.util.Log;

import com.boost.testaccelerometermap.data.model.Location;
import com.boost.testaccelerometermap.domain.interactors.Interactor;
import com.boost.testaccelerometermap.data.model.AccelerometerData;
import com.boost.testaccelerometermap.presentation.model.TimestampInRange;
import com.boost.testaccelerometermap.presentation.view.BaseView;
import com.boost.testaccelerometermap.presentation.view.statistics.StatisticView;
import com.boost.testaccelerometermap.utils.SimpleObserver;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by yaroslav on 07.06.17.
 */

public class StatisticPresenterImpl implements StatisticPresenter {
    private static final String TAG = "StatisticPresenterImpl";
    private StatisticView mStatisticView;
    private List<Interactor> mInteractors = new ArrayList<>();
    private Interactor<List<AccelerometerData>, TimestampInRange> mAccelerometerInteractor;
    private Interactor<List<Location>, Long> mLocationByDayInteractor;
    private Interactor<List<Location>, Void> mUniqueLocationInteractor;
    private CompositeDisposable mDisposables;

    @Inject
    public StatisticPresenterImpl(Interactor<List<AccelerometerData>, TimestampInRange> accelerometerInteractor,
                                  Interactor<List<Location>, Long> locationByDayInteractor,
                                  Interactor<List<Location>, Void> uniqueLocationsInteractor) {
        mAccelerometerInteractor = accelerometerInteractor;
        mUniqueLocationInteractor = uniqueLocationsInteractor;
        mLocationByDayInteractor = locationByDayInteractor;
    }

    @Override
    public void onAttachView(BaseView view) {
        mStatisticView = (StatisticView) view;
    }

    @Override
    public void getAccelerometerDataInRange(TimestampInRange timestampInRange) {
        Log.d(TAG, "getAccelerometerDataInRange() called with: timestampInRange = [" + timestampInRange + "]");
        Disposable disposable = mAccelerometerInteractor.execute(timestampInRange)
                .subscribe(mStatisticView::onAccelerometerResult, mStatisticView::onError);
        mDisposables.add(disposable);
    }

    @Override
    public void getStatistics(){
        Log.d(TAG, "getStatistics: ");
        Disposable disposable  = mUniqueLocationInteractor.execute(null)
                .subscribe(mStatisticView::onStatisticsByDay, mStatisticView::onError);
        mDisposables.add(disposable);
    }

    @Override
    public void getLocations(long dayInMillis) {
        Log.d(TAG, "getLocations: " + dayInMillis);
        Disposable disposable = mLocationByDayInteractor.execute(dayInMillis)
                .subscribe(mStatisticView::onLocations, mStatisticView::onError);
        mDisposables.add(disposable);
    }

    @Override
    public void onDetachView() {
        mStatisticView = null;
        dispose();
    }

    private void dispose() {
        if (!mDisposables.isDisposed()) {
            mDisposables.dispose();
        }
    }
}
