package com.example.shopeeappconnections;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopeeappconnections.Adapter.BellAdapter;
import com.example.shopeeappconnections.Connection.DatabaseConnection;
import com.example.shopeeappconnections.Model.Bellindex;

import java.util.ArrayList;

public class BellActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Bellindex> productList;

    BellAdapter allproductAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bell_activity);
        ImageView quaylai = findViewById(R.id.imageView2);

        // Thiết lập sự kiện nhấp vào cho ImageView
        quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        productList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerViewbell);
        allproductAdapter = new BellAdapter(this, productList);
        recyclerView.setAdapter(allproductAdapter);


        // Set the adapter to your RecyclerView
        recyclerView.setAdapter(allproductAdapter);

        DatabaseConnection con = new DatabaseConnection();

        // Call the method to fetch and display top products
        productList = con.getbell();

        // Update the adapter with the new product list
        allproductAdapter.setProductList(productList);
        allproductAdapter.notifyDataSetChanged();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

    }
}
