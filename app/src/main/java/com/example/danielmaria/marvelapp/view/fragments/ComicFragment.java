package com.example.danielmaria.marvelapp.view.fragments;

import android.content.Intent;
import android.graphics.Color;
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
import android.widget.TextView;

import com.example.danielmaria.marvelapp.R;
import com.example.danielmaria.marvelapp.adapter.ComicsAdapter;
import com.example.danielmaria.marvelapp.model.Comic;
import com.example.danielmaria.marvelapp.model.Hero;
import com.example.danielmaria.marvelapp.service.HttpService;
import com.example.danielmaria.marvelapp.view.ComicDetailActivity;
import com.example.danielmaria.marvelapp.view.HeroDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class ComicFragment extends Fragment {
    private List<Comic> comics = new ArrayList<>();
    private RecyclerView comicsRecycler;
    private ComicsAdapter comicsAdapter;
    private HttpService httpService;
    private ProgressBar progressBar;
    private ConstraintLayout errorMessage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.comic_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        httpService = new HttpService();

        comicsRecycler = view.findViewById(R.id.comic_reycler);
        progressBar = view.findViewById(R.id.progress_bar);
        errorMessage = view.findViewById(R.id.error_message);

        httpService.getComics(new HttpService.GetComicListener() {
            @Override
            public void sucess(List<Comic> comicsList) {
                progressBar.setVisibility(View.GONE);
                comics = comicsList;
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
        comicsAdapter = new ComicsAdapter(getContext(), comics, new ComicsAdapter.OnComicClicked() {
            @Override
            public void onClick(Comic comic) {
                Intent intent = new Intent(getActivity(), ComicDetailActivity.class);
                intent.putExtra("comic", comic);

                startActivity(intent);
            }
        });
        comicsRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        comicsRecycler.setAdapter(comicsAdapter);
    }
}
