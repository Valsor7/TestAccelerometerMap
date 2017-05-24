package com.boost.testaccelerometermap.data.db;

import com.boost.testaccelerometermap.data.repository.RepositoryCallback;

import javax.inject.Inject;

/**
 * Created by yaroslav on 23.05.17.
 */

public class RealmDao implements DBDao<String>{

    @Inject
    public RealmDao() {
    }

    @Override
    public void getAllData(RepositoryCallback<String> callback){
        callback.onResult("hello");
    }
}
