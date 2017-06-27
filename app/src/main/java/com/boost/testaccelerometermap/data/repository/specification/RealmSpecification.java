package com.boost.testaccelerometermap.data.repository.specification;


public interface RealmSpecification<R> extends Specification {
    public R query();
}
