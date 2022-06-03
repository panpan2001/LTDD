package com.midterm.fashionrecommend.Model;

import java.util.List;

public class CategoryModel {
    private String menu_id, name, image;
    List<ClothesModel> clothes;

    public CategoryModel() {
    }

    public CategoryModel(String menu_id, String name, String image, List<ClothesModel> clothes) {
        this.menu_id = menu_id;
        this.name = name;
        this.image = image;
        this.clothes = clothes;
    }

    public String getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
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

    public List<ClothesModel> getClothes() {
        return clothes;
    }

    public void setClothes(List<ClothesModel> clothes) {
        this.clothes = clothes;
    }
}
