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
import com.example.shopeeappconnections.Model.ProductSale;
import com.example.shopeeappconnections.R;

import java.util.ArrayList;

public class ProductSaleAdapter extends RecyclerView.Adapter<ProductSaleAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<ProductSale> productSaleAdapterArrayList; // Change the type to ArrayList<ProductDeals>

    public ProductSaleAdapter(Context context, ArrayList<ProductSale> productList) {
        this.context = context;
        this.productSaleAdapterArrayList = productList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_saleproduct, parent, false);
        return new MyViewHolder(v);
    }
    public void setProductList(ArrayList<ProductSale> productList) {
        this.productSaleAdapterArrayList = productList;
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ProductSale productSale = productSaleAdapterArrayList.get(position);

        // Set data to the views in the ViewHolder
        holder.productnameTextView.setText(productSale.getProductName());
//        holder.price.setText(String.valueOf(productDeals.getPrice())+"-"+productDeals.getGiaGiamTheoKhungGio());
        holder.price.setText(String.valueOf(productSale.getDiscountedPrice()));
//        holder.GiaGiamTheoKhungGio.setText(String.valueOf(productDeals.getGiaGiamTheoKhungGio()));
        String imageUrl = productSale.getLinkimage();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(context).load(imageUrl).into(holder.anhspgiamgia);
        } else {
            // If the image URL is not available, you can set a placeholder image or handle it accordingly
            holder.anhspgiamgia.setImageResource(R.drawable.baseline_drag_handle_24);
        }


        // Convert the int value to String before setting it as text
        holder.phantramgiamgia.setText(String.valueOf(productSale.getDiscountPercent()) + "%");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an intent to start DetailsActivity
                Intent intent = new Intent(context, DetailsActivity.class);

                // Pass relevant information as extras
                intent.putExtra("PRODUCT_ID", productSale.getProductId());
                intent.putExtra("PRODUCT_NAME", productSale.getProductName());
                intent.putExtra("PRODUCT_PRICE", productSale.getDiscountedPrice());
                intent.putExtra("PRODUCT_IMAGE", productSale.getLinkimage());
                // Start DetailsActivity
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return productSaleAdapterArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView productnameTextView;
        TextView price;
        //        TextView GiaGiamTheoKhungGio;
        TextView phantramgiamgia;

        ImageView anhspgiamgia;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            productnameTextView = itemView.findViewById(R.id.txtname);
            price = itemView.findViewById(R.id.itemgiabd);
//            GiaGiamTheoKhungGio = itemView.findViewById(R.id.textGiaGiamTheoKhungGio);
            phantramgiamgia = itemView.findViewById(R.id.phantramgiam);
            anhspgiamgia = itemView.findViewById(R.id.anhgiamgia);
        }
    }
}
