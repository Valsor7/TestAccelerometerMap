package com.boost.testaccelerometermap.presentation.view.statistics.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.boost.testaccelerometermap.R;
import com.boost.testaccelerometermap.presentation.model.DataCallback;
import com.boost.testaccelerometermap.presentation.model.LocationModel;
import com.boost.testaccelerometermap.presentation.model.LocationsGroup;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yaroslav on 08.06.17.
 */

public class LocationsAdapter extends ExpandableRecyclerViewAdapter<LocationsAdapter.LocationsGroupViewHolder, LocationsAdapter.AccelerometerViewHolder> {
    private static final String TAG = "StatisticAdapter";
    private List<LocationsGroup> mLocationGroups = new ArrayList<>();
    private List<LocationModel> mLocationModels = new ArrayList<>();
    private DataCallback<View> mCallback;


    public LocationsAdapter(List<? extends ExpandableGroup> groups,  DataCallback<View> callback) {
        super(groups);
        mCallback = callback;
    }

    @Override
    public int getItemCount() {
        return mLocations.size();
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

    }

    @Override
    public void onBindGroupViewHolder(LocationsGroupViewHolder holder, int flatPosition, ExpandableGroup group) {
        Log.d(TAG, "onBindGroupViewHolder flatPosition: " + flatPosition);
        holder.bind(mLocations.get(flatPosition));
    }

    public void addAll(List<LocationModel> data) {
        mLocations.addAll(data);
        notifyDataSetChanged();
    }

    class LocationsGroupViewHolder extends GroupViewHolder {

        @BindView(R.id.tv_data)
        TextView mLocationTv;

        public LocationsGroupViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(LocationModel model) {
            Log.d(TAG, "bind: model lat" + model.getLatitude() + " long " + model.getLongitude());

            String pattern = itemView.getContext().getString(R.string.location_pattern, model.getLatitude(), model.getLongitude());
            mLocationTv.setText(pattern);
        }

        @OnClick(R.id.ll_data_container)
        public void onClickLoad(View v){
            mCallback.onResult(v);
        }
    }

    class AccelerometerViewHolder extends ChildViewHolder {
        public AccelerometerViewHolder(View itemView) {
            super(itemView);
        }
    }
}