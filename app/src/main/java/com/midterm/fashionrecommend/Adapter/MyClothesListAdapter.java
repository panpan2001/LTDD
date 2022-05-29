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
import com.midterm.fashionrecommend.Database.CartDataSource;
import com.midterm.fashionrecommend.Database.CartDatabase;
import com.midterm.fashionrecommend.Database.LocalCartDataSource;
import com.midterm.fashionrecommend.Model.ClothesModel;
import com.midterm.fashionrecommend.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;

public class MyClothesListAdapter extends RecyclerView.Adapter<MyClothesListAdapter.MyViewHolder>{

    private Context context;
    private List<ClothesModel> clothesModelList;
    private CompositeDisposable compositeDisposable;
    private CartDataSource cartDataSource;

    public MyClothesListAdapter(Context context, List<ClothesModel> clothesModelList) {
        this.context = context;
        this.clothesModelList = clothesModelList;
        this.compositeDisposable = new CompositeDisposable();
        this.cartDataSource = new LocalCartDataSource(CartDatabase.getInstance(context).cartDAO());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_clothes_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(clothesModelList.get(position).getImage()).into(holder.img_food_image);
        holder.txt_food_price.setText(new StringBuilder("$")
                .append(clothesModelList.get(position).getPrice()));
        holder.txt_food_name.setText(new StringBuilder("")
                .append(clothesModelList.get(position).getName()));

        /*openAfter holder.setListener(new IRecyclerClickListener() {
            @Override
            public void onItemClickListener(View view, int pos) {
                Common.selectedClothes = clothesModelList.get(pos);
                Common.selectedClothes.setKey(String.valueOf(pos));
                EventBus.getDefault().postSticky(new FoodItemClick(true, clothesModelList.get(pos)));
            }
        });*/

        /*openAfter holder.img_cart.setOnClickListener(view -> {
            CartItem cartItem = new CartItem();
            cartItem.setUid(Common.currentUser.getUid());
            cartItem.setUserPhone(Common.currentUser.getPhone());

            cartItem.setClothesId(clothesModelList.get(position).getId());
            cartItem.setClothesName(clothesModelList.get(position).getName());
            cartItem.setClothesImage(clothesModelList.get(position).getImage());
            cartItem.setClothesPrice(Double.valueOf(String.valueOf(clothesModelList.get(position).getPrice())));
            cartItem.setClothesQuantity(1);
            cartItem.setClothesExtraPrice(0.0); // Because default we not choose size + addon so extra price is 0
            cartItem.setClothesAddon("Default");
            cartItem.setClothesSize("Default");

            cartDataSource.getItemWithAllOptionsInCart(Common.currentUser.getUid(),
                    cartItem.getClothesId(),
                    cartItem.getClothesSize(),
                    cartItem.getClothesAddon())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<CartItem>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onSuccess(CartItem cartItemFromDB) {
                            if(cartItemFromDB.equals(cartItem)) {
                                //Already in database, just update
                                cartItemFromDB.setClothesExtraPrice(cartItem.getClothesExtraPrice());
                                cartItemFromDB.setClothesAddon(cartItem.getClothesAddon());
                                cartItemFromDB.setClothesSize(cartItem.getClothesSize());
                                cartItemFromDB.setClothesQuantity(cartItemFromDB.getClothesQuantity() + cartItem.getClothesQuantity());

                                cartDataSource.updateCartItem(cartItemFromDB)
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(new SingleObserver<Integer>() {
                                            @Override
                                            public void onSubscribe(Disposable d) {

                                            }

                                            @Override
                                            public void onSuccess(Integer integer) {
                                                Toast.makeText(context, "Update Cart Success", Toast.LENGTH_SHORT).show();
                                                EventBus.getDefault().postSticky(new CounterCartEvent(true));
                                            }

                                            @Override
                                            public void onError(Throwable e) {
                                                Toast.makeText(context, "[UPDATE CART]"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            } else {
                                // Item not available in cart before, insert new
                                compositeDisposable.add(cartDataSource.insertOrReplaceAll(cartItem)
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(()->{
                                            Toast.makeText(context, "Add to Cart success", Toast.LENGTH_SHORT).show();
                                            EventBus.getDefault().postSticky(new CounterCartEvent(true));
                                        }, throwable -> {
                                            Toast.makeText(context, "[CART ERROR]"+throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                        }));
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            if (e.getMessage().contains("empty")){
                                //Default, if cart empty, this code will be fired
                                compositeDisposable.add(cartDataSource.insertOrReplaceAll(cartItem)
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(()->{
                                            Toast.makeText(context, "Add to Cart success", Toast.LENGTH_SHORT).show();
                                            EventBus.getDefault().postSticky(new CounterCartEvent(true));
                                        }, throwable -> {
                                            Toast.makeText(context, "[CART ERROR]"+throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                        }));
                            }else{
                                Toast.makeText(context, "[GET CART]"+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });*/
    }

    @Override
    public int getItemCount() {
        return clothesModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Unbinder unbinder;
        @BindView(R.id.txt_clothes_name)
        TextView txt_food_name;
        @BindView(R.id.txt_clothes_price)
        TextView txt_food_price;
        @BindView(R.id.img_clothes_image)
        ImageView img_food_image;
        @BindView(R.id.img_fav)
        ImageView img_fav;
        @BindView(R.id.img_quick_cart)
        ImageView img_cart;

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
}