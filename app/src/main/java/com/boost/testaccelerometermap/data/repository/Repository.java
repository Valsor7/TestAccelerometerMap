package com.boost.testaccelerometermap.data.repository;

import java.util.List;

import javax.security.auth.callback.Callback;

/**
 * Created by yaroslav on 23.05.17.
 */

public abstract class Repository<T> {

    public void get(Object id) {
    }

    public void getAll(RepositoryCallback<List<T>> callback) {
    }

    public void add(T item) {
    }

    public void addAll(List<T> items) {
    }



    public void remove(Object id) {
    }

    public void update(Object id) {
    }
}
