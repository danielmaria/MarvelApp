package com.example.danielmaria.marvelapp.interfaces.fragments;

import com.example.danielmaria.marvelapp.model.Comic;
import com.example.danielmaria.marvelapp.model.Hero;

import java.util.List;

public interface IHeroFragment {

    interface View {
        void showErrorMessage();
        void hideProgressBar();
        void setAdapter(List<Hero> comics);
    }

    interface Presenter {
        void getComics();
    }



}
