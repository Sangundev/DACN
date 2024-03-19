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

import com.example.shopeeappconnections.Adapter.MualaiAdapter;
import com.example.shopeeappconnections.Adapter.TinhTrangDHAdapter;
import com.example.shopeeappconnections.Connection.DatabaseConnection;
import com.example.shopeeappconnections.Model.Product;
import com.example.shopeeappconnections.Model.Tinhtang;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {
    TextView Logout, textView3, ThietLaptaikhoan;
    List<Tinhtang> tinhtangList;
    RecyclerView recyclerView2, recyclerView;

    MualaiAdapter mualaiAdapter;
    ArrayList<Product> productList;

    TinhTrangDHAdapter tinhTrangDHAdapter;
    private String fullname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        //quay lai
        ImageView quaylai = findViewById(R.id.imageView2);

        quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        Logout = findViewById(R.id.logout);
        textView3 = findViewById(R.id.textView3);
        fullname = getFullnameToPreferences();
        textView3.setText(fullname);
        // Create an instance of Tinhtang
        Tinhtang tinhtang = new Tinhtang("", "");

        // Call the non-static method using the instance
        List<Tinhtang> tinhtangList = tinhtang.tinhtangList();

        recyclerView2 = findViewById(R.id.recyclerView2);
        tinhTrangDHAdapter = new TinhTrangDHAdapter(this, tinhtangList);
        recyclerView2.setAdapter(tinhTrangDHAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(linearLayoutManager);

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        ThietLaptaikhoan = findViewById(R.id.ThietLaptaikhoan);
        ThietLaptaikhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserActivity.this, ThongTinTaiKhoanActivity.class);
                startActivity(i);

            }
        });


        productList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerViewspdamua);
        mualaiAdapter = new MualaiAdapter(this, productList);
        recyclerView.setAdapter(mualaiAdapter);

// Đảm bảo xóa dòng định nghĩa linearLayoutManager trước khi định nghĩa lại
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        damua();
    }

    // Trong displayData:
    private void damua() {
        Log.d("MainActivity", "Displaying data");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        String userId = saveUserIdToPreferences();

        // Sử dụng DatabaseConnection hoặc các phương thức khác để lấy danh sách sản phẩm đã mua
        ArrayList<Product> purchasedProducts = new DatabaseConnection().getPurchasedProducts(userId);

        if (purchasedProducts != null && !purchasedProducts.isEmpty()) {
            // Clear the existing list before adding new data
            productList.clear();

            for (Product product : purchasedProducts) {
                String idValue = product.getProductId();
                String nameValue = product.getProductName();
                String image = product.getLinkimage();
                Log.d("DisplayData", "ID: " + idValue + ", Name: " + nameValue + ", Image: " + image);
                if (idValue != null && nameValue != null) {
                    productList.add(product);
                }
            }

            // Notify the adapter that the data has changed
            mualaiAdapter.notifyDataSetChanged();
        } else {
            Log.e("DisplayData", "Purchased product list is null or empty");
        }
    }


    private String getFullnameToPreferences() {
        SharedPreferences preferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        return preferences.getString("FULL_NAME", null);
    }

    private String saveUserIdToPreferences() {
        SharedPreferences preferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        return preferences.getString("USER_ID", null);
    }

    public void Tym(View view) {
        Intent i = new Intent(this, TymActivity.class);
        startActivity(i);
    }
}
