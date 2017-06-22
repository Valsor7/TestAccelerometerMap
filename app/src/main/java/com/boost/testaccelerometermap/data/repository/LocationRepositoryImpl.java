package com.boost.testaccelerometermap.data.repository;

import android.util.Log;

import com.boost.testaccelerometermap.data.db.DBDao;
import com.boost.testaccelerometermap.presentation.model.LocationModel;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by yaroslav on 30.05.17.
 */

public class LocationRepositoryImpl extends Repository<LocationModel> {
    private static final String TAG = "LocationRepositoryImpl";
    private DBDao<LocationModel> mLocationDBDao;

    @Inject
    public LocationRepositoryImpl(DBDao<LocationModel> locationDbDao) {
        mLocationDBDao = locationDbDao;
    }

    @Override
    public void getAllUnique(RepositoryCallback<List<LocationModel>> callback) {
        mLocationDBDao.getAllUnique(callback);
    }

    @Override
    public void add(LocationModel item) {
        Log.d(TAG, "add: " + item);
        mLocationDBDao.save(item);
    }

    @Override
    public void get(Object id) {
        super.get(id);
    }
}
