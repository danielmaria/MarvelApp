package com.example.danielmaria.marvelapp.model;

public class Stories {
    private String resourceURI;
    private String name;
    private String type;

    public Stories() {

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
