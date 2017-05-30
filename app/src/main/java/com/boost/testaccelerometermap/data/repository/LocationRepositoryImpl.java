package com.boost.testaccelerometermap.data.repository;

import android.util.Log;

import com.boost.testaccelerometermap.data.db.DBDao;
import com.boost.testaccelerometermap.presentation.model.LatLngLocation;

import java.util.List;

/**
 * Created by yaroslav on 30.05.17.
 */

public class LocationRepositoryImpl extends Repository<LatLngLocation> {
    private static final String TAG = "LocationRepositoryImpl";
    private DBDao<LatLngLocation> mDbDao;

    public LocationRepositoryImpl(DBDao dbDao) {
        mDbDao = dbDao;
    }

    @Override
    public void getAll(RepositoryCallback<List<LatLngLocation>> callback) {
        mDbDao.getAllData(callback);
//        if (mNetwork.isNetwork()){
//
//        } else {
//            mDbDao.getAllAccelerometerData(callback);
//        }
    }

    @Override
    public void add(LatLngLocation item) {
        Log.d(TAG, "add: " + item);
        mDbDao.save(item);
    }


}
