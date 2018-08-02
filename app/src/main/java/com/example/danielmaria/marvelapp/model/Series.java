package com.example.danielmaria.marvelapp.model;

public class Series {

    private String name;
    private String resourceURI;


    public Series() {

    }

    public Series(String name, String resourceURI) {
        this.name = name;
        this.resourceURI = resourceURI;
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

}
