package com.rodriguez.boardGameslibrary.client.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Publisher implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String country;
    private String web;

    @JsonIgnoreProperties({"publishers", "artists", "designers", "images"})
    private List<BoardGame> games;


    public Publisher() {
        this.games = new ArrayList<>();
    }

    public Publisher(String name, String country, String web) {
        this();
        this.name = name;
        this.country = country;
        this.web = web;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public List<BoardGame> getGames() {
        return games;
    }

    public void setGames(List<BoardGame> games) {
        this.games = games;
    }
}
