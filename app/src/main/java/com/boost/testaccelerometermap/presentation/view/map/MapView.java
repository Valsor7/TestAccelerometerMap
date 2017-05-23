package com.boost.testaccelerometermap.presentation.view.map;

import com.boost.testaccelerometermap.presentation.model.Marker;
import com.boost.testaccelerometermap.presentation.view.BaseView;

import java.util.List;

/**
 * Created by yaroslav on 23.05.17.
 */

public interface MapView extends BaseView {
    public void showAll(List<Marker> markers);
}
