package com.boost.testaccelerometermap.data.repository.specification.location;


import com.boost.testaccelerometermap.data.repository.specification.Specification;

public interface LocationSpecificationFactory {

    Specification createGetLocationByDay(long day);

    Specification createGetUniqueLocations();

    Specification createGetFreshLocations(long timestamp);
}
