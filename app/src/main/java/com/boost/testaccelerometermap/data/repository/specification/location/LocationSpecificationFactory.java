package com.boost.testaccelerometermap.data.repository.specification.location;


import com.boost.testaccelerometermap.data.repository.specification.Specification;

public interface LocationSpecificationFactory {

    Specification createGetLocationById(long id);

    Specification createGetUniqueLocations();
}
