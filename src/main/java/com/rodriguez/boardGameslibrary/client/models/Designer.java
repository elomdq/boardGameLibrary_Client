package com.rodriguez.boardGameslibrary.client.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Designer implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String lastName;
    private String country;

    @JsonIgnoreProperties({"publishers", "artists", "designers", "images"})
    private List<BoardGame> games;

    public Designer() {
        this.games = new ArrayList<>();
    }

    public Designer(String name, String lastName, String country) {
        this();
        this.name = name;
        this.lastName = lastName;
        this.country = country;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<BoardGame> getGames() {
        return games;
    }

    @JsonIgnoreProperties({"publishers", "artists", "designers", "images"})
    public void setGames(List<BoardGame> games) {
        this.games = games;
    }
}
