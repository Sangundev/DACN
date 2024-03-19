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
import com.example.shopeeappconnections.DetailStoreActivity;
import com.example.shopeeappconnections.Model.User;
import com.example.shopeeappconnections.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {
    private List<User> userList;
    private Context context;

    public UserAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user = userList.get(position);

        holder.textView.setText(user.getFullname());

        String imageUrl = user.getLinkimage();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(context).load(imageUrl).into(holder.imageView);
        } else {
            // If the image URL is not available, you can set a placeholder image or handle it accordingly
            holder.imageView.setImageResource(R.drawable.baseline_drag_handle_24);
        }

        // Set click listener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an intent to start DetailsActivity
                Intent intent = new Intent(context, DetailStoreActivity.class);

                // Pass relevant information as extras
                intent.putExtra("USER_ID", user.getIduser());
                intent.putExtra("USER_NAME", user.getFullname());
                intent.putExtra("USER_adress", user.getDiachi());
                intent.putExtra("USER_IMAGE", user.getLinkimage());
                // Start DetailsActivity
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageshop);
            textView = itemView.findViewById(R.id.shopename);
        }
    }

}
