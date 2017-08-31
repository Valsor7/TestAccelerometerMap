package com.boost.testaccelerometermap.presentation.view.statistics.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.boost.testaccelerometermap.R;
import com.boost.testaccelerometermap.presentation.model.LocationModel;
import com.boost.testaccelerometermap.presentation.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yaroslav on 07.06.17.
 */

public class StatisticAdapter extends RecyclerView.Adapter<StatisticAdapter.StatisticViewHolder> {
    private static final String TAG = "StatisticAdapter";
    private List<LocationModel> mDays = new ArrayList<>();
    private DateClickCallback mCallback;

    public StatisticAdapter(DateClickCallback callback) {
        mCallback = callback;
    }

    @Override
    public StatisticViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_statistic, parent, false);
        view.setOnClickListener(v -> mCallback.onDateClick(v));
        return new StatisticViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StatisticViewHolder holder, int position) {
        holder.bind(mDays.get(position));
    }

    @Override
    public int getItemCount() {
        return mDays.size();
    }

    public void addAll(List<LocationModel> data) {
        mDays.addAll(data);
        notifyDataSetChanged();
    }

    public LocationModel getDataByPosition(int pos) {
        if (mDays.size() > pos && pos >= 0) {
            return mDays.get(pos);
        }
        return new LocationModel();
    }

    class StatisticViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_data)
        TextView mPeriodTv;

        public StatisticViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(LocationModel model){
            String displayTime = TimeUtils.getFormattedTimeFromMillis(model.getDayInMillis());
            Log.d(TAG, "bind: time " + displayTime);
            mPeriodTv.setText(displayTime);
        }
    }

    public interface DateClickCallback {
        public void onDateClick(View view);
    }
}
