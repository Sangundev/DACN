package com.example.shopeeappconnections.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopeeappconnections.DetailsActivity;
import com.example.shopeeappconnections.Model.Product;
import com.example.shopeeappconnections.R;

import java.util.ArrayList;

public class BanNhieuAdapter extends RecyclerView.Adapter<BanNhieuAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Product> productList;

    public BanNhieuAdapter(Context context, ArrayList<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public BanNhieuAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.itembannhieu, parent, false);
        return new BanNhieuAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BanNhieuAdapter.MyViewHolder holder, int position) {
        Product product = productList.get(position);
//        holder.productidTextView.setText(product.getProductId());
        holder.productnameTextView.setText(product.getProductName());
        float price = product.getPrice();
        holder.price.setText("Gia: " + price + "d");
        String imageUrl = product.getLinkimage();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(context).load(imageUrl).into(holder.itemsp_image);
        } else {
            // If the image URL is not available, you can set a placeholder image or handle it accordingly
            holder.itemsp_image.setImageResource(R.drawable.baseline_drag_handle_24);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an intent to start DetailsActivity
                Intent intent = new Intent(context, DetailsActivity.class);

                // Pass relevant information as extras
                intent.putExtra("PRODUCT_ID", product.getProductId());
                intent.putExtra("PRODUCT_NAME", product.getProductName());
                intent.putExtra("PRODUCT_PRICE", product.getPrice());
                intent.putExtra("PRODUCT_IMAGE", product.getLinkimage());
                // Start DetailsActivity
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView productidTextView;
        TextView productnameTextView;

        TextView price;

        ImageView itemsp_image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
//            productidTextView = itemView.findViewById(R.id.textprodcutid);
            productnameTextView = itemView.findViewById(R.id.textproductname);
            itemsp_image =itemView.findViewById(R.id.itemsp_image);
            price = itemView.findViewById(R.id.itemsp_gia);
        }
    }
}
