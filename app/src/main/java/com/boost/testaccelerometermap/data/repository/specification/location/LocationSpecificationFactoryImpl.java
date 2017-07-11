package com.boost.testaccelerometermap.data.repository.specification.location;


import com.boost.testaccelerometermap.data.repository.specification.Specification;

public class LocationSpecificationFactoryImpl implements LocationSpecificationFactory{

    @Override
    public Specification createGetLocationByDay(long day){
        return new LocationSpecificationById(day);
    }

    @Override
    public Specification createGetUniqueLocations(){
        return new LocationSpecificationUnique();
    }

    @Override
    public Specification createGetFreshLocations(long timestamp){
        return new LocationSpecificationGetFreshLocation(timestamp);
    }
}
