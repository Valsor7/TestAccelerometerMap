package com.boost.testaccelerometermap.domain;

import com.boost.testaccelerometermap.data.model.response.SuccessResponse;
import com.boost.testaccelerometermap.data.repository.specification.Specification;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by yaroslav on 23.05.17.
 */

public interface Repository<T> {

    public Observable<SuccessResponse> add(T item);

    public Observable<SuccessResponse> addAll(List<T> items);

    public Observable<T> remove(T item);

    public Observable<T> update(T item);

    public Observable<List<T>> getAll();

    public Observable<List<T>> query(Specification specification);
}
