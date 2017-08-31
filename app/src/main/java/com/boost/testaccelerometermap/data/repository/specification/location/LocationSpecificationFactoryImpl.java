package com.boost.testaccelerometermap.data.repository.specification.location;


import com.boost.testaccelerometermap.data.repository.specification.Specification;

public class LocationSpecificationFactoryImpl implements LocationSpecificationFactory{

    @Override
    public Specification createGetLocationById(long id){
        return new LocationSpecificationById(id);
    }

    @Override
    public Specification createGetUniqueLocations(){
        return new LocationSpecificationUnique();
    }
}
