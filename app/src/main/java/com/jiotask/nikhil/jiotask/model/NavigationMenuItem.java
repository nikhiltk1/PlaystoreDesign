package com.jiotask.nikhil.jiotask.model;

/**
 * Created by Sharath on 6/25/2016.
 */
public class NavigationMenuItem
{
    private String name;
    private int imageId;

    public NavigationMenuItem(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
