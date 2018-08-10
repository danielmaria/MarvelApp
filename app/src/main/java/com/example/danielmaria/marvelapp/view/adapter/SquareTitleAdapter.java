package com.example.danielmaria.marvelapp.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.danielmaria.marvelapp.R;

import java.util.List;

public class SquareTitleAdapter extends Adapter {

    private Context context;
    private List<String> titleList;

    public SquareTitleAdapter(Context context, List<String> titleList) {
        this.context = context;
        this.titleList = titleList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.square_list_item, parent, false);
        return new HeroSeriesHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HeroSeriesHolder heroSeriesHolder = (HeroSeriesHolder) holder;
        String title = titleList.get(position);

        heroSeriesHolder.titleList.setText(title);
    }

    @Override
    public int getItemCount() {
        return titleList.size();
    }

    class HeroSeriesHolder extends RecyclerView.ViewHolder {

        TextView titleList;

        public HeroSeriesHolder(View itemView) {
            super(itemView);
            titleList = itemView.findViewById(R.id.square_item_text);
        }
    }

}
