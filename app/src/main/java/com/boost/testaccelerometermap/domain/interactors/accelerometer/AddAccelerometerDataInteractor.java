package com.boost.testaccelerometermap.domain.interactors.accelerometer;

import com.boost.testaccelerometermap.data.repository.Repository;
import com.boost.testaccelerometermap.domain.interactors.Interactor;
import com.boost.testaccelerometermap.presentation.model.AccelerometerData;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by yaroslav on 28.06.17.
 */

public class AddAccelerometerDataInteractor extends Interactor<AccelerometerData,AccelerometerData> {

    private Repository<AccelerometerData> mAccelerometerDataRepository;

    public AddAccelerometerDataInteractor(Repository<AccelerometerData> accelerometerDataRepository) {
        mAccelerometerDataRepository = accelerometerDataRepository;
    }

    @Override
    protected Observable<AccelerometerData> buildObservable(AccelerometerData requestModel) {
        return mAccelerometerDataRepository.add(requestModel);
    }
}
