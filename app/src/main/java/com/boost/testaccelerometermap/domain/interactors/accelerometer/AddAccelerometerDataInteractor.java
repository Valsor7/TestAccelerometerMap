package com.boost.testaccelerometermap.domain.interactors.accelerometer;

import com.boost.testaccelerometermap.data.model.response.SuccessResponse;
import com.boost.testaccelerometermap.domain.Repository;
import com.boost.testaccelerometermap.domain.interactors.Interactor;
import com.boost.testaccelerometermap.data.model.AccelerometerData;

import io.reactivex.Observable;

/**
 * Created by yaroslav on 28.06.17.
 */

public class AddAccelerometerDataInteractor extends Interactor<SuccessResponse, AccelerometerData> {

    private Repository<AccelerometerData> mAccelerometerDataRepository;

    public AddAccelerometerDataInteractor(Repository<AccelerometerData> accelerometerDataRepository) {
        mAccelerometerDataRepository = accelerometerDataRepository;
    }

    @Override
    public Observable<SuccessResponse> execute(AccelerometerData requestModel) {
        return mAccelerometerDataRepository.add(requestModel);
    }
}
