package com.midterm.fashionrecommend.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.midterm.fashionrecommend.Callback.IRecyclerClickListener;
import com.midterm.fashionrecommend.Common.Common;
import com.midterm.fashionrecommend.Model.CategoryModel;
import com.midterm.fashionrecommend.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyCategoriesAdapter extends RecyclerView.Adapter<MyCategoriesAdapter.MyViewHolder> {

    Context context;
    List<CategoryModel> categoryModelList;

    public MyCategoriesAdapter(Context context, List<CategoryModel> list) {
        this.context = context;
        this.categoryModelList = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_category_item
                , parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(categoryModelList.get(position).getImage()).into(holder.category_image);
        holder.category_name.setText(new StringBuilder(categoryModelList.get(position).getName()));

        holder.setListener(new IRecyclerClickListener() {
            @Override
            public void onItemClickListener(View view, int pos) {
               //openAfter Common.categorySelected = categoryModelList.get(pos);
               //openAfter EventBus.getDefault().postSticky(new CategoryClick(true, categoryModelList.get(pos)));
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    public List<CategoryModel> getListCategory() {
        return categoryModelList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Unbinder unbinder;
        @BindView(R.id.img_category)
        ImageView category_image;
        @BindView(R.id.txt_category)
        TextView category_name;

        IRecyclerClickListener listener;

        public void setListener(IRecyclerClickListener listener) {
            this.listener = listener;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClickListener(view, getAdapterPosition());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(categoryModelList.size() == 1)
            return Common.DEFAULT_COLUMN_COUNT;
        else {
            if(categoryModelList.size() % 2 == 0)
                return Common.DEFAULT_COLUMN_COUNT;
            else
                return (position > 1 && position == categoryModelList.size()-1)?Common.FULL_WIDTH_COLUMN:Common.DEFAULT_COLUMN_COUNT;
        }
    }
}
