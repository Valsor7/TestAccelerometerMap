package com.boost.testaccelerometermap.data.db;

import com.boost.testaccelerometermap.data.DataSource;
import com.boost.testaccelerometermap.data.repository.RepositoryCallback;

import java.util.List;

/**
 * Created by yaroslav on 23.05.17.
 */

public interface DBDao<T> {
    void getAllData(RepositoryCallback<List<T>> callback);
    void getAllUnique(RepositoryCallback<List<T>> callback);
    void saveAll(List<T> items);
    void save(T item);
}
