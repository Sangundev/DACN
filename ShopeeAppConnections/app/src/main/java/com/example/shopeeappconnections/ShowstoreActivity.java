package com.example.shopeeappconnections;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopeeappconnections.Adapter.UserAdapter;
import com.example.shopeeappconnections.Connection.DatabaseConnection;
import com.example.shopeeappconnections.Model.User;

import java.util.ArrayList;
import java.util.List;

public class ShowstoreActivity extends AppCompatActivity {
    RecyclerView RecyclerViewstore;
    UserAdapter userAdapter;
    List<User> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showstore);
        ImageView quaylai = findViewById(R.id.imageView2);

        // Thiết lập sự kiện nhấp vào cho ImageView
        quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        list = new ArrayList<>();
        RecyclerViewstore = findViewById(R.id.recyclerViewstore);
        userAdapter = new UserAdapter(this, list);
        RecyclerViewstore.setAdapter(userAdapter);

        RecyclerViewstore.setLayoutManager(new LinearLayoutManager(this));

        DatabaseConnection databaseConnection = new DatabaseConnection();
        list.addAll(databaseConnection.getRecentStore()); // Add the fetched data to the existing list
        userAdapter.notifyDataSetChanged(); // Notify the adapter that the data has changed

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        RecyclerViewstore = findViewById(R.id.recyclerViewstore);
        RecyclerViewstore.setLayoutManager(gridLayoutManager);


    }

}
