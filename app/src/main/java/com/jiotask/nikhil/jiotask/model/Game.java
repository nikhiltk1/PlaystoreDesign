package com.jiotask.nikhil.jiotask.model;

/**
 * Created by Nikhil on 12-05-2018.
 */

public class Game {
    private String gameName;
    private String imageUrl;
    private String companyName;
    private int imageId;
    private int rating;

    public Game(String gameName, String imageUrl, String companyUrl,int imageId) {
        this.gameName = gameName;
        this.imageUrl = imageUrl;
        this.companyName = companyUrl;
        this.imageId=imageId;
    }

    public String getGameName() {
        return gameName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getCompanyName() {
        return companyName;
    }

    public int getImageId() {
        return imageId;
    }

    public int getRating() {
        return rating;
    }
}
