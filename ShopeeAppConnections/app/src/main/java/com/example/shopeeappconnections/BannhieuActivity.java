package com.example.shopeeappconnections;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopeeappconnections.Adapter.BanNhieuAdapter;
import com.example.shopeeappconnections.Connection.DatabaseConnection;
import com.example.shopeeappconnections.Model.Product;

import java.util.ArrayList;

public class BannhieuActivity extends AppCompatActivity {
    private RecyclerView recyclerViewBanNhieu;
    private BanNhieuAdapter banNhieuAdapter;
    private ArrayList<Product> productList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bannhieu);

        // Initialize RecyclerView and its adapter
        recyclerViewBanNhieu = findViewById(R.id.bannhieu);
        productList = new ArrayList<>();
        banNhieuAdapter = new BanNhieuAdapter(this, productList);
        recyclerViewBanNhieu.setAdapter(banNhieuAdapter);
        recyclerViewBanNhieu.setLayoutManager(new LinearLayoutManager(this));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        recyclerViewBanNhieu = findViewById(R.id.bannhieu);
        recyclerViewBanNhieu.setLayoutManager(gridLayoutManager);


        DatabaseConnection databaseConnection = new DatabaseConnection();
        productList = databaseConnection.getProductsSortedByQuantitySold();

        // Update the adapter with the new data
        banNhieuAdapter.setProductList(productList);
    }
}
