package com.boost.testaccelerometermap.presentation.view.statistics;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boost.testaccelerometermap.R;
import com.boost.testaccelerometermap.data.db.realm.RealmLocationDao;
import com.boost.testaccelerometermap.data.repository.LocationRepositoryImpl;
import com.boost.testaccelerometermap.presentation.model.LocationModel;
import com.boost.testaccelerometermap.presentation.presenter.statistics.StatisticPresenterImpl;
import com.boost.testaccelerometermap.presentation.view.statistics.adapter.StatisticAdapter;

import java.util.List;

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
    private StatisticPresenterImpl mStatisticPresenter;
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
        mStatisticPresenter = new StatisticPresenterImpl(new LocationRepositoryImpl(new RealmLocationDao()));
        mStatisticPresenter.onAttachView(this);
        initRecyclerView();
        mStatisticPresenter.getStatistics();
    }

    private void initRecyclerView() {
        mStatisticAdapter = new StatisticAdapter();
        mStatisticsRecyclerView.setAdapter(mStatisticAdapter);
        mStatisticsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void updateData(){
        mStatisticPresenter.getStatistics();
    }

    @Override
    public void onResult(List<LocationModel> data) {

    }

    public interface OnFragmentDataStatisticCallback {
        void onCallback();
    }
}
