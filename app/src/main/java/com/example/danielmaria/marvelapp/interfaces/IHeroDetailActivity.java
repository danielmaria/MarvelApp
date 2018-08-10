package com.example.danielmaria.marvelapp.interfaces;

import com.example.danielmaria.marvelapp.model.Hero;

public interface IHeroDetailActivity {

    interface View {
        void hideErrorMessage();

        void hideProgressBar();
        void setFixedInfos(Hero hero);
        void setAdapters(Hero hero);
    }

    interface Presenter {
        void getCharacter(int idHero);

    }



}
