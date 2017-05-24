package com.boost.testaccelerometermap.data.repository;

import javax.security.auth.callback.Callback;

/**
 * Created by yaroslav on 23.05.17.
 */

public abstract class Repository<T> {

    public void get(Object id) {
    }

    public void getAll(RepositoryCallback<T> callback) {
    }

    public void add(Object item) {
    }

    public void remove(Object id) {
    }

    public void update(Object id) {
    }
}
