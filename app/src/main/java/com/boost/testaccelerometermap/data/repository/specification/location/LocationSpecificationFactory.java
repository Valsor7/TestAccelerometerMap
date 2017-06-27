package com.boost.testaccelerometermap.data.repository.specification.location;


import com.boost.testaccelerometermap.data.repository.specification.Specification;

public class LocationSpecificationFactory {

    public Specification createGetUniqueLocations(long id){
        return new LocationSpecificationById(id);
    }

    public Specification createGetUniqueLocations(){
        return new LocationSpecificationUnique();
    }
}
