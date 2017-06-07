package com.boost.testaccelerometermap.presentation.view.statistics.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.boost.testaccelerometermap.R;
import com.boost.testaccelerometermap.presentation.model.LocationModel;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yaroslav on 07.06.17.
 */

public class StatisticAdapter extends RecyclerView.Adapter<StatisticAdapter.StatisticViewHolder> {
    List<String> periods = new ArrayList<>();

    public StatisticAdapter() {
        periods.add("0");
        periods.add("1");
        periods.add("2");
        periods.add("3");
        periods.add("4");
        periods.add("5");
        periods.add("6");
        periods.add("7");
    }

    @Override
    public StatisticViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_statistic, parent, false);
        return new StatisticViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StatisticViewHolder holder, int position) {
        holder.bind(periods.get(position));
    }

    @Override
    public int getItemCount() {
        return periods.size();
    }

    public void addAll(List<LocationModel> data) {

    }

    class StatisticViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_date)
        TextView mPeriodTv;

        public StatisticViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(String s){
            mPeriodTv.setText(s);
        }
    }
}
