package com.example.danielmaria.marvelapp.presenter.fragments;

import com.example.danielmaria.marvelapp.interfaces.fragments.IHeroFragment;
import com.example.danielmaria.marvelapp.model.Hero;
import com.example.danielmaria.marvelapp.service.HttpService;
import com.example.danielmaria.marvelapp.view.fragments.HeroFragment;

import java.util.List;

public class HeroFragmentPresenter implements IHeroFragment.Presenter {

    private HttpService httpService;
    private HeroFragment view;

    public HeroFragmentPresenter(HeroFragment view){
        this.view = view;
    }


    @Override
    public void getComics() {
        this.httpService = new HttpService();
        httpService.getCharacters(new HttpService.GetCharactersListener(){
            @Override
            public void success(List<Hero> characters) {
                view.hideProgressBar();
                view.setAdapter(characters);
            }

            @Override
            public void fail() {
                view.showErrorMessage();
                view.hideProgressBar();
            }
        });
    }
}
