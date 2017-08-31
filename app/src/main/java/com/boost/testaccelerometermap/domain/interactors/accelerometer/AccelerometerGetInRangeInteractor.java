package com.boost.testaccelerometermap.domain.interactors.accelerometer;


import com.boost.testaccelerometermap.domain.Repository;
import com.boost.testaccelerometermap.data.repository.specification.accelerometer.AccelerometerSpecificationFactory;
import com.boost.testaccelerometermap.domain.interactors.Interactor;
import com.boost.testaccelerometermap.data.model.AccelerometerData;
import com.boost.testaccelerometermap.presentation.model.TimestampInRange;

import java.util.List;

import io.reactivex.Observable;

public class AccelerometerGetInRangeInteractor extends Interactor<List<AccelerometerData>, TimestampInRange> {

    private Repository<AccelerometerData> mAccelerometerRepository;
    private AccelerometerSpecificationFactory mAccelerometerSpecificationFactory;

    public AccelerometerGetInRangeInteractor(Repository<AccelerometerData> accelerometerRepository,
                                             AccelerometerSpecificationFactory accelerometerSpecificationFactory) {
        mAccelerometerRepository = accelerometerRepository;
        mAccelerometerSpecificationFactory = accelerometerSpecificationFactory;
    }

    @Override
    public Observable<List<AccelerometerData>> execute(TimestampInRange requestModel) {
        return mAccelerometerRepository.query(mAccelerometerSpecificationFactory.createGetInRange(requestModel));
    }
}
