package com.example.shopeeappconnections;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopeeappconnections.Adapter.ProductSaleAdapter;
import com.example.shopeeappconnections.Connection.DatabaseConnection;
import com.example.shopeeappconnections.Model.ProductSale;

import java.util.ArrayList;

public class SaleActivity extends AppCompatActivity {
    ProductSaleAdapter productSaleAdapter;
    ArrayList<ProductSale> productSales;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale);

        productSales = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerViewsale);
        productSaleAdapter = new ProductSaleAdapter(this, productSales);
        recyclerView.setAdapter(productSaleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DatabaseConnection con = new DatabaseConnection();
        ImageView quaylai = findViewById(R.id.imageView2);

        // Thiết lập sự kiện nhấp vào cho ImageView
        quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // Call the method to fetch and display top products
        productSales = con.getRecentProductSale();

        // Update the adapter with the new product list
        productSaleAdapter.setProductList(productSales);
        productSaleAdapter.notifyDataSetChanged();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
    }
}
