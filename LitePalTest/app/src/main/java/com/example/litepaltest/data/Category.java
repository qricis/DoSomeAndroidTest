package com.example.litepaltest.data;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

/**
 * Description
 * <p>
 *
 * @author qricis on 2020/8/20 10:10
 * @version 1.0.0
 */
public class Category extends LitePalSupport {

    @Column(defaultValue = "unknown")
    private String categoryName;

    private int categoryCode;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }
}
