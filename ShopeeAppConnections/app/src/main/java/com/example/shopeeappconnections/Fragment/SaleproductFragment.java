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

import com.example.shopeeappconnections.Adapter.ProductSaleAdapter;
import com.example.shopeeappconnections.Connection.DatabaseConnection;
import com.example.shopeeappconnections.Model.ProductSale;
import com.example.shopeeappconnections.Model.StoreViewModel;
import com.example.shopeeappconnections.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class SaleproductFragment extends Fragment {


    RecyclerView recyclerView;

    ProductSaleAdapter productAdapter;
    ArrayList<ProductSale> productList;
    private StoreViewModel storeViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        storeViewModel = new ViewModelProvider(requireActivity()).get(StoreViewModel.class);

        // Retrieve the storeid
        String storeid = storeViewModel.getStoreId();



             View view =  inflater.inflate(R.layout.fragment_saleproduct, container, false);
           recyclerView = view.findViewById(R.id.recyclerViewsale);

        productList = getproductSale();

        productAdapter = new ProductSaleAdapter(requireContext(),productList);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(productAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        return view;
    }

    private ArrayList<ProductSale> getproductSale() {
        String storeId = storeViewModel.getStoreId();

        if (storeId != null) {
            ArrayList<ProductSale> productList = new ArrayList<>();

            try (Connection connection = new DatabaseConnection().getCon()) {
                String query = "Select * from Product Where DiscountPercent > 0 and DiscountedPrice > 0 and Userid = ?";

                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, storeId);

                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                            String productId = resultSet.getString("ProductId");
                            String productName = resultSet.getString("ProductName");
                            float price = resultSet.getFloat("Price");
                            float discountedPrice = resultSet.getFloat("DiscountedPrice");
                            int discountPercent = resultSet.getInt("DiscountPercent");
                            String anhsanpham = resultSet.getString("Linkimage");

                            ProductSale product = new ProductSale(productId, productName, anhsanpham, price,discountedPrice,discountPercent);
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