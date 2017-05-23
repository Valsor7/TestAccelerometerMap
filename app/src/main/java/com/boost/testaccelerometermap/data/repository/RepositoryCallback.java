package com.boost.testaccelerometermap.data.repository;

/**
 * Created by yaroslav on 23.05.17.
 */

public interface RepositoryCallback<T> {
    public void onResult(T data);
    public void onError(Error error);
}
