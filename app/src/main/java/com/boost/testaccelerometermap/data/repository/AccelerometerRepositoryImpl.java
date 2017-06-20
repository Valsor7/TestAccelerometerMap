package com.boost.testaccelerometermap.data.repository;

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
    public AccelerometerRepositoryImpl(Network network, DBDao<AccelerometerData> dbDao) {
        mNetwork = network;
        mDbDao = dbDao;
    }

    @Override
    public void getAll(RepositoryCallback<List<AccelerometerData>> callback) {
        mDbDao.getAllData(callback);
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
    public void add(AccelerometerData item) {
        mDbDao.save(item);
    }

    @Override
    public void addAll(List<AccelerometerData> items) {
        mDbDao.saveAll(items);
    }
}
