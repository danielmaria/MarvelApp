package com.example.danielmaria.marvelapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.danielmaria.marvelapp.R;
import com.example.danielmaria.marvelapp.model.Comic;
import com.example.danielmaria.marvelapp.model.Hero;

import java.util.List;

public class ComicsAdapter extends RecyclerView.Adapter{

    private Context context;
    private List<Comic> comics;
    private OnComicClicked onComicClicked;

    public ComicsAdapter(Context context, List<Comic> comics, OnComicClicked onComicClicked) {
        this.context = context;
        this.comics = comics;
        this.onComicClicked = onComicClicked;
    }
    public interface OnComicClicked {
        void onClick(Comic comic);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.comics_item, parent, false);
        return new ComicViewHolder(view, onComicClicked, comics);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ComicViewHolder comicViewHolder = (ComicViewHolder) holder;
        Comic comic = this.comics.get(position);

        comicViewHolder.isbn.setText(!comic.getIsbn().isEmpty() ? "ISBN: " + comic.getIsbn() : "");
        comicViewHolder.comicName.setText(comic.getName());

        setImageComic(comicViewHolder, comic);
    }

    @Override
    public int getItemCount() {
        return comics.size();
    }

    private void setImageComic(ComicViewHolder holder, Comic comic) {
        holder.backgroundComic.setContentDescription("Imagem do " + comic.getName());
        Glide.with(this.context).load(comic.getThumbnail()).into(holder.backgroundComic);
    }

    class ComicViewHolder extends RecyclerView.ViewHolder {

        TextView comicName;
        TextView isbn;
        ImageView backgroundComic;

        public ComicViewHolder(View itemView, final ComicsAdapter.OnComicClicked onComicClicked, final List<Comic> comics) {
            super(itemView);
            this.comicName = itemView.findViewById(R.id.name_comic);
            this.isbn = itemView.findViewById(R.id.isbn_comic);
            this.backgroundComic = (ImageView) itemView.findViewById(R.id.background_comic);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onComicClicked.onClick(comics.get(getAdapterPosition()));
                }
            });
        }

    }
}
