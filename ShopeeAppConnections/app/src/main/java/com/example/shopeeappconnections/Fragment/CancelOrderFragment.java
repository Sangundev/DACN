package com.example.shopeeappconnections.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopeeappconnections.Adapter.HuydonproductAdapter;
import com.example.shopeeappconnections.Connection.DatabaseConnection;
import com.example.shopeeappconnections.Model.Order;
import com.example.shopeeappconnections.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CancelOrderFragment extends Fragment {

    RecyclerView damua;
    HuydonproductAdapter orderAdapter;
    List<Order> orderList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cancel_order, container, false);

        damua = view.findViewById(R.id.recyclerViewcanorder);
        orderList = getOrders(); // Replace this with your actual method to fetch orders

        orderAdapter = new HuydonproductAdapter(requireContext(), orderList);

        damua.setLayoutManager(new LinearLayoutManager(requireContext()));
        damua.setAdapter(orderAdapter);

        return view;
    }

    private List<Order> getOrders() {
        // Replace this with your actual method to fetch orders from a data source
        List<Order> orders = new ArrayList<>();

        // Replace "userId" with the actual way you retrieve the user's ID
        String userId = getUserIdFromPreferences();

        if (userId != null) {
            // Assuming you have a method to query orders based on user ID from your database
            // Modify your SQL query accordingly
            String query = "Select * from Orders where Od_name = ? and VoidanOder = 0";

            try (Connection connection = new DatabaseConnection().getCon();
                 PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, userId);

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int orderId = resultSet.getInt("Od_id");
                        String orderDate = resultSet.getString("Od_date");
                        int orderStatus = resultSet.getInt("Od_status");
                        String orderName = resultSet.getString("Od_name");

                        // Create an Order object and add it to the list
                        Order order = new Order(orderId, orderName, "", 0, "", "", orderDate, orderStatus, true, true);
                        orders.add(order);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return orders;
    }

    private String getUserIdFromPreferences() {
        // Replace this with your actual method to retrieve the user's ID
        SharedPreferences preferences = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        return preferences.getString("USER_ID", null);
    }
}