package com.boost.testaccelerometermap.data.repository.specification;


import io.realm.Realm;

public interface RealmSpecification<R> extends Specification {
    public R query(Realm realm);
}
