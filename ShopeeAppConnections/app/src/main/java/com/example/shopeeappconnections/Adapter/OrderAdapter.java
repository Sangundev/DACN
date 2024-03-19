package com.example.shopeeappconnections.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopeeappconnections.Connection.DatabaseConnection;
import com.example.shopeeappconnections.Model.Order;
import com.example.shopeeappconnections.Model.Order_Deatails;
import com.example.shopeeappconnections.R;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private Context context;
    private List<Order> orderList;

    public OrderAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.txtOrderId.setText("Order ID: " + order.getOd_id());
        holder.txtOrderDate.setText("Date: " + order.getDate());
        holder.txtOrderDate.setText("Date: " + order.getDate());
        holder.buttonXemChiTiet.setOnClickListener(v -> showOrderDetailsPopup(order));

    }
    // Inside showOrderDetailsPopup method
    private void showOrderDetailsPopup(Order order) {
        DatabaseConnection databaseConnection = new DatabaseConnection();

        // Fetch order details using getDetails method
        ArrayList<Order_Deatails> detailsList = databaseConnection.getDetails(order.getOd_id());

        if (!detailsList.isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Order Details");

            View view = LayoutInflater.from(context).inflate(R.layout.order_details_popup, null);
            RecyclerView recyclerView = view.findViewById(R.id.recyclerViewOrderDetails);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            OrderDetailsAdapter orderDetailsAdapter = new OrderDetailsAdapter(context, detailsList);
            recyclerView.setAdapter(orderDetailsAdapter);

            builder.setView(view);
            builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        } else {
            Toast.makeText(context, "No details found for this order", Toast.LENGTH_SHORT).show();
        }
    }




    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtOrderId, Tongtien;
        TextView Nameproduct;
        TextView txtOrderDate;
        Button buttonXemChiTiet, buttonHuyDon;
            TextView Soluong;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            buttonXemChiTiet = itemView.findViewById(R.id.button7);
            buttonHuyDon = itemView.findViewById(R.id.huydon);
            txtOrderId = itemView.findViewById(R.id.txtOrderId);
            Nameproduct = itemView.findViewById(R.id.diachi);
            txtOrderDate = itemView.findViewById(R.id.txtOrderDate);
            // Add other TextViews if needed
        }
    }
}
