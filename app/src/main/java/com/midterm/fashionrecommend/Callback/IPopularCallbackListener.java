package com.midterm.fashionrecommend.Callback;

import com.midterm.fashionrecommend.Model.PopularCategoryModel;

import java.util.List;

public interface IPopularCallbackListener {
    void onPopularLoadSuccess(List<PopularCategoryModel> popularCategoryModelList);
    void onPopularLoadFailed(String message);
}
