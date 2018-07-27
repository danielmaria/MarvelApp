package com.example.danielmaria.marvelapp.model;

public class Event {
    private String resourceURI;
    private String name;

    public Event() {
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
