package com.example.danielmaria.marvelapp.presenter;

import com.example.danielmaria.marvelapp.interfaces.IHeroDetailActivity;
import com.example.danielmaria.marvelapp.model.Hero;
import com.example.danielmaria.marvelapp.service.HttpService;
import com.example.danielmaria.marvelapp.view.HeroDetailActivity;

public class HeroDetailActivityPresenter implements IHeroDetailActivity.Presenter {
    private HttpService httpService;

    private HeroDetailActivity view;

    public HeroDetailActivityPresenter(HeroDetailActivity view){
        this.view = view;
    }

    @Override
    public void getCharacter(int idHero) {
        this.httpService = new HttpService();
        this.httpService.getHeroById(idHero, new HttpService.GetCharacterByIdListener() {
            @Override
            public void sucess(Hero heroRequest) {
                Hero hero = heroRequest;
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
