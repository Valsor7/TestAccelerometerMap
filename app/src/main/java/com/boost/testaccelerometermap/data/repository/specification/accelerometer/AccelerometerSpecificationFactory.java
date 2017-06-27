package com.boost.testaccelerometermap.data.repository.specification.accelerometer;

import com.boost.testaccelerometermap.data.repository.specification.Specification;
import com.boost.testaccelerometermap.presentation.model.TimestampInRange;

// TODO: 27.06.17 think if new db type is added
public class AccelerometerSpecificationFactory {

    public Specification createGetInRange(TimestampInRange timestampInRange){
        return new GetInRangeSpecification(timestampInRange);
    }
}
