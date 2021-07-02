package com.example.my_shop.entity;

import java.util.Objects;

public class ClothDetails {
    private Long id;
    private Long languageID;
    private String name;
    private String color;
    private int numberOfPockets;
    private String season;
    private String pattern;
    private String about;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLanguageID() {
        return languageID;
    }

    public void setLanguageID(Long languageID) {
        this.languageID = languageID;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getNumberOfPockets() {
        return numberOfPockets;
    }

    public void setNumberOfPockets(int numberOfPockets) {
        this.numberOfPockets = numberOfPockets;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClothDetails that = (ClothDetails) o;
        return numberOfPockets == that.numberOfPockets && Objects.equals(id, that.id) && Objects.equals(languageID, that.languageID) && Objects.equals(name, that.name) && Objects.equals(color, that.color) && Objects.equals(season, that.season) && Objects.equals(pattern, that.pattern) && Objects.equals(about, that.about);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, languageID, name, color, numberOfPockets, season, pattern, about);
    }

    @Override
    public String toString() {
        return "ClothDetails{" +
                "id=" + id +
                ", languageID=" + languageID +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", numberOfPockets=" + numberOfPockets +
                ", season='" + season + '\'' +
                ", pattern='" + pattern + '\'' +
                ", about='" + about + '\'' +
                '}';
    }
}
