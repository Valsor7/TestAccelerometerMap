package com.boost.testaccelerometermap.presentation.view.statistics;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boost.testaccelerometermap.R;
import com.boost.testaccelerometermap.presentation.model.AccelerometerData;
import com.boost.testaccelerometermap.presentation.model.DataCallback;
import com.boost.testaccelerometermap.presentation.model.LocationGroup;
import com.boost.testaccelerometermap.presentation.model.LocationModel;
import com.boost.testaccelerometermap.presentation.model.TimestampInRange;
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
    private static final String TAG = "StatisticsDialog";
    public static final int REQ_CODE_LOCATIONS = 22002;
    public static final int REQ_CODE_ACCELEROMETER = 33003;
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
        mLocationsAdapter = new LocationsAdapter(getLocationsFromBundle(), new DataCallback<TimestampInRange>() {
            @Override
            public void onResult(TimestampInRange timestampInRange) {
                Log.d(TAG, "onResult:  locationmodel " + timestampInRange);

                Intent intent = new Intent();
                intent.putExtra(TimestampInRange.class.getSimpleName(), timestampInRange);
                getTargetFragment().onActivityResult(REQ_CODE_ACCELEROMETER, Activity.RESULT_OK, intent);
            }
        });
        mLocationsRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mLocationsRv.setAdapter(mLocationsAdapter);
    }

    private List<LocationGroup> getLocationsFromBundle() {
        List<LocationGroup> locationsGroup = getArguments().getParcelableArrayList(LocationGroup.class.getSimpleName());
        if (locationsGroup == null) {
            locationsGroup = new ArrayList<>();
        }
        Log.d(TAG, "getLocationsFromBundle: size " + locationsGroup.size());
        return locationsGroup;
    }

    @OnClick(R.id.btn_on_map)
    public void onClickShowOnMap() {
        Intent intent = new Intent();
        intent.putExtra(LocationModel.class.getSimpleName(), getArguments());
        getTargetFragment().onActivityResult(REQ_CODE_LOCATIONS, Activity.RESULT_OK, intent);
        dismiss();
    }

    public static StatisticsDialog newInstance(ArrayList<LocationGroup> locationGroups) {
        StatisticsDialog dialog = new StatisticsDialog();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(LocationGroup.class.getSimpleName(), locationGroups);
        dialog.setArguments(bundle);
        return dialog;
    }

    public void onAccelerometerDataLoaded(List<AccelerometerData> data) {
        mLocationsAdapter.addAccelerometerList(data);
    }
}
