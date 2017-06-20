package com.boost.testaccelerometermap.dagger.accelerometer;

import android.app.Service;

import com.boost.testaccelerometermap.dagger.UtilsComponent;
import com.boost.testaccelerometermap.data.db.DBDao;
import com.boost.testaccelerometermap.data.db.realm.RealmAccelerometerDao;
import com.boost.testaccelerometermap.data.repository.Repository;
import com.boost.testaccelerometermap.presentation.model.AccelerometerData;
import com.boost.testaccelerometermap.presentation.view.AccelerometerService;

import dagger.Component;
import dagger.Module;

/**
 * Created by yaroslav on 25.05.17.
 */

@AccelerometerScope
@Component(dependencies = UtilsComponent.class, modules = AccelerometerModule.class)
public interface AccelerometerComponent {
    void inject(AccelerometerService service);
    DBDao<AccelerometerData> accelerometerDao();
    Repository<AccelerometerData> accelerometerRepository();
}
