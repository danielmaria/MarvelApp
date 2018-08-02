package com.example.danielmaria.marvelapp.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.danielmaria.marvelapp.R;
import com.example.danielmaria.marvelapp.adapter.HeroesAdapter;
import com.example.danielmaria.marvelapp.model.Hero;
import com.example.danielmaria.marvelapp.service.HttpService;
import com.example.danielmaria.marvelapp.view.HeroDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class HeroFragment extends Fragment {

    private RecyclerView herosRecycler;
    private HeroesAdapter heroesAdapter;
    private List<Hero> heros = new ArrayList<>();
    private HttpService httpService;
    private ProgressBar progressBar;
    private ConstraintLayout errorMessage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.hero_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        httpService = new HttpService();

        herosRecycler = (RecyclerView) view.findViewById(R.id.hero_recycler);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        errorMessage = (ConstraintLayout) view.findViewById(R.id.error_message);

        httpService.getCharacters(new HttpService.GetCharactersListener(){
            @Override
            public void success(List<Hero> characters) {
                progressBar.setVisibility(View.GONE);
                heros = characters;
                setAdapter();
            }

            @Override
            public void fail() {
                errorMessage.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        });

    }

    private void setAdapter() {
        heroesAdapter = new HeroesAdapter(getContext(), heros, new HeroesAdapter.OnHeroClicked() {
            @Override
            public void onClick(Hero hero) {
                Intent intent = new Intent(getActivity(), HeroDetailActivity.class);
                intent.putExtra("hero", hero);

                startActivity(intent);
            }
        });
        herosRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        herosRecycler.setAdapter(heroesAdapter);
    }
}
