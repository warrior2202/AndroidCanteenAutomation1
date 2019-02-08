package com.example.user.catteemtestapp.Model;

public class CategoriesPogo {
    private String cm_cat_id;
    private String cm_cat_name;
    private String cm_cat_image;

    public CategoriesPogo(String cm_cat_id, String cm_cat_name, String cm_cat_image) {
        this.cm_cat_id = cm_cat_id;
        this.cm_cat_name = cm_cat_name;
        this.cm_cat_image = cm_cat_image;
    }

    public String getCm_cat_id() {
        return cm_cat_id;
    }

    public void setCm_cat_id(String cm_cat_id) {
        this.cm_cat_id = cm_cat_id;
    }

    public String getCm_cat_name() {
        return cm_cat_name;
    }

    public void setCm_cat_name(String cm_cat_name) {
        this.cm_cat_name = cm_cat_name;
    }

    public String getCm_cat_image() {
        return cm_cat_image;
    }

    public void setCm_cat_image(String cm_cat_image) {
        this.cm_cat_image = cm_cat_image;
    }
}
