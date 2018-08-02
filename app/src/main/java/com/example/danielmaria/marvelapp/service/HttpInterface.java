package com.example.danielmaria.marvelapp.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface HttpInterface {

        @GET("characters")
        Call<JsonObject> getCharacters(
                @Query("ts") String timeStamp,
                @Query("apikey") String publicKey,
                @Query("hash") String hash);

        @GET("characters/{id}")
        Call<JsonObject> getCharactersById(
                @Path("id") int characterId,
                @Query("ts") String timeStamp,
                @Query("apikey") String publicKey,
                @Query("hash") String hash);

        @GET("comics")
        Call<JsonObject> getComics(
                @Query("ts") String timeStamp,
                @Query("apikey") String publicKey,
                @Query("hash") String hash);



        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gateway.marvel.com:443/v1/public/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

}
