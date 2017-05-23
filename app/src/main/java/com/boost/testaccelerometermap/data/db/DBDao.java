package com.boost.testaccelerometermap.data.db;

import com.boost.testaccelerometermap.data.DataSource;
import com.boost.testaccelerometermap.data.repository.RepositoryCallback;

/**
 * Created by yaroslav on 23.05.17.
 */

public interface DBDao {
    void getAllData(RepositoryCallback callback);
}
