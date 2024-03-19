package com.example.shopeeappconnections.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopeeappconnections.DetailsActivity;
import com.example.shopeeappconnections.Model.Product;
import com.example.shopeeappconnections.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AllproductAdapter extends RecyclerView.Adapter<AllproductAdapter.MyViewHolder> implements Filterable {
    private Context context;
    private ArrayList<Product> productList;
    private ArrayList<Product> filteredList; // Added this line

    public AllproductAdapter(Context context, ArrayList<Product> productList) {
        this.context = context;
        this.productList = productList;
        this.filteredList = new ArrayList<>(productList); // Added this line
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.productitem, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Product product = filteredList.get(position);
        holder.productnameTextView.setText(product.getProductName());
        float price = product.getPrice();
        holder.price.setText("Gia: " + price + "d");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("PRODUCT_ID", product.getProductId());
                intent.putExtra("PRODUCT_NAME", product.getProductName());
                intent.putExtra("PRODUCT_PRICE", product.getPrice());
                intent.putExtra("PRODUCT_IMAGE", product.getLinkimage());
                context.startActivity(intent);
            }
        });
        String imageUrl = product.getLinkimage();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(context).load(imageUrl).into(holder.imageView);
        } else {
            // If the image URL is not available, you can set a placeholder image or handle it accordingly
            holder.imageView.setImageResource(R.drawable.baseline_drag_handle_24);
        }
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView productnameTextView;
        TextView price;

        ImageView imageView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.itemsp_image);
            productnameTextView = itemView.findViewById(R.id.textproductname);
            price = itemView.findViewById(R.id.itemsp_gia);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String query = charSequence.toString().toLowerCase().trim();
                List<Product> filtered = new ArrayList<>();

                for (Product product : productList) {
                    // Convert both product name and query to lowercase for case-insensitive comparison
                    String productName = product.getProductName().toLowerCase();

                    if (productName.contains(query)
                            || String.valueOf(product.getPrice()).contains(query)) {
                        filtered.add(product);
                    }
                }

                FilterResults results = new FilterResults();
                results.values = filtered;
                return results;
            }


            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredList.clear();
                filteredList.addAll((List<Product>) filterResults.values);
                notifyDataSetChanged();
            }
        };
    }
    // Add this method to your AllproductAdapter class
    public void sortByPriceAscending() {
        Collections.sort(productList, new Comparator<Product>() {
            @Override
            public int compare(Product product1, Product product2) {
                // Compare products based on their prices in ascending order
                return Float.compare(product1.getPrice(), product2.getPrice());
            }
        });
        notifyDataSetChanged();
    }



    public void updateList(List<Product> newList) {
        productList = new ArrayList<>(newList);
        filteredList.clear();
        filteredList.addAll(productList);
        notifyDataSetChanged();
    }
}
