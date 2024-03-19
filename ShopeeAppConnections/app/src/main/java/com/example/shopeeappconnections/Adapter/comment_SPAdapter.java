package com.example.shopeeappconnections.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopeeappconnections.Model.comment_SP;
import com.example.shopeeappconnections.R;

import java.util.ArrayList;

public class comment_SPAdapter extends RecyclerView.Adapter<comment_SPAdapter.MyViewHolder> {

    private ArrayList<comment_SP> commentSps;
    private Context context;

    // Constructor to initialize the adapter with data and context
    public comment_SPAdapter(Context context, ArrayList<comment_SP> commentSps) {
        this.context = context;
        this.commentSps = commentSps;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment_sp, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Bind data to UI elements
        comment_SP comment = commentSps.get(position);
        holder.textViewComment.setText(comment.getContent());
    }

    @Override
    public int getItemCount() {
        return commentSps.size();
    }

    // ViewHolder class to hold references to the UI elements
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewComment;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize UI elements from the item layout
            textViewComment = itemView.findViewById(R.id.txtcontent);
        }
    }
}
