package com.boost.testaccelerometermap.presentation.view.statistics.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.boost.testaccelerometermap.R;
import com.boost.testaccelerometermap.presentation.model.AccelerometerData;
import com.boost.testaccelerometermap.presentation.model.DataCallback;
import com.boost.testaccelerometermap.presentation.model.LocationModel;
import com.boost.testaccelerometermap.presentation.model.LocationGroup;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yaroslav on 08.06.17.
 */

public class LocationsAdapter extends ExpandableRecyclerViewAdapter<LocationsAdapter.LocationsGroupViewHolder, LocationsAdapter.AccelerometerViewHolder> {
    private static final String TAG = "StatisticAdapter";
    private List<LocationGroup> mLocationGroups = new ArrayList<>();
    private DataCallback<View> mCallback;


    public LocationsAdapter(List<LocationGroup> groups,  DataCallback<View> callback) {
        super(groups);
        mCallback = callback;
    }

    @Override
    public LocationsGroupViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_statistic, parent, false);
        return new LocationsGroupViewHolder(view);
    }

    @Override
    public AccelerometerViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_statistic, parent, false);
        return new AccelerometerViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(AccelerometerViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        holder.bind((AccelerometerData) group.getItems().get(childIndex));
    }

    @Override
    public void onBindGroupViewHolder(LocationsGroupViewHolder holder, int flatPosition, ExpandableGroup group) {
        Log.d(TAG, "onBindGroupViewHolder flatPosition: " + flatPosition);
        holder.bind((LocationGroup) group);
    }

    public void addAll(List<LocationGroup> groupList) {
        mLocationGroups.clear();
        mLocationGroups.addAll(groupList);
        notifyDataSetChanged();
    }

    class LocationsGroupViewHolder extends GroupViewHolder {
        private static final String TAG = "LocationsGroupViewHolde";
        @BindView(R.id.tv_data)
        TextView mLocationTv;

        public LocationsGroupViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(LocationGroup locationGroup) {
            Log.d(TAG, "bind: " + locationGroup.getTitle());
            mLocationTv.setText(locationGroup.getTitle());
        }

        @OnClick(R.id.ll_data_container)
        public void onClickLoad(View v){
            mCallback.onResult(v);
        }
    }

    class AccelerometerViewHolder extends ChildViewHolder {
        private static final String TAG = "AccelerometerViewHolder";
        @BindView(R.id.tv_data)
        TextView mAccelerometerTv;

        public AccelerometerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(AccelerometerData data){
            Log.d(TAG, "bind: " + data);
            if (data != null) {
                mAccelerometerTv.setText(data.getTitle());
            }
        }
    }
}