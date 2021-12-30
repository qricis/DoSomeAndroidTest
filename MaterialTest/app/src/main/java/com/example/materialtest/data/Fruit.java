package com.example.materialtest.data;

/**
 * Description
 * <p>
 * 用于封装水果的信息
 * @author qricis on 2020/9/2 9:17
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
