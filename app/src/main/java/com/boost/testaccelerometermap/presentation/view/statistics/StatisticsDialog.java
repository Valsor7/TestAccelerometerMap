package com.boost.testaccelerometermap.presentation.view.statistics;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boost.testaccelerometermap.R;
import com.boost.testaccelerometermap.presentation.model.DataCallback;
import com.boost.testaccelerometermap.presentation.model.LocationModel;
import com.boost.testaccelerometermap.presentation.view.statistics.adapter.LocationsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yaroslav on 08.06.17.
 */

public class StatisticsDialog extends DialogFragment {
    public static int REQ_CODE_LOCATIONS = 22002;
    @BindView(R.id.rv_locations)
    RecyclerView mLocationsRv;
    private LocationsAdapter mLocationsAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_statistics, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        initRecyclerView();
    }

    private void initRecyclerView() {
        mLocationsAdapter = new LocationsAdapter(new DataCallback<View>() {
            @Override
            public void onResult(View view) {
                Intent intent = new Intent();
                intent.
                int pos = mLocationsRv.getChildAdapterPosition(view);
                LocationModel model = mLocationsAdapter.getItemByPosition(pos);
                getTargetFragment().onActivityResult(REQ_CODE_LOCATIONS, Activity.RESULT_OK, null);

            }
        });
        mLocationsAdapter.addAll(getLocationsFromBundle());
        mLocationsRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mLocationsRv.setAdapter(mLocationsAdapter);
    }

    private List<LocationModel> getLocationsFromBundle(){
        List<LocationModel> locationModels = getArguments().getParcelableArrayList(LocationModel.class.getSimpleName());
        if (locationModels == null){
            return new ArrayList<>();
        }
        return locationModels;
    }

    @OnClick(R.id.btn_on_map)
    public void onClickShowOnMap(){
        Intent intent = new Intent();
        intent.putExtra(LocationModel.class.getSimpleName(), getArguments());
        getTargetFragment().onActivityResult(REQ_CODE_LOCATIONS, Activity.RESULT_OK, intent);
        dismiss();
    }
}
