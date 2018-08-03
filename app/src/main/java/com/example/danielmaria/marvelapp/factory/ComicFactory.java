package com.example.danielmaria.marvelapp.factory;

import com.example.danielmaria.marvelapp.model.Comic;
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

public class ComicFactory {

    public static List<Comic> createSimpleComics (JsonObject jsonObject){
        List<Comic> comics = new ArrayList<>();
        try {
            JSONObject json = new JSONObject(jsonObject.toString());

            JSONArray results = json.getJSONObject("data").getJSONArray("results");

            for (int i = 0; i < results.length(); i++) {
                JSONObject comicJson = results.getJSONObject(i);
                Comic comic = new Comic();
                comic.setName(comicJson.getString("title"));
                comic.setId(comicJson.getInt("id"));
                comic.setIsbn(comicJson.getString("isbn"));

                JSONObject thumbNail = comicJson.getJSONObject("thumbnail");
                comic.setThumbnail(thumbNail.getString("path") + "." + thumbNail.getString("extension"));

                comics.add(comic);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return comics;
    }

    public static Comic createComic(JsonObject jsonObject){
        Comic comic = new Comic();
        try {
            JSONObject json = new JSONObject(jsonObject.toString());

            JSONArray results = json.getJSONObject("data").getJSONArray("results");

            for (int i = 0; i < results.length(); i++) {
                JSONObject comicJson = results.getJSONObject(i);

                comic.setName(comicJson.getString("title"));
                comic.setId(comicJson.getInt("id"));
                comic.setIsbn(comicJson.getString("isbn"));
                comic.setPageCount(comicJson.getInt("pageCount"));
                comic.setResourceURI(comicJson.getString("resourceURI"));

                JSONObject thumbNail = comicJson.getJSONObject("thumbnail");
                comic.setThumbnail(thumbNail.getString("path") + "." + thumbNail.getString("extension"));

                JSONObject series = comicJson.getJSONObject("series");
                comic.setSeries(new Series(series.getString("name"), series.getString("resourceURI")));

                comic.setUrls(formatUrls(comicJson.getJSONArray("urls")));

                comic.setCharacters(formatCharacters(comicJson.getJSONObject("characters").getJSONArray("items")));
                comic.setStories(formatStories(comicJson.getJSONObject("stories").getJSONArray("items")));
                return comic;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return comic;
    }

    private static List<Hero> formatCharacters(JSONArray charactersArray) throws JSONException{
        List<Hero> characters = new ArrayList<>();
        for(int i = 0; i < charactersArray.length(); i++){
            Hero character = new Hero();
            character.setName(charactersArray.getJSONObject(i).getString("name"));
        }
        return characters;
    }


    private static List<Url> formatUrls(JSONArray urls) throws JSONException {
        List<Url> urlList = new ArrayList<>();
        for (int i = 0; i < urls.length(); i++) {
            Url url = new Url();
            url.setType(urls.getJSONObject(i).getString("type"));
            url.setUrl(urls.getJSONObject(i).getString("url"));
            urlList.add(url);
        }
        return urlList;
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
}
