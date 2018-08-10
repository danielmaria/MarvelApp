package com.example.danielmaria.marvelapp.presenter.fragments;

import com.example.danielmaria.marvelapp.interfaces.fragments.IComicFragment;
import com.example.danielmaria.marvelapp.model.Comic;
import com.example.danielmaria.marvelapp.service.HttpService;
import com.example.danielmaria.marvelapp.view.fragments.ComicFragment;

import java.util.List;

public class ComicFragmentPresenter implements IComicFragment.Presenter {

    private HttpService httpService;
    private ComicFragment view;

    public ComicFragmentPresenter(ComicFragment view){
        this.view = view;
    }


    @Override
    public void getComics() {
        this.httpService = new HttpService();
        this.httpService.getComics(new HttpService.GetComicListener() {
            @Override
            public void sucess(List<Comic> comicsList) {
                view.hideProgressBar();
                view.setAdapter(comicsList);
            }

            @Override
            public void fail() {
                view.showErrorMessage();
                view.hideProgressBar();
            }
        });
    }
}
