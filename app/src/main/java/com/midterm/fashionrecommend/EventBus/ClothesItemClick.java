package com.midterm.fashionrecommend.EventBus;

import com.midterm.fashionrecommend.Model.ClothesModel;

public class ClothesItemClick {
    private boolean success;
    private ClothesModel clothesModel;

    public ClothesItemClick(boolean success, ClothesModel clothesModel) {
        this.success = success;
        this.clothesModel = clothesModel;
    }

    public ClothesItemClick() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ClothesModel getClothesModel() {
        return clothesModel;
    }

    public void setClothesModel(ClothesModel ClothesModel) {
        this.clothesModel = ClothesModel;
    }
}

