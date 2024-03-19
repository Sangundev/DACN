package com.example.shopeeappconnections;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shopeeappconnections.Connection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TuychinhtaikhoanActivity extends AppCompatActivity {
    EditText email, phone, thongbao, fullname, username;
    ImageView anh;
    TextView Quyen, dangxuat;
    Button save, exit;

    private String userId, userEmail, usernameValue, fullnameValue, phoneValue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuychinhtaikhoan);

        email = findViewById(R.id.emailtaikhaon);
        userEmail = saveEmailToPreferences();
        email.setText(userEmail);

        phone = findViewById(R.id.Phone);
        username = findViewById(R.id.Username);
        fullname = findViewById(R.id.fullname);

        dangxuat = findViewById(R.id.Dangxuat);
        save = findViewById(R.id.luu);
        exit = findViewById(R.id.thoat);

        // Lấy thông tin người dùng từ SharedPreferences và hiển thị
        ImageView quaylai = findViewById(R.id.imageView2);

        // Thiết lập sự kiện nhấp vào cho ImageView
        quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        phoneValue = savePhoneToPreferences();
        phone.setText(phoneValue);

        usernameValue = saveUserNameToPreferences();
        username.setText(usernameValue);

        fullnameValue = getFullnameToPreferences();
        fullname.setText(fullnameValue);

        userId = saveUserIdToPreferences();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy thông tin mới từ người dùng
                String newEmail = email.getText().toString();
                String newPhone = phone.getText().toString();
                String newUsername = username.getText().toString();
                String newFullname = fullname.getText().toString();

                // Gọi phương thức cập nhật thông tin người dùng
                new UpdateUserDataTask().execute(userId, newEmail, newPhone, newUsername, newFullname);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TuychinhtaikhoanActivity.this, ThongTinTaiKhoanActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private class UpdateUserDataTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            String userId = params[0];
            String newEmail = params[1];
            String newPhone = params[2];
            String newUsername = params[3];
            String newFullname = params[4];

            try (Connection connection = new DatabaseConnection().getCon()) {
                String updateQuery = "UPDATE AspNetUsers SET Email = ?, PhoneNumber = ?, UserName = ?, FullName = ? WHERE Id = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                    preparedStatement.setString(1, newEmail);
                    preparedStatement.setString(2, newPhone);
                    preparedStatement.setString(3, newUsername);
                    preparedStatement.setString(4, newFullname);
                    preparedStatement.setString(5, userId);

                    int rowsAffected = preparedStatement.executeUpdate();

                    return rowsAffected > 0;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                Intent intent = new Intent(TuychinhtaikhoanActivity.this, ThongTinTaiKhoanActivity.class);
                startActivity(intent);
                finish();
            } else {


            }
        }
    }

    private String saveEmailToPreferences() {
        SharedPreferences preferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        return preferences.getString("EMAIL_USER", null);
    }

    private String getFullnameToPreferences() {
        SharedPreferences preferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        return preferences.getString("FULL_NAME", null);
    }

    private String saveUserNameToPreferences() {
        SharedPreferences preferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        return preferences.getString("USN_USER", null);
    }

    private String savePhoneToPreferences() {
        SharedPreferences preferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        return preferences.getString("PHONE_USER", null);
    }

    private String saveUserIdToPreferences() {
        SharedPreferences preferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        return preferences.getString("USER_ID", null);
    }


}
