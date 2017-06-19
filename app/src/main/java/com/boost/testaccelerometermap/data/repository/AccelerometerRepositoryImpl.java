package com.boost.testaccelerometermap.data.repository;

import com.boost.testaccelerometermap.dagger.map.qualifiers.Accelerometer;
import com.boost.testaccelerometermap.data.MyError;
import com.boost.testaccelerometermap.data.Network;
import com.boost.testaccelerometermap.data.db.DBDao;
import com.boost.testaccelerometermap.presentation.model.AccelerometerData;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by yaroslav on 24.05.17.
 */
public class AccelerometerRepositoryImpl extends Repository<AccelerometerData> {
    private static final String TAG = "MapRepository";
    private final Network mNetwork;
    private final DBDao<AccelerometerData> mDbDao;

    @Inject
    public AccelerometerRepositoryImpl(Network network, @Accelerometer DBDao dbDao) {
        this.mNetwork = network;
        mDbDao = dbDao;
    }

    @Override
    public void getAll(RepositoryCallback<List<AccelerometerData>> callback) {
        mDbDao.getAllData(callback);
//        if (mNetwork.isNetwork()){
//
//        } else {
//            mDbDao.getAccelerometerDataInRange(callback);
//        }
    }

    @Override
    public void getInRange(long from, long to, RepositoryCallback<List<AccelerometerData>> callback) {
        if (from < to) {
            mDbDao.getInRange(from, to, callback);
        } else {
            callback.onError(MyError.RANGE_ERROR);
        }
    }

    @Override
    public void addAll(List<AccelerometerData> items) {
        mDbDao.saveAll(items);
    }
}
