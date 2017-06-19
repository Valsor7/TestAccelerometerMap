package com.boost.testaccelerometermap.presentation.view.statistics.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.boost.testaccelerometermap.R;
import com.boost.testaccelerometermap.presentation.model.LocationModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yaroslav on 08.06.17.
 */

public class LocationsAdapter extends RecyclerView.Adapter<LocationsAdapter.LocationsViewHolder> {
    private static final String TAG = "StatisticAdapter";
    private List<LocationModel> mLocations = new ArrayList<>();

    @Override
    public LocationsAdapter.LocationsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_statistic, parent, false);
        return new LocationsAdapter.LocationsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LocationsAdapter.LocationsViewHolder holder, int position) {
        holder.bind(mLocations.get(position));
    }

    @Override
    public int getItemCount() {
        return mLocations.size();
    }

    public void addAll(List<LocationModel> data) {
        mLocations.addAll(data);
        notifyDataSetChanged();
    }

    class LocationsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_data)
        TextView mLocationTv;

        public LocationsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(LocationModel model) {
            Log.d(TAG, "bind: model lat" + model.getLatitude() + " long " + model.getLongitude());
            String pattern = itemView.getContext().getString(R.string.location_pattern, model.getLatitude(), model.getLongitude());
            mLocationTv.setText(pattern);
        }
    }
}