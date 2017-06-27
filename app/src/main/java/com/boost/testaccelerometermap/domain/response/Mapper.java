package com.boost.testaccelerometermap.domain.response;


public interface Mapper<From, To> {
    public To map(From from);
}
