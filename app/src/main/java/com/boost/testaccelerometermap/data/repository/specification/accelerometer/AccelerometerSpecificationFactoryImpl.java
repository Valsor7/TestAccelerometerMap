package com.boost.testaccelerometermap.data.repository.specification.accelerometer;

import com.boost.testaccelerometermap.data.repository.specification.Specification;
import com.boost.testaccelerometermap.presentation.model.TimestampInRange;

public class AccelerometerSpecificationFactoryImpl implements AccelerometerSpecificationFactory{

    @Override
    public Specification createGetInRange(TimestampInRange timestampInRange){
        return new GetInRangeSpecification(timestampInRange);
    }
}
