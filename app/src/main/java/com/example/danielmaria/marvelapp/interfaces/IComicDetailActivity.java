package com.example.danielmaria.marvelapp.interfaces;

import com.example.danielmaria.marvelapp.model.Comic;

public interface IComicDetailActivity {

    interface View {
        void showErrorMessage();

        void hideProgressBar();
        void setFixedInfos(Comic comic);
        void setAdapters(Comic comic);
    }

    interface Presenter {
        void getComics(int idComic);

    }



}
