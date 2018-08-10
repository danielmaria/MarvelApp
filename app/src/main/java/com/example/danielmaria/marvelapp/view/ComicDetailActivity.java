package com.example.danielmaria.marvelapp.view;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.danielmaria.marvelapp.R;
import com.example.danielmaria.marvelapp.view.adapter.SquareTitleAdapter;
import com.example.danielmaria.marvelapp.model.Comic;
import com.example.danielmaria.marvelapp.model.Hero;
import com.example.danielmaria.marvelapp.model.Stories;
import com.example.danielmaria.marvelapp.service.HttpService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ComicDetailActivity extends AppCompatActivity {
    private HttpService httpService;
    private Comic comic;

    @BindView(R.id.titulo_comic) TextView tituloComic;
    @BindView(R.id.progress_bar) ProgressBar progressBar;
    @BindView(R.id.error_message) ConstraintLayout errorMessage;
    @BindView(R.id.description_comic) TextView descriptionComic;
    @BindView(R.id.comic_isbn_title) TextView comicIsbnTitle;
    @BindView(R.id.title_page_count) TextView titlePageCount;
    @BindView(R.id.title_serie) TextView titleSerie;
    @BindView(R.id.img_comic) ImageView imgComic;

    @BindView(R.id.comic_stories_recycler) RecyclerView comicStoriesRecycler;
    private SquareTitleAdapter storiesAdapter;
    @BindView(R.id.comic_characters_recycler) RecyclerView comicCharactersRecycler;
    private SquareTitleAdapter charactersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_detail);
        ButterKnife.bind(this);

        this.comic = (Comic) getIntent().getSerializableExtra("comic");
        this.httpService = new HttpService();

        httpService.getComicById(this.comic.getId(), new HttpService.GetComicrByIdListener(){
            @Override
            public void sucess(Comic comicRequest) {
                comic = comicRequest;
                progressBar.setVisibility(View.GONE);

                setFixedInfos();
                setISBN();
                setImageComic();
                setAdapters();
            }
            @Override
            public void fail() {
                errorMessage.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        });

    }

    private void setFixedInfos() {
        tituloComic.setText(comic.getName());
        descriptionComic.setText(comic.getDescription());
        titlePageCount.setText(String.valueOf(comic.getPageCount()));
        titleSerie.setText(comic.getSeries().getName());
    }

    private void setAdapters() {
        setStoriesAdapter();
        setCharactersAdapter();
    }

    private void setISBN() {
        if(this.comic.getIsbn().isEmpty()){
            comicIsbnTitle.setVisibility(View.GONE);
        } else {
            this.comicIsbnTitle.setText("ISBN: " + comic.getIsbn());
        }
    }

    private void setCharactersAdapter() {
        List<String> namesCharacters = new ArrayList<>();
        for(Hero character : comic.getCharacters()){
            namesCharacters.add(character.getName());
        }
        if(namesCharacters.size() > 0){
            charactersAdapter = new SquareTitleAdapter(this, namesCharacters);
            comicCharactersRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            comicCharactersRecycler.setAdapter(charactersAdapter);
        } else {
            findViewById(R.id.comic_characters_recycler).setVisibility(View.GONE);
            findViewById(R.id.title_characters).setVisibility(View.GONE);
        }
    }

    private void setStoriesAdapter() {
        List<String> nomesStories = new ArrayList<>();
        for(Stories storie : comic.getStories()){
            nomesStories.add(storie.getName());
        }
        if(nomesStories.size() > 0){
            storiesAdapter = new SquareTitleAdapter(this, nomesStories);
            comicStoriesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            comicStoriesRecycler.setAdapter(storiesAdapter);
        } else {
            findViewById(R.id.comic_stories_recycler).setVisibility(View.GONE);
        }

    }

    private void setImageComic() {
        imgComic.setContentDescription("Image of " + comic.getName());
        Glide.with(this).load(comic.getThumbnail()).into(imgComic);
    }
}
