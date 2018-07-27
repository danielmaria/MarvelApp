package com.example.danielmaria.marvelapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.danielmaria.marvelapp.R;
import com.example.danielmaria.marvelapp.model.Comic;

import java.util.List;

public class ComicsAdapter extends RecyclerView.Adapter{

    private Context context;
    private List<Comic> comics;

    public ComicsAdapter(Context context, List<Comic> comics) {
        this.context = context;
        this.comics = comics;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.comics_item, parent, false);
        return new ComicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ComicViewHolder comicViewHolder = (ComicViewHolder) holder;
        Comic comic = this.comics.get(position);

        comicViewHolder.isbn.setText(comic.getIsbn());
        comicViewHolder.comicName.setText(comic.getName());
    }

    @Override
    public int getItemCount() {
        return comics.size();
    }

    class ComicViewHolder extends RecyclerView.ViewHolder {

        TextView comicName;
        TextView isbn;

        public ComicViewHolder(View itemView) {
            super(itemView);
            comicName = itemView.findViewById(R.id.name_comic);
            isbn = itemView.findViewById(R.id.isbn_comic);

        }

    }
}
