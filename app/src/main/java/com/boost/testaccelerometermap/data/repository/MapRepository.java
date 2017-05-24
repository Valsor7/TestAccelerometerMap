package com.boost.testaccelerometermap.data.repository;

import com.boost.testaccelerometermap.data.Network;
import com.boost.testaccelerometermap.data.db.DBDao;

import javax.inject.Inject;

/**
 * Created by yaroslav on 24.05.17.
 */

public class MapRepository extends Repository<String> {
    private final Network mNetwork;
    private final DBDao<String> mDbDao;

    @Inject
    public MapRepository(Network network, DBDao dbDao) {
        this.mNetwork = network;
        mDbDao = dbDao;
    }

    @Override
    public void get(Object id) {

    }

    @Override
    public void getAll(RepositoryCallback<String> callback) {
        if (mNetwork.isNetwork()){

        } else {
            mDbDao.getAllData(callback);
        }
    }

    @Override
    public void add(Object item) {

    }

    @Override
    public void remove(Object id) {

    }

    @Override
    public void update(Object id) {

    }
}
