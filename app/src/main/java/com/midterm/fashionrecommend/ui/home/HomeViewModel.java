package com.midterm.fashionrecommend.ui.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.midterm.fashionrecommend.Callback.IBestDealCallbackListener;
import com.midterm.fashionrecommend.Callback.IPopularCallbackListener;
import com.midterm.fashionrecommend.Common.Common;
import com.midterm.fashionrecommend.Model.BestDealModel;
import com.midterm.fashionrecommend.Model.PopularCategoryModel;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel  implements IBestDealCallbackListener, IPopularCallbackListener {

    private MutableLiveData<List<BestDealModel>> bestDealList;
    private MutableLiveData<List<PopularCategoryModel>> popularCategoryList;
    private MutableLiveData<String> messageError;
    private IPopularCallbackListener popularCallbackListener;
    private IBestDealCallbackListener bestDealCallbackListener;

    public HomeViewModel() {
        popularCallbackListener = this;
        bestDealCallbackListener = this;
    }

    public MutableLiveData<List<BestDealModel>> getBestDealList() {
        if(bestDealList == null) {
            bestDealList = new MutableLiveData<>();
            messageError = new MutableLiveData<>();
            loadBestDealList();
        }
        return bestDealList;
    }

    private void loadBestDealList() {
        List<BestDealModel> tempList = new ArrayList<>();
        DatabaseReference bestDealRef = FirebaseDatabase.getInstance().getReference(Common.BEST_DEALS_REF);
        //executing that method once, it stops listening
        bestDealRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    BestDealModel model = dataSnapshot.getValue(BestDealModel.class);
                    tempList.add(model);
                }
                bestDealCallbackListener.onBestDealLoadSuccess(tempList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                bestDealCallbackListener.onBestDealLoadFailed(error.getMessage());
            }
        });
    }

    @Override
    public void onBestDealLoadSuccess(List<BestDealModel> bestDealModels) {
        bestDealList.setValue(bestDealModels);
    }

    @Override
    public void onBestDealLoadFailed(String message) {
        messageError.setValue(message);
    }

    public MutableLiveData<List<PopularCategoryModel>> getPopularCategoryList(){
        if(popularCategoryList == null) {
            popularCategoryList = new MutableLiveData<>();
            messageError = new MutableLiveData<>();
            loadPopularCategoryList();
        }
        return popularCategoryList;
    }

    private void loadPopularCategoryList() {
        List<PopularCategoryModel> tempList = new ArrayList<>();
        DatabaseReference popularRef = FirebaseDatabase.getInstance().getReference(Common.POPULAR_CATEGORY_REF);
        popularRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    PopularCategoryModel model = dataSnapshot.getValue(PopularCategoryModel.class);
                    tempList.add(model);
                }
                popularCallbackListener.onPopularLoadSuccess(tempList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                popularCallbackListener.onPopularLoadFailed(error.getMessage());
            }
        });
    }

    @Override
    public void onPopularLoadSuccess(List<PopularCategoryModel> popularCategoryModelList) {
        popularCategoryList.setValue(popularCategoryModelList);
    }

    @Override
    public void onPopularLoadFailed(String message) {
        messageError.setValue(message);
    }
}