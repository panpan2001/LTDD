package com.midterm.fashionrecommend.Callback;

import com.midterm.fashionrecommend.Model.Order;

public interface ILoadTimeFromFirebaseListener {
    void onLoadTimeSuccess(Order order, long estimateTimeInMs);
    void onLoadTimeFailed(String message);
}
