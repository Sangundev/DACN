package com.example.shopeeappconnections;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopeeappconnections.Adapter.HotproductAdapter;
import com.example.shopeeappconnections.Connection.DatabaseConnection;
import com.example.shopeeappconnections.Model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HotActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Product> productList;
    HotproductAdapter hotproductAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot);

        ImageView quaylai = findViewById(R.id.imageView2);

        quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        productList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerViewhot);

        // Create an instance of DatabaseConnection
        DatabaseConnection con = new DatabaseConnection();

        // Call the method to fetch and display hot products
        productList = getproductHot();

        // Initialize the adapter
        hotproductAdapter = new HotproductAdapter(this, productList);
        recyclerView.setAdapter(hotproductAdapter);

        // Use GridLayoutManager with 2 columns in a vertical orientation
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        // Notify the adapter that the data set has changed
        hotproductAdapter.notifyDataSetChanged();
    }

    private ArrayList<Product> getproductHot() {
        ArrayList<Product> productList = new ArrayList<>();

        try (Connection connection = new DatabaseConnection().getCon()) {
            String query = "Select * from Product Where is_hot = 'true'";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
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
}
