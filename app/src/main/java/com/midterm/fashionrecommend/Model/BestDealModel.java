package com.midterm.fashionrecommend.Model;

public class BestDealModel {
    private String menu_id,clothes_id, name, image;

    public BestDealModel() {
    }

    public BestDealModel(String menu_id, String clothes_id, String name, String image) {
        this.menu_id = menu_id;
        this.clothes_id = clothes_id;
        this.name = name;
        this.image = image;
    }

    public String getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }

    public String getClothes_id() {
        return clothes_id;
    }

    public void setClothes_id(String clothes_id) {
        this.clothes_id = clothes_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
