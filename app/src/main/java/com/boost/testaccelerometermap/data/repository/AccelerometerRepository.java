package com.boost.testaccelerometermap.data.repository;

import com.boost.testaccelerometermap.data.repository.specification.Specification;
import com.boost.testaccelerometermap.domain.response.Response;
import com.boost.testaccelerometermap.presentation.model.AccelerometerData;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.realm.RealmModel;
import io.realm.RealmResults;


/**
 * Created by yaroslav on 23.05.17.
 */

public interface AccelerometerRepository {

    public void add(AccelerometerData item);

    public void addAll(List<T> items);

    public void remove(T item);

    public void update(T item);

    public Observable<List<T>> getAll();

    public Observable<Response<T>> query(Specification specification);
}
