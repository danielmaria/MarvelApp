package com.example.danielmaria.marvelapp.factory;

import com.example.danielmaria.marvelapp.model.Comic;
import com.example.danielmaria.marvelapp.model.Event;
import com.example.danielmaria.marvelapp.model.Hero;
import com.example.danielmaria.marvelapp.model.Series;
import com.example.danielmaria.marvelapp.model.Stories;
import com.example.danielmaria.marvelapp.model.Url;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HeroFactory {

    public static List<Hero> createSimpleHero(JsonObject jsonObject){
        List<Hero> heros = new ArrayList<>();
        try {
            JSONObject json = new JSONObject(jsonObject.toString());

            JSONArray results = json.getJSONObject("data").getJSONArray("results");
            for (int i = 0; i < results.length(); i++) {
                Hero hero = new Hero();

                JSONObject characterJson = results.getJSONObject(i);
                hero.setId(characterJson.getInt("id"));
                hero.setName(characterJson.getString("name"));
                heros.add(hero);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return heros;
    }

    public static Hero createHero(JsonObject jsonObject){
        Hero hero = new Hero();
        try {
            JSONObject json = new JSONObject(jsonObject.toString());

            JSONArray results = json.getJSONObject("data").getJSONArray("results");
            for (int i = 0; i < results.length(); i++) {

                JSONObject characterJson = results.getJSONObject(i);
                hero.setId(characterJson.getInt("id"));
                hero.setName(characterJson.getString("name"));
                hero.setDescription(characterJson.getString("description"));
                JSONObject thumbNail = characterJson.getJSONObject("thumbnail");

                hero.setThumbnail(thumbNail.getString("path") + "." + thumbNail.getString("extension"));

                hero.setComics(formatComics(characterJson.getJSONObject("comics").getJSONArray("items")));
                hero.setSeries(formatSeries(characterJson.getJSONObject("series").getJSONArray("items")));
                hero.setStories(formatStories(characterJson.getJSONObject("stories").getJSONArray(("items"))));
                hero.setEvents(formatEvents(characterJson.getJSONObject("events").getJSONArray("items")));
                hero.setUrls(formatUrls(characterJson.getJSONArray("urls")));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return hero;
    }

    private static List<Url> formatUrls(JSONArray urls) throws JSONException {
        List<Url> urlList = new ArrayList<>();
        for (int i = 0; i < urls.length(); i++){
            Url url = new Url();
            url.setType(urls.getJSONObject(i).getString("type"));
            url.setUrl(urls.getJSONObject(i).getString("url"));
            urlList.add(url);
        }
        return urlList;
    }

    private static List<Event> formatEvents(JSONArray events) throws JSONException {
        List<Event> eventsList = new ArrayList<>();
        for (int i = 0; i < events.length(); i++){
            Event event = new Event();
            event.setName(events.getJSONObject(i).getString("name"));
            event.setResourceURI(events.getJSONObject(i).getString("resourceURI"));
            eventsList.add(event);
        }
        return eventsList;
    }

    private static List<Stories> formatStories(JSONArray stories) throws JSONException {
        List<Stories> storiesList = new ArrayList<>();
        for (int i = 0; i < stories.length(); i++){
            Stories story = new Stories();
            story.setName(stories.getJSONObject(i).getString("name"));
            story.setResourceURI(stories.getJSONObject(i).getString("resourceURI"));
            story.setType(stories.getJSONObject(i).getString("type"));
            storiesList.add(story);
        }
        return storiesList;
    }

    private static List<Series> formatSeries(JSONArray jsonArray) throws JSONException {
        List<Series> seriesList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++){
            Series serie = new Series();
            serie.setName(jsonArray.getJSONObject(i).getString("name"));
            serie.setResourceURI(jsonArray.getJSONObject(i).getString("resourceURI"));
            seriesList.add(serie);
        }
        return seriesList;
    }

    private static List<Comic> formatComics(JSONArray comics) throws JSONException {
        List<Comic> comicsHero = new ArrayList<>();
        for (int i = 0; i < comics.length(); i++) {
                Comic comic = new Comic();
                comic.setResourceURI(comics.getJSONObject(i).getString("resourceURI"));
                comic.setName(comics.getJSONObject(i).getString("name"));
                comicsHero.add(comic);
            }
        return comicsHero;
    }
}
