package com.midterm.fashionrecommend.ui.clotheslist;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.midterm.fashionrecommend.Adapter.MyClothesListAdapter;
import com.midterm.fashionrecommend.Common.Common;
import com.midterm.fashionrecommend.EventBus.MenuItemBack;
import com.midterm.fashionrecommend.Model.ClothesModel;
import com.midterm.fashionrecommend.R;
import com.midterm.fashionrecommend.databinding.FragmentClothesListBinding;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ClothesListFragment extends Fragment {

    private FragmentClothesListBinding binding;

    private ClothesListViewModel clotheListViewModel;

    Unbinder unbinder;
    @BindView(R.id.recycler_clothes_list)
    RecyclerView recycler_clothes_list;

    LayoutAnimationController layoutAnimationController;
    MyClothesListAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        clotheListViewModel =
                new ViewModelProvider(this).get(ClothesListViewModel.class);

        View root  = inflater.inflate(R.layout.fragment_clothes_list, container, false);

        unbinder = ButterKnife.bind(this, root);
        initView();
        System.out.println("22222222222222222222222222222222222222");
        clotheListViewModel.getMutableLiveDataClothesList().observe(getViewLifecycleOwner(), new Observer<List<ClothesModel>>() {
            @Override
            public void onChanged(List<ClothesModel> clothesModels) {
                if(clothesModels != null) {
                    System.out.println("11111111111111111111111111111111111111111111111");
                    adapter = new MyClothesListAdapter(getContext(), clothesModels);
                    recycler_clothes_list.setAdapter(adapter);
                    recycler_clothes_list.setLayoutAnimation(layoutAnimationController);
                }
            }
        });
        System.out.println("3333333333333333333333333333");
        return root;
    }

    private void initView() {
        setHasOptionsMenu(true);

        ((AppCompatActivity)getActivity())
                .getSupportActionBar()
                .setTitle(Common.categorySelected.getName());

        recycler_clothes_list.setHasFixedSize(true);
        recycler_clothes_list.setLayoutManager(new LinearLayoutManager(getContext()));

        layoutAnimationController = AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_item_from_left);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

        //Event
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                startSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        //Clear text when click to clear button on search view
        ImageView closeButton = (ImageView) searchView.findViewById(androidx.appcompat.R.id.search_close_btn);
        closeButton.setOnClickListener(view -> {
            EditText ed = (EditText) searchView.findViewById(androidx.appcompat.R.id.search_src_text);
            //Clear text
            ed.setText("");
            //Clear query
            searchView.setQuery("", false);
            //Collapse the action view
            searchView.onActionViewCollapsed();
            //Collapse the search widget
            menuItem.collapseActionView();
            //Restore result to original
            clotheListViewModel.getMutableLiveDataClothesList();
        });
    }

    private void startSearch(String s) {
        List<ClothesModel> resultList = new ArrayList<>();
        for(int i=0; i<Common.categorySelected.getClothes().size(); i++) {
            ClothesModel clothesModel = Common.categorySelected.getClothes().get(i);
            if(clothesModel.getName().toLowerCase().contains(s)) {
                resultList.add(clothesModel);
            }
        }

        clotheListViewModel.getMutableLiveDataClothesList().setValue(resultList);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().postSticky(new MenuItemBack());
        super.onDestroy();
    }
}