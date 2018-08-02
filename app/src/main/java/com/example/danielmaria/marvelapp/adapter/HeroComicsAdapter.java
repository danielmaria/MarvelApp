package com.example.danielmaria.marvelapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.danielmaria.marvelapp.R;
import com.example.danielmaria.marvelapp.model.Comic;

import java.util.List;

public class HeroComicsAdapter extends Adapter {

    private Context context;
    private List<Comic> comicList;

    public HeroComicsAdapter(Context context, List<Comic> comicList) {
        this.context = context;
        this.comicList = comicList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.square_list_item, parent, false);
        return new HeroComicsHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HeroComicsHolder heroComicsHolder = (HeroComicsHolder) holder;
        Comic comic = comicList.get(position);

        heroComicsHolder.comicsTitleText.setText(comic.getName());
    }

    @Override
    public int getItemCount() {
        return comicList.size();
    }

    class HeroComicsHolder extends RecyclerView.ViewHolder {

        TextView comicsTitleText;

        public HeroComicsHolder(View itemView) {
            super(itemView);
            comicsTitleText = itemView.findViewById(R.id.square_item_text);
        }
    }

}
