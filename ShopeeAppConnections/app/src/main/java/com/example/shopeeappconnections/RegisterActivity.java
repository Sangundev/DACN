package com.example.shopeeappconnections;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shopeeappconnections.Connection.DatabaseConnection;
import com.example.shopeeappconnections.Gmail.JavaMailAPI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.UUID;

public class RegisterActivity extends AppCompatActivity {

    private EditText txtEmail, txtPassword, txtConfirmPassword, txtUsername, txtEmailConfirm;
    private Button btnRegister, btnsend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize views
        txtUsername = findViewById(R.id.txtUsername);
        txtEmailConfirm = findViewById(R.id.txtEmailConfirm);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtpass);
        txtConfirmPassword = findViewById(R.id.txtConfirmPassword);

        btnsend = findViewById(R.id.btnsend);
        btnRegister = findViewById(R.id.btnRegister);

        // Set click listener for the register button
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performRegistration();
            }
        });

        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtEmail.getText().toString();
                String confirmationCode = generateRandomCode();
                SendConfirmEmail(email, confirmationCode);
            }
        });
    }

    private void SendConfirmEmail(String email, String confirmationCode) {
        // You can customize the subject and message for your confirmation email
        String subject = "Confirm Your Registration";
        String message = "Thank you for registering! Your confirmation code is: " + confirmationCode;

        saveSessionData(email, confirmationCode);

        new JavaMailAPI(RegisterActivity.this, email, subject, message).execute();
    }
    private void saveSessionData(String email, String confirmationCode) {
        // Use SharedPreferences to store session data
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", email);
        editor.putString("confirmationCode", confirmationCode);
        editor.apply();
    }
    private String generateRandomCode() {
        // Generate a random 6-digit code
        Random random = new Random();
        int code = 100000 + random.nextInt(900000); // Generates a number between 100000 and 999999
        return String.valueOf(code);
    }

    private void performRegistration() {
        // Generate a random UUID for the user
        String userId = UUID.randomUUID().toString();
        String username = txtUsername.getText().toString();
        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();
        String emailconfirm = txtEmailConfirm.getText().toString();
        String confirmPassword = txtConfirmPassword.getText().toString();

        if (isValidInput(email, password, confirmPassword)) {
            if (checkcodeemail(emailconfirm)==true) {
                // If the codes match, proceed with registration
                new RegisterTask().execute(userId, email, username, password);
            } else {
                showToast("Invalid confirmation code. Please check and try again.");
            }
        } else {
            showToast("Invalid input. Please check your email and password.");
        }
    }

    public boolean checkcodeemail(String enteredCode) {
        String storedCode = getConfirmationCodeFromSession();

        if (storedCode.equals(enteredCode)) {
            // If the entered code matches the stored code, complete the registration
            showToast("Confirmation code is correct! Registration successful!");
            return true;
        } else {
            showToast("Incorrect confirmation code. Registration failed. Please try again.");
            return false;
        }
    }


    private boolean isValidInput(String email, String password, String confirmPassword) {
        return !email.isEmpty() && !password.isEmpty() && password.length() >= 6 && password.equals(confirmPassword);
    }

    private class RegisterTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            String userId = params[0];
            String email = params[1];
            String username = params[2];
            String password = params[3];

            try {
                // Establish a database connection
                try (Connection connect = new DatabaseConnection().getCon()) {
                    // Execute a query to insert a new user into the AspNetUsers table
                    String insertUserQuery = "INSERT INTO AspNetUsers (Id, Email, UserName, PasswordHash, EmailConfirmed, PhoneNumberConfirmed, TwoFactorEnabled, LockoutEnabled, AccessFailedCount, IsApproved) " +
                            "VALUES (?, ?, ?, ?, 0, 0, 0, 1, 0, 1)";
                    try (PreparedStatement preparedStatement = connect.prepareStatement(insertUserQuery)) {
                        preparedStatement.setString(1, userId);
                        preparedStatement.setString(2, email);
                        preparedStatement.setString(3, username);
                        preparedStatement.setString(4, password);

                        int rowsAffected = preparedStatement.executeUpdate();

                        if (rowsAffected > 0) {
                            // If the user was successfully added, now add the role
                            String insertUserRoleQuery = "INSERT INTO AspNetUserRoles (UserId, RoleId) VALUES (?, 2)";
                            try (PreparedStatement preparedStatement1 = connect.prepareStatement(insertUserRoleQuery)) {
                                preparedStatement1.setString(1, userId);
                                int rowsAffectedUserRole = preparedStatement1.executeUpdate();
                                return rowsAffectedUserRole > 0;
                            }
                        } else {
                            return false; // Registration failed
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return false; // Return false in case of any exception
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                showToast("Registration successful");
                sendEmail(txtEmail.getText().toString(), "Registration Successful", "Thank you for registering!");
                // Add code to navigate to the next activity (e.g., LoginActivity)
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                // Finish the current RegisterActivity to prevent the user from going back to it
                finish();
            } else {
                showToast("Registration failed. Please try again.");
            }
        }
    }

    public void Login(View view) {
        Intent a = new Intent(this, LoginActivity.class);
        startActivity(a);
    }

    private void showToast(String message) {
        Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    private String getEmailFromSession() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        return sharedPreferences.getString("email", "");
    }

    private String getConfirmationCodeFromSession() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        return sharedPreferences.getString("confirmationCode", "");
    }

    private void sendEmail(String email, String subject, String message) {
        new JavaMailAPI(RegisterActivity.this, email, subject, message).execute();
    }
}
