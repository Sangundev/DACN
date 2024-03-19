package com.example.shopeeappconnections;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopeeappconnections.Adapter.CartitemAdapter;
import com.example.shopeeappconnections.Connection.DatabaseConnection;
import com.example.shopeeappconnections.Model.Cartitem;

import java.util.ArrayList;
import java.util.List;

public class GioHangActivity extends AppCompatActivity {

    private RecyclerView recycleviewgiohang;
    private CartitemAdapter cartitemAdapter;
    private List<Cartitem> cartitemList;
    private String userId;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang);


        ImageView quaylai = findViewById(R.id.imageView2);

        // Thiết lập sự kiện nhấp vào cho ImageView
        quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // Retrieve the user ID from SharedPreferences
        userId = getUserIdFromPreferences();

        // Initialize RecyclerView, LinearLayoutManager, and CartitemAdapter
        recycleviewgiohang = findViewById(R.id.recycleviewgiohang);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycleviewgiohang.setLayoutManager(layoutManager);

        cartitemList = new ArrayList<>();

        TextView tv_Total = findViewById(R.id.tv_totalPrice);
        cartitemAdapter = new CartitemAdapter(this, cartitemList, selectedItems -> {
            tv_Total.setText(String.valueOf(makeTotal(selectedItems)));
        });
        recycleviewgiohang.setAdapter(cartitemAdapter);
        displayCartitemData();
    }

    private double makeTotal(List<Cartitem> list) {
        double sum = 0;
        for (Cartitem item : list) {
            sum += item.getPrice() * item.getQuantity();
        }
        return sum;
    }

    private void displayCartitemData() {
        Log.d("GioHangActivity", "Displaying cart item data");

        if (userId != null) {
            // Assuming getCartItemsByUserId() returns a list of Cartitem objects for the given user ID
            ArrayList<Cartitem> newCartItemlist = new DatabaseConnection().getCartItemsByUserId(userId);

            if (newCartItemlist != null && !newCartItemlist.isEmpty()) {
                cartitemList.clear();
                cartitemList.addAll(newCartItemlist);
                cartitemAdapter.notifyDataSetChanged();
            } else {
                Log.e("GioHangActivity", "Cart item list is null or empty");
            }
        } else {
            Log.e("GioHangActivity", "User ID is null");
        }
    }

    private String getUserIdFromPreferences() {
        SharedPreferences preferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        return preferences.getString("USER_ID", null);
    }

    public void handleBuy(View view) {
        if (view.getId() == R.id.btnmuahang) {
            List<Cartitem> selectedProducts = cartitemAdapter.getSelectedProducts();
            Intent intent = new Intent(this, ShowActivity.class);
            intent.putExtra("data", new ArrayList<>(selectedProducts));
            startActivity(intent);
//            finish();
        }
    }
}
