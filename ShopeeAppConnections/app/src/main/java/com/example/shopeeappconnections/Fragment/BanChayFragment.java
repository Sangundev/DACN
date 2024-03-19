package com.example.shopeeappconnections.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopeeappconnections.Adapter.TopAdapter;
import com.example.shopeeappconnections.Connection.DatabaseConnection;
import com.example.shopeeappconnections.Model.Product;
import com.example.shopeeappconnections.Model.StoreViewModel;
import com.example.shopeeappconnections.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BanChayFragment extends Fragment {

    private StoreViewModel storeViewModel;

    TextView banchay;

    TopAdapter productAdapter;
    RecyclerView recyclerView;
    ArrayList<Product> productList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        storeViewModel = new ViewModelProvider(requireActivity()).get(StoreViewModel.class);

        // Retrieve the storeid
        String storeid = storeViewModel.getStoreId();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ban_chay, container, false);

        // Find the TextView by its ID in the fragment's layout
        banchay = view.findViewById(R.id.banchay); // Replace 'R.id.textViewBanChay' with the actual ID of your TextView
        recyclerView = view.findViewById(R.id.recbanchay);

        productList = getproductbanchay();

        productAdapter = new TopAdapter(requireContext(), productList);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(productAdapter);
        // Set the text to the retrieved storeid
        banchay.setText(storeid);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);


        return view;
    }

    private ArrayList<Product> getproductbanchay() {
        String storeId = storeViewModel.getStoreId();

        if (storeId != null) {
            ArrayList<Product> productList = new ArrayList<>();

            try (Connection connection = new DatabaseConnection().getCon()) {
                String query = "WITH RankedProducts AS (\n" +
                        "    SELECT\n" +
                        "        a.ProductId,\n" +
                        "        a.ProductName,\n" +
                        "        a.Price,\n" +
                        "        a.Linkimage,\n" +
                        "        SUM(b.num) as TotalSold,\n" +
                        "        b.Storeid,\n" +
                        "        ROW_NUMBER() OVER (PARTITION BY b.storeid ORDER BY SUM(b.num) DESC) AS RowNum\n" +
                        "    FROM\n" +
                        "        Product a\n" +
                        "    JOIN\n" +
                        "        Order_detail b ON a.ProductId = b.ProductId\n" +
                        "    GROUP BY\n" +
                        "        a.ProductId, a.ProductName, a.Price, a.Linkimage, b.storeid\n" +
                        ")\n" +
                        "SELECT\n" +
                        "    ProductId,\n" +
                        "    ProductName,\n" +
                        "    Price,\n" +
                        "    Linkimage,\n" +
                        "    TotalSold,\n" +
                        "    Storeid\n" +
                        "FROM\n" +
                        "    RankedProducts\n" +
                        "WHERE\n" +
                        "    StoreId = ? AND RowNum <= 4;";

                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, storeId);

                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                            String productId = resultSet.getString("ProductId");
                            String productName = resultSet.getString("ProductName");
                            float price = resultSet.getFloat("Price");
                            String anhsanpham = resultSet.getString("Linkimage");

                            Product product = new Product(productId, productName, price, anhsanpham);
                            productList.add(product);
                        }
                    }
                }
            } catch (SQLException e) {
                Log.e("Error", "Error while retrieving products: " + e.getMessage());
                e.printStackTrace();
            }

            return productList;
        }

        return new ArrayList<>();  // Return an empty list if storeId is null
    }

}