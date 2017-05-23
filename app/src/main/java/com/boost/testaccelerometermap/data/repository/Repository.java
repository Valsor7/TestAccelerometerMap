package com.boost.testaccelerometermap.data.repository;

import com.boost.testaccelerometermap.data.Network;
import com.boost.testaccelerometermap.data.db.DBDao;
import com.boost.testaccelerometermap.data.db.RealmDao;

import javax.inject.Inject;

/**
 * Created by yaroslav on 23.05.17.
 */

public class Repository {

    private final Network mNetwork;
    private final DBDao mDbDao;

    @Inject
    public Repository(Network network) {
        this.mNetwork = network;
        mDbDao = new RealmDao();
    }

    public void getAllData(RepositoryCallback callback){
        if (mNetwork.isNetwork()){

        } else {
            mDbDao.getAllData(callback);
        }
    }
}
