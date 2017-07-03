package com.boost.testaccelerometermap.presentation.view.statistics;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boost.testaccelerometermap.R;
import com.boost.testaccelerometermap.data.model.AccelerometerData;
import com.boost.testaccelerometermap.data.model.Location;
import com.boost.testaccelerometermap.presentation.model.LocationGroup;
import com.boost.testaccelerometermap.presentation.model.TimestampInRange;
import com.boost.testaccelerometermap.presentation.presenter.statistics.StatisticPresenterImpl;
import com.boost.testaccelerometermap.presentation.view.BaseFragment;
import com.boost.testaccelerometermap.presentation.view.statistics.adapter.StatisticAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yaroslav on 07.06.17.
 */

public class DataStatisticFragment extends BaseFragment implements StatisticView {
    private static final String TAG = "DataStatisticFragment";
    @BindView(R.id.rv_statistics)
    RecyclerView mStatisticsRecyclerView;

    private DataStatisticFragment.OnFragmentDataStatisticCallback mListener;
    @Inject
    StatisticPresenterImpl mStatisticPresenter;

    private StatisticAdapter mStatisticAdapter;
    private StatisticsDialog mDialog;
    private List<Location> mLocations;


    public static DataStatisticFragment newInstance() {
        DataStatisticFragment fragment = new DataStatisticFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DataStatisticFragment.OnFragmentDataStatisticCallback) {
            mListener = (DataStatisticFragment.OnFragmentDataStatisticCallback) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDomainComponent.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_statistics, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        mStatisticPresenter.onAttachView(this);
        initRecyclerView();
        mStatisticPresenter.getStatistics();
    }

    private void initRecyclerView() {
        mStatisticAdapter = new StatisticAdapter(view -> {
            int pos = mStatisticsRecyclerView.getChildAdapterPosition(view);
            Location model = mStatisticAdapter.getDataByPosition(pos);
            mStatisticPresenter.getLocations(model.getDayInMillis());
        });
        mStatisticsRecyclerView.setAdapter(mStatisticAdapter);
        mStatisticsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onAccelerometerResult(List<AccelerometerData> data) {
        if (mDialog != null){
            mDialog.onAccelerometerDataLoaded(data);
        }
    }

    @Override
    public void onStatisticsByDay(List<Location> data) {
        mStatisticAdapter.addAll(data);
    }

    @Override
    public void onLocations(List<Location> data) {
        mLocations = data;
        mDialog = StatisticsDialog.newInstance(LocationGroup.parseFromLocationsList(data, getString(R.string.location_pattern)));
        mDialog.setTargetFragment(this, StatisticsDialog.REQ_CODE_LOCATIONS);
        mDialog.show(getChildFragmentManager(), StatisticsDialog.class.getSimpleName());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            switch (requestCode) {
                case StatisticsDialog.REQ_CODE_LOCATIONS:
                    mListener.onStatisticCallback(getLocationsBundle());
                    break;
                case StatisticsDialog.REQ_CODE_ACCELEROMETER:
                    TimestampInRange timestampInRange = data.getParcelableExtra(TimestampInRange.class.getSimpleName());
                    mStatisticPresenter.getAccelerometerDataInRange(timestampInRange);
                    break;
            }
        }
    }

    private Bundle getLocationsBundle() {
        Bundle bundle = new Bundle();
        if (mLocations instanceof ArrayList){
            bundle.putParcelableArrayList(Location.class.getSimpleName(), (ArrayList<? extends Parcelable>) mLocations);
        }
        return bundle;
    }

    public interface OnFragmentDataStatisticCallback {
        void onStatisticCallback(Bundle data);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mStatisticPresenter.onDetachView();
        Log.d(TAG, "onDestroy: ");
    }
}
