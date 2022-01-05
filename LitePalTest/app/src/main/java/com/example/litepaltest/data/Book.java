package com.example.litepaltest.data;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

/**
 * Description
 * <p>
 *
 * @author qricis on 2020/8/20 9:40
 * @version 1.0.0
 */
public class Book extends LitePalSupport {

    @Column(nullable = false)
    private String author;

    private double price;

    private int pages;

    @Column(unique = true,defaultValue = "unknown",index = true)
    private String name;

    private String press;

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
