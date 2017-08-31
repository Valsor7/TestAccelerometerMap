package com.boost.testaccelerometermap.domain;


public interface Mapper<From, To> {
    public To map(From from);
}
