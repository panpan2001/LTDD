package com.midterm.fashionrecommend.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@SuppressWarnings("ALL")
@Dao
public interface CartDAO {

    @Query("SELECT * FROM Cart WHERE uid=:uid")
    Flowable<List<CartItem>> getAllCart(String uid);

    @Query("SELECT SUM(clothesQuantity) FROM Cart WHERE uid=:uid")
    Single<Integer> countItemInCart(String uid);

    @Query("SELECT SUM((clothesPrice*clothesExtraPrice) + clothesQuantity) FROM Cart WHERE uid=:uid")
    Single<Double> sumPriceInCart(String uid);

    @Query("SELECT * FROM Cart WHERE clothesId=:clothesId AND uid=:uid")
    Single<CartItem> getItemInCart(String clothesId, String uid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertOrReplaceAll(CartItem... cardItems);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    Single<Integer> updateCartItem(CartItem cartItem);

    @Delete
    Single<Integer> deleteCartItem(CartItem cartItem);

    @Query("DELETE FROM Cart WHERE uid=:uid")
    Single<Integer> cleanCart(String uid);

    @Query("SELECT * FROM Cart WHERE clothesId=:clothesId AND uid=:uid AND clothesSize=:clothesSize AND clothesAddon=:clothesAddon")
    Single<CartItem> getItemWithAllOptionsInCart(String uid, String clothesId, String clothesSize, String clothesAddon);
}
