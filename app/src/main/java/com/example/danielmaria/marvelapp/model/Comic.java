package com.example.danielmaria.marvelapp.model;

import java.util.List;

public class Comic {
    private String resourceURI;
    private String name;
    private int id;
    private String isbn;
    private String variantDescription;
    private String description;
    private int pageCount;
    private List<Url> urls;
    private List<Stories> stories;
    private List<Creator> creators;
    private List<Hero> characters;

    public Comic() {

    }

    public Comic(String name, String isbn) {
        this.name = name;
        this.isbn = isbn;
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
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

    public String getVariantDescription() {
        return variantDescription;
    }

    public void setVariantDescription(String variantDescription) {
        this.variantDescription = variantDescription;
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

    public List<Url> getUrls() {
        return urls;
    }

    public void setUrls(List<Url> urls) {
        this.urls = urls;
    }

    public List<Stories> getStories() {
        return stories;
    }

    public void setStories(List<Stories> stories) {
        this.stories = stories;
    }

    public List<Creator> getCreators() {
        return creators;
    }

    public void setCreators(List<Creator> creators) {
        this.creators = creators;
    }

    public List<Hero> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Hero> characters) {
        this.characters = characters;
    }
}
