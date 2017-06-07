package com.boost.testaccelerometermap.data.repository;

import android.util.Log;

import com.boost.testaccelerometermap.dagger.map.qualifiers.Location;
import com.boost.testaccelerometermap.data.db.DBDao;
import com.boost.testaccelerometermap.presentation.model.LocationModel;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by yaroslav on 30.05.17.
 */

public class LocationRepositoryImpl extends Repository<LocationModel> {
    private static final String TAG = "LocationRepositoryImpl";
    private DBDao<LocationModel> mDbDao;

    @Inject
    public LocationRepositoryImpl(@Location DBDao dbDao) {
        mDbDao = dbDao;
    }

    @Override
    public void getAll(RepositoryCallback<List<LocationModel>> callback) {
        mDbDao.getAllData(callback);

//        if (mNetwork.isNetwork()){
//
//        } else {
//            mDbDao.getAllAccelerometerData(callback);
//        }
    }

    @Override
    public void getAllUnique(RepositoryCallback<List<LocationModel>> callback) {
        mDbDao.getAllUnique(callback);
    }

    @Override
    public void add(LocationModel item) {
        Log.d(TAG, "add: " + item);
        mDbDao.save(item);
    }


}
