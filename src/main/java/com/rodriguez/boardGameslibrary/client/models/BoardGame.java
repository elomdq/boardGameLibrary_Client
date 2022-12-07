package com.rodriguez.boardGameslibrary.client.models;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")*/
public class BoardGame implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private int minPlayers;
    private int maxPlayers;
    private int minAge;
    private int bgYear;
    private int likes;

    private String bgg;

    //@JsonIgnoreProperties({"game"})
    //@JsonManagedReference
    @JsonIgnore
    private List<Image> images;

    @JsonIgnoreProperties({"games"})
    private List<Publisher> publishers;

    @JsonIgnoreProperties({"games"})
    private List<Designer> designers;

    @JsonIgnoreProperties({"games"})
    private List<Artist> artists;


    public BoardGame() {
        this.images = new ArrayList<>();
        this.designers = new ArrayList<>();
        this.publishers = new ArrayList<>();
        this.artists = new ArrayList<>();
    }

    public BoardGame(String name, int minPlayers, int maxPlayers, int minAge, int year, String bgg) {
        this();
        this.name = name;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.minAge = minAge;
        this.bgYear = year;
        this.bgg = bgg;
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

    public int getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getBgYear() {
        return bgYear;
    }

    public void setBgYear(int bgYear) {
        this.bgYear = bgYear;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getBgg() {
        return bgg;
    }

    public void setBgg(String bgg) {
        this.bgg = bgg;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<Publisher> getPublishers() {
        return publishers;
    }

    public void setPublishers(List<Publisher> publishers) {
        this.publishers = publishers;
    }

    public List<Designer> getDesigners() {
        return designers;
    }

    public void setDesigners(List<Designer> designers) {
        this.designers = designers;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }
}
