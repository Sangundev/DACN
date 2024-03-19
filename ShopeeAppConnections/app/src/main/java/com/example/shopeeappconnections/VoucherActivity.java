package com.example.shopeeappconnections;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopeeappconnections.Adapter.VoucherAdapter;
import com.example.shopeeappconnections.Connection.DatabaseConnection;
import com.example.shopeeappconnections.Model.Voucher;

import java.sql.SQLException;
import java.util.ArrayList;

public class VoucherActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    VoucherAdapter voucherAdapter;
    DatabaseConnection databaseConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher);
        recyclerView = findViewById(R.id.recyclerViewVoucher);

        databaseConnection = new DatabaseConnection();
        // Fetch vouchers from the database
        ArrayList<Voucher> voucherList = getVouchersFromDatabase();

        // Set up the RecyclerView with the adapter
        voucherAdapter = new VoucherAdapter(this, voucherList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(voucherAdapter);

        ImageView quaylai = findViewById(R.id.imageView2);

        // Thiết lập sự kiện nhấp vào cho ImageView
        quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private ArrayList<Voucher> getVouchersFromDatabase() {
        ArrayList<Voucher> voucherList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView = findViewById(R.id.recyclerViewVoucher);
        recyclerView.setLayoutManager(linearLayoutManager);
        try {
            voucherList = databaseConnection.getVouchers(); // Implement a method to fetch vouchers from the database

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return voucherList;
    }
}
