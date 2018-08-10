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
import com.example.danielmaria.marvelapp.interfaces.IHeroDetailActivity;
import com.example.danielmaria.marvelapp.presenter.HeroDetailActivityPresenter;
import com.example.danielmaria.marvelapp.view.adapter.SquareTitleAdapter;
import com.example.danielmaria.marvelapp.model.Comic;
import com.example.danielmaria.marvelapp.model.Event;
import com.example.danielmaria.marvelapp.model.Hero;
import com.example.danielmaria.marvelapp.model.Series;
import com.example.danielmaria.marvelapp.model.Stories;
import com.example.danielmaria.marvelapp.service.HttpService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HeroDetailActivity extends AppCompatActivity implements IHeroDetailActivity.View{

    private IHeroDetailActivity.Presenter presenter = new HeroDetailActivityPresenter(this);

    @BindView(R.id.progress_bar) ProgressBar progressBar;
    @BindView(R.id.img_hero) ImageView imgHero;
    @BindView(R.id.hero_description) TextView heroDescription;
    @BindView(R.id.name_hero) TextView nameHero;
    @BindView(R.id.error_message) ConstraintLayout errorMessage;

    @BindView(R.id.hero_series_recycler) RecyclerView heroSeriesRecycler;
    private SquareTitleAdapter heroSeriesAdapter;
    @BindView(R.id.hero_comics_recycler) RecyclerView heroComicsRecycler;
    private SquareTitleAdapter heroComicsAdapter;
    @BindView(R.id.hero_stories_recycler) RecyclerView heroStoriesRecycler;
    private SquareTitleAdapter heroStoriesAdapter;
    @BindView(R.id.hero_events_recycler) RecyclerView heroEventsRecycler;
    private SquareTitleAdapter heroEventsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_detail);
        ButterKnife.bind(this);

        Hero heroSelected = (Hero) getIntent().getSerializableExtra("hero");

        if(getActionBar() != null){
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }

        this.presenter.getCharacter(heroSelected.getId());
    }

    @Override
    public void hideErrorMessage(){
        errorMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setFixedInfos(Hero hero) {
        heroDescription.setText(hero.getDescription());
        nameHero.setText(hero.getName());
        setImageHero(hero.getName(), hero.getThumbnail());
    }

    @Override
    public void setAdapters(Hero hero) {
        setHeroComicAdapter(hero.getComics());
        setHeroSeriesAdapter(hero.getSeries());
        setHeroStoriesAdapter(hero.getStories());
        setHeroEventsAdapter(hero.getEvents());
    }

    private void setImageHero(String name, String thumbnail) {
        imgHero.setContentDescription("Image of " + name);
        Glide.with(this).load(thumbnail).into(imgHero);
    }

    private void setHeroComicAdapter(List<Comic> comics) {
        List<String> nomes = new ArrayList<>();
        for (Comic comic: comics) {
            nomes.add(comic.getName());
        }
        if(nomes.size() > 0){
            heroSeriesAdapter = new SquareTitleAdapter(this, nomes);
            heroSeriesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            heroSeriesRecycler.setAdapter(heroSeriesAdapter);
        } else {
            findViewById(R.id.hero_comics_title).setVisibility(View.GONE);
        }
    }

    private void setHeroSeriesAdapter(List<Series> series) {
        List<String> nomes = new ArrayList<>();
        for (Series serie: series) {
            nomes.add(serie.getName());
        }
        if(nomes.size() > 0){
            heroComicsAdapter = new SquareTitleAdapter(this, nomes);
            heroComicsRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            heroComicsRecycler.setAdapter(heroComicsAdapter);
        } else {
            findViewById(R.id.hero_series_title).setVisibility(View.GONE);
        }
    }

    private void setHeroStoriesAdapter(List<Stories> stories) {
        List<String> nomes = new ArrayList<>();
        for (Stories story: stories) {
            nomes.add(story.getName());
        }
        if(nomes.size() > 0){
            heroStoriesAdapter = new SquareTitleAdapter(this, nomes);
            heroStoriesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            heroStoriesRecycler.setAdapter(heroStoriesAdapter);
        } else {
            findViewById(R.id.hero_stories_title).setVisibility(View.GONE);
        }
    }
    private void setHeroEventsAdapter(List<Event> eventos) {
        List<String> nomes = new ArrayList<>();
        for (Event event: eventos) {
            nomes.add(event.getName());
        }
        if(nomes.size() > 0){
            heroEventsAdapter = new SquareTitleAdapter(this, nomes);
            heroEventsRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            heroEventsRecycler.setAdapter(heroEventsAdapter);
        } else {
            findViewById(R.id.hero_events_title).setVisibility(View.GONE);
        }

    }

}
