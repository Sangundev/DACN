package com.example.shopeeappconnections.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopeeappconnections.Model.Voucher;
import com.example.shopeeappconnections.R;

import java.util.ArrayList;

public class VoucherAdapter extends RecyclerView.Adapter<VoucherAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Voucher> voucherList;
    private int selectedPosition = RecyclerView.NO_POSITION;

    public VoucherAdapter(Context context, ArrayList<Voucher> voucherList) {
        this.context = context;
        this.voucherList = voucherList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_voucher, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Voucher voucher = voucherList.get(position);

        // Bind data to the ViewHolder views
        holder.codeTextView.setText(voucher.getCode());
        holder.discountTextView.setText(String.valueOf(voucher.getDiscountOercent()));

        // Highlight the selected item
    }

    @Override
    public int getItemCount() {
        return voucherList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView codeTextView;
        TextView discountTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            codeTextView = itemView.findViewById(R.id.textViewCode);
            discountTextView = itemView.findViewById(R.id.discountTextView);

            // Set the click listener for the item
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // Get the adapter position of the clicked item
            int position = getAdapterPosition();

            // Update the selected position
            setSelectedPosition(position);
        }
    }

    // Getter method for the selected position
    public int getSelectedPosition() {
        return selectedPosition;
    }

    // Setter method to update the selected position
    public void setSelectedPosition(int position) {
        selectedPosition = position;
        notifyDataSetChanged(); // Notify the adapter that the data has changed to trigger a UI update
    }
}
