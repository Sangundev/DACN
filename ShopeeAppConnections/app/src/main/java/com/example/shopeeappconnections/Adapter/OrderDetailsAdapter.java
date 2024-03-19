package com.example.shopeeappconnections.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopeeappconnections.Model.Order_Deatails;
import com.example.shopeeappconnections.R;

import java.util.List;

public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.ViewHolder> {

    private List<Order_Deatails> detailsList;
    private Context context;
    public OrderDetailsAdapter(Context context,List<Order_Deatails> detailsList) {
        this.detailsList = detailsList;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_details_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order_Deatails details = detailsList.get(position);

        holder.textViewProduct.setText("Product ID: " + details.getProductid());
        holder.textViewNum.setText("Quantity: " + details.getNum());
        holder.textViewTotalMoney.setText("Total Money: " + details.getTt_money());
    }

    @Override
    public int getItemCount() {
        return detailsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewProduct;
        TextView textViewNum;
        TextView textViewTotalMoney;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewProduct = itemView.findViewById(R.id.textViewProduct);
            textViewNum = itemView.findViewById(R.id.textViewNum);
            textViewTotalMoney = itemView.findViewById(R.id.textViewTotalMoney);
            // Add other views if needed
        }
    }
}
