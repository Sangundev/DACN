package com.example.shopeeappconnections;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shopeeappconnections.Connection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DiachigiaohangfActivity extends AppCompatActivity {
    private Spinner spinnerTinh, spinnerHuyen, spinnerQuan, spinnerXa, spinnerHem;
    private TextView selectedInfoTextView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diachigiaohangf_activity);

        spinnerTinh = findViewById(R.id.spinnerTinh);
        spinnerHuyen = findViewById(R.id.spinnerHuyen);
        spinnerQuan = findViewById(R.id.spinnerQuan);
        spinnerXa = findViewById(R.id.spinnerXa);
        spinnerHem = findViewById(R.id.spinnerHem);
        String userId = saveUserIdToPreferences();
        String diachi = saveAdressToPreferences(userId);
        selectedInfoTextView = findViewById(R.id.selectedInfoTextView);
        selectedInfoTextView.setText(diachi);  // Corrected line

        Button luuButton = findViewById(R.id.luutt);
        luuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveAddressUserId();
            }
        });
        // Dữ liệu giả định
        String[] tinhData = {"TP.HCM"};
        String[] huyenData = {"Quận 1", "Quận 2", "Quận 3", "Quận 4", "Quận 5", "Quận 6", "Quận 7", "Quận 8", "Quận 9", "Quận 10", "Quận 11", "Quận 12"};
        String[] quanData = {"Phường 1", "Phường 2", "Phường 3"};
        String[] xaData = {"Nguyễn Thị Minh Khai", "Đinh Công Tráng", "Nguyễn Văn Trỗi"};
        String[] hemData = {"Hẻm 1", "Hẻm 2", "Hẻm 3"};

        // Tạo Adapter và gán dữ liệu cho Spinner
        ArrayAdapter<String> tinhAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tinhData);
        ArrayAdapter<String> huyenAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, huyenData);
        ArrayAdapter<String> quanAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, quanData);
        ArrayAdapter<String> xaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, xaData);
        ArrayAdapter<String> hemAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, hemData);

        // Set layout cho dropdown menu
        tinhAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        huyenAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        xaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hemAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        // Gán Adapter cho Spinner
        spinnerTinh.setAdapter(tinhAdapter);
        spinnerHuyen.setAdapter(huyenAdapter);
        spinnerQuan.setAdapter(quanAdapter);
        spinnerXa.setAdapter(xaAdapter);
        spinnerHem.setAdapter(hemAdapter);


        Button confirmButton = findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy thông tin đã chọn từ Spinner
                String selectedTinh = spinnerTinh.getSelectedItem().toString();
                String selectedHuyen = spinnerHuyen.getSelectedItem().toString();
                String selectedQuan = spinnerQuan.getSelectedItem().toString();
                String selectedXa = spinnerXa.getSelectedItem().toString();
                String selectedHem = spinnerHem.getSelectedItem().toString();

                // Hiển thị thông tin đã chọn lên TextView
                String selectedInfo = "Tỉnh: " + selectedTinh + "\n"
                        + "Huyện: " + selectedHuyen + "\n"
                        + "Quận: " + selectedQuan + "\n"
                        + "Xã: " + selectedXa + "\n"
                        + "Hẻm: " + selectedHem;

                selectedInfoTextView.setText(selectedInfo);
            }
        });

    }

    private void SaveAddressUserId() {
        // Lấy thông tin đã chọn từ TextView
        String existingAddress = selectedInfoTextView.getText().toString();

        // Lấy user ID từ SharedPreferences
        String userId = saveUserIdToPreferences();

        // Thực hiện cập nhật địa chỉ hiện tại vào database
        new UpdateAddressTask().execute(userId, existingAddress);
    }

    private class UpdateAddressTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            String existingAddress = params[1];
            String userId = params[0];

            try (Connection connection = new DatabaseConnection().getCon()) {
                String updateQuery = "UPDATE AspNetUsers SET Adress = ? WHERE Id = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                    preparedStatement.setString(1, existingAddress);
                    preparedStatement.setString(2, userId);
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
        protected void onPostExecute(Boolean success) {
            if (success) {
                Intent intent = new Intent(DiachigiaohangfActivity.this, ThongTinTaiKhoanActivity.class);
                startActivity(intent);
                finish();
            } else {
                // Update failed
            }
        }
    }

    private String saveAdressToPreferences(String userid) {
        try (Connection connection = new DatabaseConnection().getCon()) {
            String selectQuery = "select adress from AspNetUsers where id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setString(1, userid);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getString("adress");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String saveUserIdToPreferences() {
        SharedPreferences preferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        return preferences.getString("USER_ID", null);
    }

}
