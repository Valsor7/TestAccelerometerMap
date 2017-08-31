package com.boost.testaccelerometermap.presentation.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.boost.testaccelerometermap.MyApplication;
import com.boost.testaccelerometermap.dagger.activity.ActivityModule;
import com.boost.testaccelerometermap.dagger.domain.DomainComponent;
import com.boost.testaccelerometermap.dagger.domain.DomainModule;

public class BaseFragment extends Fragment {

    protected DomainComponent mDomainComponent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                mDomainComponent = MyApplication.getApp().getAppComponent()
                        .plusActivityComponent(new ActivityModule())
                        .plusDomain(new DomainModule());
    }
}
