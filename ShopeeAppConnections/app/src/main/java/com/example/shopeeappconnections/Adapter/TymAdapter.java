package com.example.shopeeappconnections.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopeeappconnections.Connection.DatabaseConnection;
import com.example.shopeeappconnections.Model.Heart;
import com.example.shopeeappconnections.R;

import java.math.BigDecimal;
import java.util.List;

public class TymAdapter extends RecyclerView.Adapter<TymAdapter.MyViewHolder> {

    private List<Heart> heartList;
    private Context context;

    public TymAdapter(Context context, List<Heart> heartList) {
        this.context = context;
        this.heartList = heartList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tym, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Heart heart = heartList.get(position);

        // Set data to views
        holder.itemTymTenSp.setText(heart.getProductName());
        holder.itemTymGia.setText(String.valueOf(heart.getPrice()));
        String imageUrl = heart.getLinkimage();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(context).load(imageUrl).into(holder.itemTymImg);
        } else {
            // If the image URL is not available, you can set a placeholder image or handle it accordingly
            holder.itemTymImg.setImageResource(R.drawable.baseline_drag_handle_24);
        }

        holder.addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve product information
                // Retrieve product information
                // Retrieve product information
                String productId = String.valueOf(heart.getProductid());
                String productName = heart.getProductName();

// Convert float price to BigDecimal
                BigDecimal price = BigDecimal.valueOf(heart.getPrice());

// Get user ID from preferences
                String userId = getUserIdFromPreferences(context);

// Call addToCart method in DatabaseConnection
                DatabaseConnection databaseConnection = new DatabaseConnection();
                databaseConnection.addToCart(userId, productId, productName, price, 1, "img_1", heart.getLinkimage());

// Optionally, provide feedback to the user (e.g., show a toast)
                Toast.makeText(context, "Item added to cart successfully", Toast.LENGTH_SHORT).show();


            }
        });

    }

    public static String getUserIdFromPreferences(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        return preferences.getString("USER_ID", null);
    }

    @Override
    public int getItemCount() {
        return heartList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView itemTymImg;
        TextView itemTymTenSp;
        TextView itemTymGia;
        Button addtocart;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            addtocart = itemView.findViewById(R.id.button2);
            itemTymImg = itemView.findViewById(R.id.item_tym_img);
            itemTymTenSp = itemView.findViewById(R.id.item_tym_tensp);
            itemTymGia = itemView.findViewById(R.id.item_tym_gia);
        }
    }
}
