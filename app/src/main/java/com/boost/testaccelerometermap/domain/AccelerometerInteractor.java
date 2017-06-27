package com.boost.testaccelerometermap.domain;


import com.boost.testaccelerometermap.data.repository.AccelerometerRepository;
import com.boost.testaccelerometermap.data.repository.specification.accelerometer.AccelerometerSpecificationFactory;
import com.boost.testaccelerometermap.domain.response.Response;
import com.boost.testaccelerometermap.presentation.model.AccelerometerData;
import com.boost.testaccelerometermap.presentation.model.TimestampInRange;

import io.reactivex.Observable;

public class AccelerometerInteractor extends Interactor<Response<AccelerometerData>, TimestampInRange>{

    private AccelerometerRepository<AccelerometerData> mAccelerometerRepository;
    private AccelerometerSpecificationFactory mAccelerometerSpecificationFactory;

    public AccelerometerInteractor(AccelerometerRepository<AccelerometerData> accelerometerRepository,
                                   AccelerometerSpecificationFactory accelerometerSpecificationFactory) {

        mAccelerometerRepository = accelerometerRepository;
        mAccelerometerSpecificationFactory = accelerometerSpecificationFactory;
    }

    @Override
    Observable<Response<AccelerometerData>> buildObservable(TimestampInRange timestampInRange) {
        return mAccelerometerRepository.query(mAccelerometerSpecificationFactory.createGetInRange(timestampInRange));
    }
}
