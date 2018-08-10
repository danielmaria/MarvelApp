package com.example.danielmaria.marvelapp.interfaces.fragments;

import com.example.danielmaria.marvelapp.model.Comic;

import java.util.List;

public interface IComicFragment {

    interface View {
        void showErrorMessage();
        void hideProgressBar();
        void setAdapter(List<Comic> comics);
    }

    interface Presenter {
        void getComics();
    }



}
