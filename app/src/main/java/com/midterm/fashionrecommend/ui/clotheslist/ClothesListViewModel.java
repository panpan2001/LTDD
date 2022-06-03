package com.midterm.fashionrecommend.ui.clotheslist;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.midterm.fashionrecommend.Common.Common;
import com.midterm.fashionrecommend.Model.ClothesModel;

import java.util.List;

public class ClothesListViewModel extends ViewModel {

    private MutableLiveData<List<ClothesModel>> mutableLiveDataClothesList;

    public ClothesListViewModel() {

    }

    public MutableLiveData<List<ClothesModel>> getMutableLiveDataClothesList() {
        if (mutableLiveDataClothesList == null) {
            mutableLiveDataClothesList = new MutableLiveData<>();
        }
        mutableLiveDataClothesList.setValue(Common.categorySelected.getClothes());
        if(Common.categorySelected.getClothes() != null) {
            System.out.println("11111111111111111111111111111111111111111111111");
        } else {
            System.out.println("1222222222222222222222222222222222222222222222222");
        }
        return mutableLiveDataClothesList;
    }
}