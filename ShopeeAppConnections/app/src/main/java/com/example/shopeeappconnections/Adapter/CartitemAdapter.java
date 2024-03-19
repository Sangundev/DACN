package com.example.shopeeappconnections.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopeeappconnections.Connection.DatabaseConnection;
import com.example.shopeeappconnections.Model.Cartitem;
import com.example.shopeeappconnections.R;

import java.util.ArrayList;
import java.util.List;

public class CartitemAdapter extends RecyclerView.Adapter<CartitemAdapter.MyViewHolder> {
    private final Context context;
    private final List<Cartitem> cartitemList;
    public List<Cartitem> selectedProducts;

    private final DatabaseConnection connect;

    private String userId;
    private OnCartItemCheckedChangeListener checkListener;

    public CartitemAdapter(Context context, List<Cartitem> cartitemList, OnCartItemCheckedChangeListener checkListener) {
        this.context = context;
        this.cartitemList = cartitemList;
        selectedProducts = new ArrayList<>();
        connect = new DatabaseConnection();
        this.checkListener = checkListener;
    }


    public interface OnCartItemCheckedChangeListener {
        void isCheckedItems(List<Cartitem> selectedItems);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_giohang, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Cartitem cartitem = cartitemList.get(position);

        holder.productNameTextView.setText(cartitem.getProductName());
        holder.quantityTextView.setText(String.format("%d", cartitem.getQuantity()));
        holder.priceTextView.setText(String.format("%.2f", cartitem.getPrice()));
        holder.totalPriceTextView.setText(String.format("%.2f", cartitem.getTotalPrice()));
        //holder.handleCheckbox(cartitem);
        holder.decreaseIncreaseListener(cartitem);
        holder.checkBox.setOnCheckedChangeListener((btn, isCheck) -> {
            if (holder.checkBox.isChecked()) {
                if (!selectedProducts.contains(cartitem)) {
                    selectedProducts.add(cartitem);
                }
            } else {
                selectedProducts.remove(cartitem);
            }
            checkListener.isCheckedItems(selectedProducts);
        });

        String imageUrl = cartitem.getLinkimage();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(context).load(imageUrl).into(holder.anhsp);
        } else {
            // If the image URL is not available, you can set a placeholder image or handle it accordingly
            holder.anhsp.setImageResource(R.drawable.baseline_drag_handle_24);
        }
        holder.xoagiohang.setOnClickListener(v -> removeItem(cartitem));
    }

    private void removeItem(Cartitem cartitem) {
        int position = cartitemList.indexOf(cartitem);
        if (position != -1) {
            // Remove the item from the database
            removeFromCart(cartitem.getUserId(), cartitem.getProductId());

            // Remove the item from the list and update the adapter
            cartitemList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, cartitemList.size());
        }
    }

    private void removeFromCart(String userId, int productId) {
        userId = saveUserIdToPreferences(context);
        // Assuming you have an instance of your database connection class
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.removeFromCart(userId, productId);
    }

    private String saveUserIdToPreferences(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        return preferences.getString("USER_ID", null);
    }

    public List<Cartitem> getSelectedProducts() {
        return selectedProducts;
    }


    @Override
    public int getItemCount() {
        return cartitemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // Declare your ViewHolder views here

        CheckBox checkBox;
        TextView productNameTextView;
        TextView priceTextView;
        TextView quantityTextView;
        ImageView decreaseImageView;
        ImageView increaseImageView;
        TextView totalPriceTextView;
        ImageView xoagiohang;

        ImageView anhsp;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialize your ViewHolder views here
            anhsp = itemView.findViewById(R.id.item_giohang_img);
            checkBox = itemView.findViewById(R.id.Checkproductcart);
            productNameTextView = itemView.findViewById(R.id.item_giohang_tensp);
            priceTextView = itemView.findViewById(R.id.item_giohang_gia);
            quantityTextView = itemView.findViewById(R.id.item_giohang_soluong);
            decreaseImageView = itemView.findViewById(R.id.item_giohang_tru);
            increaseImageView = itemView.findViewById(R.id.item_giohang_cong);
            totalPriceTextView = itemView.findViewById(R.id.item_giohang_tonggia);
            xoagiohang = itemView.findViewById(R.id.xoagiohang); // Add this line
        }

        /*private void handleCheckbox(Cartitem cartitem) {
            checkBox.setOnClickListener(v -> {
                if (checkBox.isChecked()) {
                    if (!selectedProducts.contains(cartitem)) {
                        selectedProducts.add(cartitem);
                    }
                } else {
                    selectedProducts.remove(cartitem);
                }
            });
        }*/

        public void decreaseIncreaseListener(Cartitem cartitem) {
            decreaseImageView.setOnClickListener(v -> updateQuantity(cartitem, -1));
            increaseImageView.setOnClickListener(v -> updateQuantity(cartitem, 1));
        }

        private void updateQuantity(Cartitem cartitem, int change) {
            int quantity = cartitem.getQuantity() + change;
            if (quantity > 0) {
                cartitem.setQuantity(quantity);
                quantityTextView.setText(String.format("%d", quantity));
                totalPriceTextView.setText(String.format("%.2f", cartitem.getPrice() * quantity));
                connect.updateCartItemQuantity(saveUserIdToPreferences(context), String.valueOf(cartitem.getProductId()), quantity);
            }
        }


    }
}