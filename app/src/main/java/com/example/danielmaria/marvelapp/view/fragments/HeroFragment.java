package com.example.danielmaria.marvelapp.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import java.util.ArrayList;
import java.util.List;

public class HeroFragment extends Fragment {

    private RecyclerView herosRecycler;
    private HeroesAdapter heroesAdapter;
    private List<Hero> heros = new ArrayList<>();
    private HttpService httpService;
    private ProgressBar progressBar;

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

        httpService.getCharacters(new HttpService.GetCharactersListener(){

            @Override
            public void success(List<Hero> characters) {
                progressBar.setVisibility(View.GONE);
                heros = characters;
                setAdapter();
            }

            @Override
            public void fail() {
                //Algo deu errado
            }
        });

    }

    private void setAdapter(){
        heroesAdapter = new HeroesAdapter(getContext(), heros);
        herosRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        herosRecycler.setAdapter(heroesAdapter);
    }

    private void mockHero(){
        for(int i = 0; i < 3000; i++){
            heros.add(new Hero("Heroi " + i, i + 10));
        }
    }
}
