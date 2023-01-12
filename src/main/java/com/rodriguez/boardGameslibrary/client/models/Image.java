package com.rodriguez.boardGameslibrary.client.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import java.util.Objects;

/*@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")*/
public class Image implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String url;
    private String name;
    private String img;

    @JsonIgnoreProperties({"publishers", "artists", "designers", "images"})
    //@JsonBackReference
    private BoardGame game;


    public Image() {
    }

    public Image(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BoardGame getGame() {
        return game;
    }

    public void setGame(BoardGame game) {
        this.game = game;
        if(!game.getImages().contains(this)){
            game.getImages().add(this);
        }
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(id, image.id) && Objects.equals(url, image.url) && Objects.equals(name, image.name) && Objects.equals(img, image.img) && Objects.equals(game, image.game);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, name, img, game);
    }
}
