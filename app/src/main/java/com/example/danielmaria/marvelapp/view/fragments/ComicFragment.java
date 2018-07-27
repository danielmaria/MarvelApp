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

import com.example.danielmaria.marvelapp.R;
import com.example.danielmaria.marvelapp.adapter.ComicsAdapter;
import com.example.danielmaria.marvelapp.model.Comic;
import com.example.danielmaria.marvelapp.model.Hero;

import java.util.ArrayList;
import java.util.List;

public class ComicFragment extends Fragment {
    private List<Comic> comics = new ArrayList<>();
    private RecyclerView comicsRecycler;
    private ComicsAdapter comicsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.comic_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        comicsRecycler = view.findViewById(R.id.comic_reycler);

        for(int i = 0; i < 3000; i++){
            comics.add(new Comic("Comic " + i, String.valueOf(i + 10)));
        }

        setAdapter();
    }

    private void setAdapter() {
        comicsAdapter = new ComicsAdapter(getContext(), comics);
        comicsRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        comicsRecycler.setAdapter(comicsAdapter);
    }
}
