package com.midterm.fashionrecommend.Database;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "Cart", primaryKeys = {"uid", "clothesId", "clothesAddon", "clothesSize"})
public class CartItem {

    @NonNull
    @ColumnInfo(name = "clothesId")
    private String clothesId;

    @ColumnInfo(name = "clothesName")
    private String clothesName;

    @ColumnInfo(name = "clothesImage")
    private String clothesImage;

    @ColumnInfo(name = "clothesPrice")
    private double clothesPrice;

    @ColumnInfo(name = "clothesQuantity")
    private int clothesQuantity;

    @ColumnInfo(name = "userPhone")
    private String userPhone;

    @ColumnInfo(name = "clothesExtraPrice")
    private double clothesExtraPrice;

    @NonNull
    @ColumnInfo(name = "clothesAddon")
    private String clothesAddon;


    @NonNull
    @ColumnInfo(name = "clothesSize")
    private String clothesSize;

    @NonNull
    @ColumnInfo(name = "uid")
    private String uid;

    public CartItem(@NonNull String clothesId, String clothesName, String clothesImage, double clothesPrice,
                    int clothesQuantity, String userPhone, double clothesExtraPrice,
                    @NonNull String clothesAddon, @NonNull String clothesSize, @NonNull String uid) {
        this.clothesId = clothesId;
        this.clothesName = clothesName;
        this.clothesImage = clothesImage;
        this.clothesPrice = clothesPrice;
        this.clothesQuantity = clothesQuantity;
        this.userPhone = userPhone;
        this.clothesExtraPrice = clothesExtraPrice;
        this.clothesAddon = clothesAddon;
        this.clothesSize = clothesSize;
        this.uid = uid;
    }

    public CartItem() {
    }

    @NonNull
    public String getClothesId() {
        return clothesId;
    }

    public void setClothesId(@NonNull String clothesId) {
        this.clothesId = clothesId;
    }

    public String getClothesName() {
        return clothesName;
    }

    public void setClothesName(String clothesName) {
        this.clothesName = clothesName;
    }

    public String getClothesImage() {
        return clothesImage;
    }

    public void setClothesImage(String clothesImage) {
        this.clothesImage = clothesImage;
    }

    public double getClothesPrice() {
        return clothesPrice;
    }

    public void setClothesPrice(double clothesPrice) {
        this.clothesPrice = clothesPrice;
    }

    public int getClothesQuantity() {
        return clothesQuantity;
    }

    public void setClothesQuantity(int clothesQuantity) {
        this.clothesQuantity = clothesQuantity;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public double getClothesExtraPrice() {
        return clothesExtraPrice;
    }

    public void setClothesExtraPrice(double clothesExtraPrice) {
        this.clothesExtraPrice = clothesExtraPrice;
    }

    @NonNull
    public String getClothesAddon() {
        return clothesAddon;
    }

    public void setClothesAddon(@NonNull String clothesAddon) {
        this.clothesAddon = clothesAddon;
    }

    @NonNull
    public String getClothesSize() {
        return clothesSize;
    }

    public void setClothesSize(@NonNull String clothesSize) {
        this.clothesSize = clothesSize;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    //Ctrl+O
    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof CartItem))
            return false;
        CartItem cartItem = (CartItem) obj;
        return cartItem.getClothesId().equals(this.clothesId)&&
                cartItem.getClothesAddon().equals(this.clothesAddon)&&
                cartItem.getClothesSize().equals(this.clothesSize);
    }
}
