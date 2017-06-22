package com.boost.testaccelerometermap.presentation.view.statistics;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boost.testaccelerometermap.R;
import com.boost.testaccelerometermap.presentation.model.AccelerometerData;
import com.boost.testaccelerometermap.presentation.model.LocationModel;
import com.boost.testaccelerometermap.presentation.presenter.statistics.StatisticPresenterImpl;
import com.boost.testaccelerometermap.presentation.view.statistics.adapter.StatisticAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yaroslav on 07.06.17.
 */

public class DataStatisticFragment extends Fragment implements StatisticView{
    private static final String TAG = "DataStatisticFragment";
    @BindView(R.id.rv_statistics)
    RecyclerView mStatisticsRecyclerView;

    private DataStatisticFragment.OnFragmentDataStatisticCallback mListener;
    @Inject
    StatisticPresenterImpl mStatisticPresenter;

    private StatisticAdapter mStatisticAdapter;


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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    }

    private void initRecyclerView() {
        mStatisticAdapter = new StatisticAdapter(new StatisticAdapter.DateClickCallback() {
            @Override
            public void onDateClick(View view) {
                int pos = mStatisticsRecyclerView.getChildAdapterPosition(view);
                LocationModel model = mStatisticAdapter.getDataByPosition(pos);
                mStatisticPresenter.getLocations(model.getDayInMillis());
            }
        });
        mStatisticsRecyclerView.setAdapter(mStatisticAdapter);
        mStatisticsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void updateData(){
        mStatisticPresenter.getStatistics();
    }

    @Override
    public void onAccelerometerResult(List<AccelerometerData> data) {

    }

    @Override
    public void onStatisticsByDay(List<LocationModel> data) {
        mStatisticAdapter.addAll(data);
    }

    @Override
    public void onLocations(ArrayList<LocationModel> data) {
        StatisticsDialog dialog = new StatisticsDialog();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(LocationModel.class.getSimpleName(), data);
        dialog.setArguments(bundle);
        dialog.setTargetFragment(this, StatisticsDialog.REQ_CODE_LOCATIONS);
        dialog.show(getChildFragmentManager(), StatisticsDialog.class.getSimpleName());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == StatisticsDialog.REQ_CODE_LOCATIONS && resultCode == Activity.RESULT_OK){
            if (data != null){
                mListener.onStatisticCallback(data.getBundleExtra(LocationModel.class.getSimpleName()));
            }
        }
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
