package com.example.shopeeappconnections;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class ThongTinTaiKhoanActivity extends AppCompatActivity {
    TextView Tuychinhtaikhoan, Doimatkhau, DoiDiaChiGiaoHang, logout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtintaikhoan);
        Tuychinhtaikhoan = findViewById(R.id.Tuychinhtaikhoan);
        Doimatkhau = findViewById(R.id.Doimatkhau);
        DoiDiaChiGiaoHang = findViewById(R.id.DoiDiaChiGiaoHang);
        logout = findViewById(R.id.logout);
        ImageView quaylai = findViewById(R.id.imageView2);

        // Thiết lập sự kiện nhấp vào cho ImageView
        quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Tuychinhtaikhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ThongTinTaiKhoanActivity.this, TuychinhtaikhoanActivity.class);
                startActivity(i);
            }
        });

        Doimatkhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ThongTinTaiKhoanActivity.this, DoiMatKhauActivity.class);
                startActivity(i);
            }
        });

        DoiDiaChiGiaoHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ThongTinTaiKhoanActivity.this, DiachigiaohangfActivity.class);
                startActivity(i);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ThongTinTaiKhoanActivity.this, UserActivity.class);
                startActivity(i);
            }
        });

    }
}
