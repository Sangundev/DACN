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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shopeeappconnections.Connection.DatabaseConnection;
import com.example.shopeeappconnections.Gmail.JavaMailAPI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class DoiMatKhauActivity extends AppCompatActivity {
    TextView emailTextView;
    EditText newPasswordEditText, confirmNewPasswordEditText, confirmationCodeEditText, currentPasswordEditText;
    Button sendConfirmationCodeButton, changePasswordButton;
    private String userEmail, confirmationCode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doimatkhau);

        emailTextView = findViewById(R.id.email);
        userEmail = saveEmailToPreferences();
        emailTextView.setText(userEmail);

        currentPasswordEditText = findViewById(R.id.nhapmkcu);
        newPasswordEditText = findViewById(R.id.Matkhaumoi);
        confirmNewPasswordEditText = findViewById(R.id.xacnhanmkmoi);
        confirmationCodeEditText = findViewById(R.id.checkemail);
        sendConfirmationCodeButton = findViewById(R.id.Guigmail);
        changePasswordButton = findViewById(R.id.button3);
        ImageView quaylai = findViewById(R.id.imageView2);

        // Thiết lập sự kiện nhấp vào cho ImageView
        quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        sendConfirmationCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = emailTextView.getText().toString();
                confirmationCode = generateRandomCode();
                sendConfirmationEmail(userEmail, confirmationCode);
            }
        });

        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAndChangePassword();
            }
        });
    }

    private void checkAndChangePassword() {
        String enteredCode = confirmationCodeEditText.getText().toString();
        String currentPassword = currentPasswordEditText.getText().toString();
        String newPassword = newPasswordEditText.getText().toString();
        String confirmNewPassword = confirmNewPasswordEditText.getText().toString();

        if (checkCodeEmail(enteredCode) && currentPassword.equals(getPasswordFromDatabase(userEmail))) {
            if (newPassword.equals(confirmNewPassword)) {
                new ChangePasswordTask().execute(userEmail, newPassword);
            } else {
                showToast("New passwords do not match. Please try again.");
            }
        } else {
            showToast("Invalid confirmation code or current password. Please try again.");
        }
    }

    private String getPasswordFromDatabase(String userEmail) {
        String password = null;

        try (Connection connection = new DatabaseConnection().getCon()) {
            String selectPasswordQuery = "SELECT PasswordHash FROM AspNetUsers WHERE Email = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectPasswordQuery)) {
                preparedStatement.setString(1, userEmail);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        password = resultSet.getString("PasswordHash");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return password;
    }


    private class ChangePasswordTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            String userEmail = params[0];
            String newPassword = params[1];

            try (Connection connection = new DatabaseConnection().getCon()) {
                String updatePasswordQuery = "UPDATE AspNetUsers SET PasswordHash = ? WHERE Email = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(updatePasswordQuery)) {
                    preparedStatement.setString(1, newPassword);
                    preparedStatement.setString(2, userEmail);

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
                showToast("Password updated successfully!");
                startMainActivity();
            } else {
                showToast("Failed to update password. Please try again.");
            }
        }
    }

    private void sendConfirmationEmail(String userEmail, String confirmationCode) {
        String subject = "Confirm Your Registration";
        String message = "Thank you for registering! Your confirmation code is: " + confirmationCode;

        saveSessionData(userEmail, confirmationCode);

        new JavaMailAPI(DoiMatKhauActivity.this, userEmail, subject, message).execute();
    }

    private void saveSessionData(String userEmail, String confirmationCode) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", userEmail);
        editor.putString("confirmationCode", confirmationCode);
        editor.apply();
    }

    private boolean checkCodeEmail(String enteredCode) {
        String storedCode = getConfirmationCodeFromSession();

        if (storedCode.equals(enteredCode)) {
            showToast("Confirmation code is correct! Registration successful!");
            return true;
        } else {
            showToast("Incorrect confirmation code. Registration failed. Please try again.");
            return false;
        }
    }

    private String getConfirmationCodeFromSession() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        return sharedPreferences.getString("confirmationCode", "");
    }

    private String generateRandomCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }

    private void showToast(String message) {
        Toast.makeText(DoiMatKhauActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    private String saveEmailToPreferences() {
        SharedPreferences preferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        return preferences.getString("EMAIL_USER", null);
    }

    private void startMainActivity() {
        Intent intent = new Intent(DoiMatKhauActivity.this, MainActivity.class);
        startActivity(intent);
        finish(); // This will close the current activity
    }
}
