package com.boost.testaccelerometermap.data.repository.specification.accelerometer;


import com.boost.testaccelerometermap.data.repository.specification.Specification;
import com.boost.testaccelerometermap.presentation.model.TimestampInRange;

public interface AccelerometerSpecificationFactory {
    Specification createGetInRange(TimestampInRange timestampInRange);
}
