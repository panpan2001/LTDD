package com.midterm.fashionrecommend.Database;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface CartDataSource {

    Flowable<List<CartItem>> getAllCart(String uid);

    Single<Integer> countItemInCart(String uid);

    Single<Double> sumPriceInCart(String uid);

    Single<CartItem> getItemInCart(String clothesId, String uid);

    Completable insertOrReplaceAll(CartItem... cardItems);

    Single<Integer> updateCartItem(CartItem cartItem);

    Single<Integer> deleteCartItem(CartItem cartItem);

    Single<Integer> cleanCart(String uid);

    Single<CartItem> getItemWithAllOptionsInCart(String uid, String clothesId, String clothesSize, String clothesAddon);

}