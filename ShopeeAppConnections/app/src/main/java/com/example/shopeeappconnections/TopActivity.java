package com.example.shopeeappconnections;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopeeappconnections.Adapter.TopAdapter;
import com.example.shopeeappconnections.Connection.DatabaseConnection;
import com.example.shopeeappconnections.Model.Product;

import java.util.ArrayList;

public class TopActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Product> productList;
    TopAdapter topAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);

        productList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerViewtop);
        topAdapter = new TopAdapter(this, productList);
        recyclerView.setAdapter(topAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ImageView quaylai = findViewById(R.id.imageView2);

        // Thiết lập sự kiện nhấp vào cho ImageView
        quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // Create an instance of DatabaseConnection
        DatabaseConnection con = new DatabaseConnection();

        // Call the method to fetch and display top products
        productList = con.getProductsSortedByQuantitySold();

        // Update the adapter with the new product list
        topAdapter.setProductList(productList);
        topAdapter.notifyDataSetChanged();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);


    }


}
