package com.boost.testaccelerometermap.data.repository;

import com.boost.testaccelerometermap.data.MyError;

/**
 * Created by yaroslav on 23.05.17.
 */

public interface RepositoryCallback<T> {
    public void onResult(T data);
    public void onError(MyError error);
}
