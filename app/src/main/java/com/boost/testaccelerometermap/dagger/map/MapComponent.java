package com.boost.testaccelerometermap.dagger.map;

import com.boost.testaccelerometermap.dagger.UtilsComponent;
import com.boost.testaccelerometermap.dagger.statistics.StatisticsComponent;
import com.boost.testaccelerometermap.data.db.DBDao;
import com.boost.testaccelerometermap.data.repository.Repository;
import com.boost.testaccelerometermap.presentation.model.AccelerometerData;
import com.boost.testaccelerometermap.presentation.model.LocationModel;
import com.boost.testaccelerometermap.presentation.view.map.LocationHelper;
import com.boost.testaccelerometermap.presentation.view.map.MapFragment;

import dagger.Component;

/**
 * Created by yaroslav on 24.05.17.
 */
@MapScope
@Component(dependencies = UtilsComponent.class, modules = MapModule.class)
public interface MapComponent {
    void inject(MapFragment fragment);
    DBDao<LocationModel> locationDao();
    Repository<LocationModel> locationRepository();
    LocationHelper helper();
}
