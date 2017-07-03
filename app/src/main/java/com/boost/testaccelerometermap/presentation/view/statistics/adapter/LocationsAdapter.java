package com.boost.testaccelerometermap.presentation.view.statistics.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.boost.testaccelerometermap.R;
import com.boost.testaccelerometermap.data.model.AccelerometerData;
import com.boost.testaccelerometermap.presentation.model.DataCallback;
import com.boost.testaccelerometermap.presentation.model.EmptyTitleExpandableElement;
import com.boost.testaccelerometermap.presentation.model.ExpandableElement;
import com.boost.testaccelerometermap.presentation.model.LocationGroup;
import com.boost.testaccelerometermap.presentation.model.TimestampInRange;
import com.boost.testaccelerometermap.presentation.utils.TimeUtils;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yaroslav on 08.06.17.
 */

public class LocationsAdapter extends ExpandableRecyclerViewAdapter<LocationsAdapter.LocationsGroupViewHolder, LocationsAdapter.AccelerometerViewHolder> {
    private static final String TAG = "LocationsAdapter";
    private List<LocationGroup> mLocationGroups = new ArrayList<>();
    private LocationGroup mExpanded;
    private DataCallback<TimestampInRange> mCallback;


    public LocationsAdapter(List<LocationGroup> groups, DataCallback<TimestampInRange> callback) {
        super(groups);
        mLocationGroups = groups;
        mCallback = callback;
    }

    @Override
    public LocationsGroupViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_statistic, parent, false);
        return new LocationsGroupViewHolder(view);
    }

    @Override
    public AccelerometerViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_accelerometer, parent, false);
        return new AccelerometerViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(AccelerometerViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        Log.d(TAG, "onBindChildViewHolder: " + childIndex);
        holder.bind((ExpandableElement) group.getItems().get(childIndex));
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

    public void addAccelerometerList(List<AccelerometerData> data) {
        if (mExpanded != null && !data.isEmpty()) {
            mExpanded.getItems().clear();
            mExpanded.getItems().addAll(data);
            notifyDataSetChanged();
        }
    }

    class LocationsGroupViewHolder extends GroupViewHolder {
        private static final String TAG = "LocationsGroupViewHolde";
        @BindView(R.id.tv_data)
        TextView mLocationTv;
        private LocationGroup mLocationGroup;

        public LocationsGroupViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(LocationGroup locationGroup) {
            Log.d(TAG, "bind: " + locationGroup.getTitle());
            mLocationGroup = locationGroup;
            mLocationTv.setText(locationGroup.getTitle());
        }

        @Override
        public void expand() {
            super.expand();
            Log.d(TAG, "expand: ");
            mExpanded = mLocationGroup;

            long fromTimestamp = mLocationGroup.getLocationModel().getTimestamp();
            long toTimestamp = getNextElementTimestamp(mLocationGroups.indexOf(mLocationGroup));

            mCallback.onResult(new TimestampInRange(fromTimestamp, toTimestamp));
        }

        private long getNextElementTimestamp(int position) {
            return position + 1 < mLocationGroups.size() ?
                    mLocationGroups.get(position + 1).getLocationModel().getTimestamp() :
                    TimeUtils.getLastTimestampOfDay(mLocationGroups.get(position).getLocationModel().getTimestamp());
        }
    }

    class AccelerometerViewHolder extends ChildViewHolder {
        private static final String TAG = "AccelerometerViewHolder";
        @BindView(R.id.tv_accelerometer)
        TextView mAccelerometerTv;

        public AccelerometerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(ExpandableElement data) {
            Log.d(TAG, "bind: " + data);
            if (data instanceof EmptyTitleExpandableElement){
                mAccelerometerTv.setText(data.getTitle(itemView.getContext().getString(R.string.empty)));
            } else if (data instanceof AccelerometerData) {
                mAccelerometerTv.setText(data.getTitle(itemView.getContext().getString(R.string.accelerometer_pattern)));
            }
        }
    }
}