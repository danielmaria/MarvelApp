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
import com.example.danielmaria.marvelapp.adapter.SquareTitleAdapter;
import com.example.danielmaria.marvelapp.model.Comic;
import com.example.danielmaria.marvelapp.model.Hero;
import com.example.danielmaria.marvelapp.model.Series;
import com.example.danielmaria.marvelapp.model.Stories;
import com.example.danielmaria.marvelapp.service.HttpService;

import java.util.ArrayList;
import java.util.List;

public class ComicDetailActivity extends AppCompatActivity {
    private TextView tituloComic;
    private TextView descriptionComic;
    private TextView comicIsbnTitle;
    private TextView titlePageCount;
    private TextView titleSerie;
    private Comic comic;
    private HttpService httpService;
    private ConstraintLayout errorMessage;
    private ProgressBar progressBar;
    private ImageView imgComic;

    private RecyclerView comicStoriesRecycler;
    private SquareTitleAdapter storiesAdapter;
    private RecyclerView comicCharactersRecycler;
    private SquareTitleAdapter charactersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_detail);
        progressBar = findViewById(R.id.progress_bar);
        errorMessage = findViewById(R.id.error_message);
        tituloComic = findViewById(R.id.titulo_comic);
        descriptionComic = findViewById(R.id.description_comic);
        comicIsbnTitle = findViewById(R.id.comic_isbn_title);
        titlePageCount = findViewById(R.id.title_page_count);
        imgComic = findViewById(R.id.img_comic);
        titleSerie = findViewById(R.id.title_serie);

        comicStoriesRecycler = findViewById(R.id.comic_stories_recycler);
        comicCharactersRecycler = findViewById(R.id.comic_characters_recycler);

        this.comic = (Comic) getIntent().getSerializableExtra("comic");
        httpService = new HttpService();

        httpService.getComicById(this.comic.getId(), new HttpService.GetComicrByIdListener(){
            @Override
            public void sucess(Comic comicRequest) {
                progressBar.setVisibility(View.GONE);
                comic = comicRequest;
                tituloComic.setText(comic.getName());
                descriptionComic.setText(comic.getDescription());
                comicIsbnTitle.setText(!comic.getIsbn().isEmpty() ? "ISBN: " + comic.getIsbn() : "");
                titlePageCount.setText(String.valueOf(comic.getPageCount()));
                titleSerie.setText(comic.getSeries().getName());

                setStoriesAdapter();
                setCharactersAdapter();
                setImageComic();
            }
            @Override
            public void fail() {
                errorMessage.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        });

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
        imgComic.setContentDescription("Imagem do " + comic.getName());
        Glide.with(this).load(comic.getThumbnail()).into(imgComic);
    }
}
