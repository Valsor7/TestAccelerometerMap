package com.boost.testaccelerometermap.data.repository;

import com.boost.testaccelerometermap.data.repository.specification.Specification;

import java.util.List;

/**
 * Created by yaroslav on 23.05.17.
 */

public interface Repository<T> {

    public void add(T item);

    public void addAll(List<T> items);

    public void remove(T item);

    public void update(T item);

    public void getAll(RepositoryCallback<List<T>> callback);

    public void query(Specification specification, RepositoryCallback<List<T>> callback);
}
