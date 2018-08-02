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
import com.example.danielmaria.marvelapp.adapter.HeroComicsAdapter;
import com.example.danielmaria.marvelapp.adapter.SquareTitleAdapter;
import com.example.danielmaria.marvelapp.model.Comic;
import com.example.danielmaria.marvelapp.model.Hero;
import com.example.danielmaria.marvelapp.model.Series;
import com.example.danielmaria.marvelapp.model.Stories;
import com.example.danielmaria.marvelapp.service.HttpService;

import java.util.ArrayList;
import java.util.List;

public class HeroDetailActivity extends AppCompatActivity {

    private HttpService httpService;
    private ProgressBar progressBar;
    private ImageView imgHero;
    private SquareTitleAdapter heroComicsAdapter;
    private RecyclerView heroComicsRecycler;
    private TextView heroDescription;
    private TextView nameHero;
    private Hero hero;
    private ConstraintLayout errorMessage;
    private RecyclerView heroSeriesRecycler;
    private SquareTitleAdapter heroSeriesAdapter;
    private RecyclerView heroStoriesRecycler;
    private SquareTitleAdapter heroStoriesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_detail);

        hero = (Hero) getIntent().getSerializableExtra("hero");

        httpService = new HttpService();

        if(getActionBar() != null){
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }

        progressBar = findViewById(R.id.progress_bar);
        heroComicsRecycler = findViewById(R.id.hero_comics_recycler);
        heroSeriesRecycler = findViewById(R.id.hero_series_recycler);
        heroStoriesRecycler = findViewById(R.id.hero_stories_recycler);
        errorMessage = findViewById(R.id.error_message);
        heroDescription = findViewById(R.id.hero_description);
        nameHero = findViewById(R.id.name_hero);
        imgHero = findViewById(R.id.img_hero);

        httpService.getComicsById(hero.getId(), new HttpService.GetCharacterByIdListener() {
            @Override
            public void sucess(Hero heroRequest) {
                progressBar.setVisibility(View.GONE);
                hero = heroRequest;
                heroDescription.setText(hero.getDescription());
                nameHero.setText(hero.getName());

                setHeroComicAdapter();
                setHeroSeriesAdapter();
                setHeroStoriesAdapter();
                setImageHero();
            }

            @Override
            public void fail() {
                errorMessage.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void setImageHero() {
        imgHero.setContentDescription("Imagem do " + hero.getName());
        Glide.with(this).load(hero.getThumbnail()).into(imgHero);
    }

    private void setHeroComicAdapter() {
        List<String> nomes = new ArrayList<>();
        for (Comic comic: hero.getComics()) {
            nomes.add(comic.getName());
        }
        heroSeriesAdapter = new SquareTitleAdapter(this, nomes);
        heroSeriesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        heroSeriesRecycler.setAdapter(heroSeriesAdapter);
    }

    private void setHeroSeriesAdapter() {
        List<String> nomes = new ArrayList<>();
        for (Series serie: hero.getSeries()) {
            nomes.add(serie.getName());
        }
        heroComicsAdapter = new SquareTitleAdapter(this, nomes);
        heroComicsRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        heroComicsRecycler.setAdapter(heroComicsAdapter);
    }

    private void setHeroStoriesAdapter() {
        List<String> nomes = new ArrayList<>();
        for (Stories story: hero.getStories()) {
            nomes.add(story.getName());
        }
        heroStoriesAdapter = new SquareTitleAdapter(this, nomes);
        heroStoriesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        heroStoriesRecycler.setAdapter(heroStoriesAdapter);
    }

}
