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
import com.example.shopeeappconnections.Model.TheLoaiSp;
import com.example.shopeeappconnections.R;

import java.util.ArrayList;

public class GetcategoryAdapter extends RecyclerView.Adapter<GetcategoryAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<TheLoaiSp> theLoaiSps;

    public GetcategoryAdapter(Context context, ArrayList<TheLoaiSp> theLoaiSps) {
        this.context = context;
        this.theLoaiSps = theLoaiSps;
    }

    @NonNull
    @Override
    public GetcategoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_theloaisp, parent, false);
        return new GetcategoryAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GetcategoryAdapter.MyViewHolder holder, int position) {
        TheLoaiSp theLoaiSp = theLoaiSps.get(position);


        holder.categoryNameTextView.setText(theLoaiSp.getCategoryid());
        // Assuming you have appropriate methods in TheLoaiSp class to get the necessary data
        holder.categoryNameTextView.setText(theLoaiSp.getCategoryname());

        String imageUrl = theLoaiSp.getLinkanhtt();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(context).load(imageUrl).into(holder.categoryImage);
        } else {
            holder.categoryImage.setImageResource(R.drawable.agreement); // Set a placeholder image
        }

        // Set up click listener to open DetailsActivity or perform other actions
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Example: Open DetailsActivity with category information
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("CATEGORY_ID", theLoaiSp.getCategoryid());
                intent.putExtra("CATEGORY_NAME", theLoaiSp.getCategoryname());
                intent.putExtra("CATEGORY_IMAGE", theLoaiSp.getLinkanhtt());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return theLoaiSps.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView categoryNameTextView;
        ImageView categoryImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryNameTextView = itemView.findViewById(R.id.textCategoryName);
            categoryImage = itemView.findViewById(R.id.imageCategory);
        }
    }
}
