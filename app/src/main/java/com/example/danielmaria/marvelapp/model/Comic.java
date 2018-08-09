package com.example.danielmaria.marvelapp.model;

import java.io.Serializable;
import java.util.List;

public class Comic implements Serializable {
    private String name;
    private int id;
    private String isbn;
    private String description;
    private int pageCount;
    private String thumbnail;
    private List<Stories> stories;
    private Series series;
    private List<Hero> characters;

    public Comic() {

    }

    public Comic(String name, String isbn) {
        this.name = name;
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<Stories> getStories() {
        return stories;
    }

    public void setStories(List<Stories> stories) {
        this.stories = stories;
    }

    public Series getSeries() { return series; }

    public void setSeries(Series series) {
        this.series = series;
    }

    public List<Hero> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Hero> characters) {
        this.characters = characters;
    }
}
