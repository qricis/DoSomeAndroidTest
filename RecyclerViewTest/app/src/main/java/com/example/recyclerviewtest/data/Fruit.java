package com.example.recyclerviewtest.data;

/**
* Description
* <p>
*
*
* @author qricis on 2020/8/13 13:50
* @version 1.0.0
*/
public class Fruit {

    private String name;
    private int imageId;

    public Fruit(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}