package com.example.shopeeappconnections.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopeeappconnections.Adapter.HotproductAdapter;
import com.example.shopeeappconnections.Connection.DatabaseConnection;
import com.example.shopeeappconnections.Model.Product;
import com.example.shopeeappconnections.Model.StoreViewModel;
import com.example.shopeeappconnections.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HotStoreFragment extends Fragment {


     RecyclerView recyclerView;
    HotproductAdapter productAdapter;
    ArrayList<Product> productList;
    private StoreViewModel storeViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        storeViewModel = new ViewModelProvider(requireActivity()).get(StoreViewModel.class);

        // Retrieve the storeid
        String storeid = storeViewModel.getStoreId();

        View view = inflater.inflate(R.layout.fragment_hot_store, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewhotpd);


        productList = getproductHot();

        productAdapter = new HotproductAdapter(requireContext(),productList);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(productAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);


        return view;

    }

    private ArrayList<Product> getproductHot() {
        String storeId = storeViewModel.getStoreId();

        if (storeId != null) {
            ArrayList<Product> productList = new ArrayList<>();

            try (Connection connection = new DatabaseConnection().getCon()) {
                String query = "Select * from Product Where is_hot = 'true' and Userid = ?";

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