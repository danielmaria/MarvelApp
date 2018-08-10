package com.example.danielmaria.marvelapp.presenter;

import android.view.View;

import com.example.danielmaria.marvelapp.interfaces.IHeroDetailActivity;
import com.example.danielmaria.marvelapp.interfaces.IMainActivity;
import com.example.danielmaria.marvelapp.model.Hero;
import com.example.danielmaria.marvelapp.service.HttpService;
import com.example.danielmaria.marvelapp.view.HeroDetailActivity;
import com.example.danielmaria.marvelapp.view.MainActivity;

public class HeroDetailActivityPresenter implements IHeroDetailActivity.Presenter {
    private HttpService httpService;
    private Hero hero;

    private HeroDetailActivity view;

    public HeroDetailActivityPresenter(HeroDetailActivity view){
        this.view = view;
    }

    @Override
    public void getCharacter(int idHero) {
        this.httpService.getHeroById(idHero, new HttpService.GetCharacterByIdListener() {
            @Override
            public void sucess(Hero heroRequest) {
                hero = heroRequest;
                view.hideProgressBar();
                view.setFixedInfos(hero);
                view.setAdapters(hero);
            }

            @Override
            public void fail() {
                view.hideErrorMessage();
                view.hideProgressBar();
            }
        });
    }
}
