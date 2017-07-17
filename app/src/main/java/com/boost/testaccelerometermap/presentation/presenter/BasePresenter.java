package com.boost.testaccelerometermap.presentation.presenter;

import com.boost.testaccelerometermap.presentation.view.BaseView;

/**
 * Created by yaroslav on 23.05.17.
 */

public interface BasePresenter<T> {
    public void onAttachView(T view);
    public void onDetachView();
}
