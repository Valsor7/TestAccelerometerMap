package com.boost.testaccelerometermap.presentation.presenter;

import com.boost.testaccelerometermap.presentation.view.BaseView;

/**
 * Created by yaroslav on 23.05.17.
 */

public interface BasePresenter {
    public void onAttachView(BaseView view);
    public void onDetachView();
}
