package com.example.shopeeappconnections;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopeeappconnections.Adapter.ProductDealsAdapter;
import com.example.shopeeappconnections.Connection.DatabaseConnection;
import com.example.shopeeappconnections.Model.ProductDeals;

import java.util.ArrayList;

public class DealsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProductDealsAdapter productDealsAdapter;

    ArrayList<ProductDeals> productDealsList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deals);
        ImageView quaylai = findViewById(R.id.imageView2);

        // Thiết lập sự kiện nhấp vào cho ImageView
        quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        productDealsList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerViewdeal);
        productDealsAdapter = new ProductDealsAdapter(this, productDealsList);
        recyclerView.setAdapter(productDealsAdapter);

        // Create an instance of DatabaseConnection
        DatabaseConnection con = new DatabaseConnection();

        // Call the method to fetch and display top products
        productDealsList = con.getDealsProducts();
        productDealsAdapter.setProductList(productDealsList);
        productDealsAdapter.notifyDataSetChanged();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
    }
}
