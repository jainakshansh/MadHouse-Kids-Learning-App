package com.madhouseapp.kidslearningapp.Object;

/**
 * Created by Akshansh on 30-09-2017.
 */

public class CategoryItem {

    private String cat_name;
    private int cat_image;

    private CategoryItem() {
    }

    public CategoryItem(String cat_name, int cat_image) {
        this.cat_name = cat_name;
        this.cat_image = cat_image;
    }

    public String getCat_name() {
        return cat_name;
    }

    public int getCat_image() {
        return cat_image;
    }
}
