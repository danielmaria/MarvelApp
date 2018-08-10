package com.example.danielmaria.marvelapp.presenter;

import com.example.danielmaria.marvelapp.interfaces.IComicDetailActivity;
import com.example.danielmaria.marvelapp.interfaces.IHeroDetailActivity;
import com.example.danielmaria.marvelapp.model.Comic;
import com.example.danielmaria.marvelapp.model.Hero;
import com.example.danielmaria.marvelapp.service.HttpService;
import com.example.danielmaria.marvelapp.view.ComicDetailActivity;
import com.example.danielmaria.marvelapp.view.HeroDetailActivity;

public class ComicDetailActivityPresenter implements IComicDetailActivity.Presenter {
    private HttpService httpService;

    private ComicDetailActivity view;

    public ComicDetailActivityPresenter(ComicDetailActivity view){
        this.view = view;
    }

    @Override
    public void getComics(int idComic) {
        this.httpService = new HttpService();
        this.httpService.getComicById(idComic, new HttpService.GetComicrByIdListener(){
            @Override
            public void sucess(Comic comicRequest) {
                Comic comic = comicRequest;
                view.hideProgressBar();

                view.setFixedInfos(comic);
                view.setAdapters(comic);
            }
            @Override
            public void fail() {
                view.hideErrorMessage();
                view.hideProgressBar();
            }
        });
    }
}
