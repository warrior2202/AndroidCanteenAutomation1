package com.example.user.catteemtestapp.Model;

public class Foodpogo {
    private String cm_item_id;
    private String cm_item_cat_id;
    private String cm_item_name;
    private String cm_item_image;
    private String cm_item_price;
    private String cm_item_desc;

    public String getCm_item_id() {
        return cm_item_id;
    }

    public void setCm_item_id(String cm_item_id) {
        this.cm_item_id = cm_item_id;
    }

    public String getCm_item_cat_id() {
        return cm_item_cat_id;
    }

    public void setCm_item_cat_id(String cm_item_cat_id) {
        this.cm_item_cat_id = cm_item_cat_id;
    }

    public String getCm_item_name() {
        return cm_item_name;
    }

    public void setCm_item_name(String cm_item_name) {
        this.cm_item_name = cm_item_name;
    }

    public String getCm_item_image() {
        return cm_item_image;
    }

    public void setCm_item_image(String cm_item_image) {
        this.cm_item_image = cm_item_image;
    }

    public String getCm_item_price() {
        return cm_item_price;
    }

    public void setCm_item_price(String cm_item_price) {
        this.cm_item_price = cm_item_price;
    }

    public String getCm_item_desc() {
        return cm_item_desc;
    }

    public void setCm_item_desc(String cm_item_desc) {
        this.cm_item_desc = cm_item_desc;
    }

    public Foodpogo(String cm_item_id, String cm_item_cat_id, String cm_item_name, String cm_item_image, String cm_item_price, String cm_item_desc) {
        this.cm_item_id = cm_item_id;
        this.cm_item_cat_id = cm_item_cat_id;
        this.cm_item_name = cm_item_name;
        this.cm_item_image = cm_item_image;
        this.cm_item_price = cm_item_price;
        this.cm_item_desc = cm_item_desc;
    }
}
