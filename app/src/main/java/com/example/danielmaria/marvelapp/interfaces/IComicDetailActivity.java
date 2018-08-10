package com.example.danielmaria.marvelapp.interfaces;

import com.example.danielmaria.marvelapp.model.Comic;
import com.example.danielmaria.marvelapp.model.Hero;

public interface IComicDetailActivity {

    interface View {
        void hideErrorMessage();

        void hideProgressBar();
        void setFixedInfos(Comic comic);
        void setAdapters(Comic comic);
    }

    interface Presenter {
        void getComics(int idComic);

    }



}
