package com.boost.testaccelerometermap.data.repository;

import android.util.Log;

import com.boost.testaccelerometermap.data.Network;
import com.boost.testaccelerometermap.data.db.DBDao;
import com.boost.testaccelerometermap.presentation.model.AccelerometerData;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by yaroslav on 24.05.17.
 */

public class MapRepository extends Repository<AccelerometerData> {
    private static final String TAG = "MapRepository";
    private final Network mNetwork;
    private final DBDao<AccelerometerData> mDbDao;

    @Inject
    public MapRepository(Network network, DBDao dbDao) {
        this.mNetwork = network;
        mDbDao = dbDao;
    }

    @Override
    public void get(Object id) {

    }

    @Override
    public void getAll(RepositoryCallback<List<AccelerometerData>> callback) {
        mDbDao.getAllData(callback);
//        if (mNetwork.isNetwork()){
//
//        } else {
//            mDbDao.getAllData(callback);
//        }
    }

    @Override
    public void add(AccelerometerData item) {
        Log.d(TAG, "add: " + item);
        mDbDao.save(item);
    }

    @Override
    public void remove(Object id) {

    }

    @Override
    public void update(Object id) {

    }
}
