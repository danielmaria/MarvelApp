package com.example.danielmaria.marvelapp.service;

import com.example.danielmaria.marvelapp.model.Comic;
import com.example.danielmaria.marvelapp.model.Hero;
import com.example.danielmaria.marvelapp.utils.MD5Utils;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.danielmaria.marvelapp.factory.HeroFactory.createSimpleHero;
import static com.example.danielmaria.marvelapp.factory.HeroFactory.createHero;
import static com.example.danielmaria.marvelapp.factory.ComicFactory.createSimpleComics;

public class HttpService {
    private HttpInterface api = HttpInterface.retrofit.create(HttpInterface.class);

    private String PRIVATE_KEY = "ca76d278180769407439e72dbc90f8cb64659527";
    private String PUBLIC_KEY = "3be683e06cd7801bbf9d4f0318d518e2";
    private String TIME_STAMP = "1";
    private String HASH = MD5Utils.createHashMd5(TIME_STAMP + PRIVATE_KEY + PUBLIC_KEY);

    public interface GetCharactersListener{
        void success(List<Hero> characters);
        void fail();
    }

    public interface GetComicListener {
        void sucess(List<Comic> comics);
        void fail();
    }

    public interface GetCharacterByIdListener {
        void sucess(Hero comics);
        void fail();
    }


    public void getComicsById(int id, final GetCharacterByIdListener getCharactersByIdListener) {
        Call<JsonObject> call = api.getCharactersById(id, TIME_STAMP, PUBLIC_KEY, HASH);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                switch (response.code()){
                    case 200:
                        getCharactersByIdListener.sucess(createHero(response.body()));
                        break;
                    default:
                        getCharactersByIdListener.fail();
                        break;
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                getCharactersByIdListener.fail();
            }
        });
    }

    public void getCharacters(final GetCharactersListener getCharactersListener){
        Call<JsonObject> call = api.getCharacters(TIME_STAMP, PUBLIC_KEY, HASH);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                switch (response.code()){
                    case 200:
                        getCharactersListener.success(createSimpleHero(response.body()));
                        break;
                    default:
                        getCharactersListener.fail();
                        break;
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                getCharactersListener.fail();
            }
        });
    }

    public void getComics(final GetComicListener getComicListener){
        Call<JsonObject> call = api.getComics(TIME_STAMP, PUBLIC_KEY, HASH);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                switch (response.code()){
                    case 200:
                        getComicListener.sucess(createSimpleComics(response.body()));
                        break;
                    default:
                        getComicListener.fail();
                        break;
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                getComicListener.fail();
            }
        });
    }
}
