package com.example.shopeeappconnections;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shopeeappconnections.Connection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginActivity extends AppCompatActivity {

    private EditText txtEmail, txtPassword;
    private Button btnLogin;

    CheckBox checkbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize views
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtpass);
        btnLogin = findViewById(R.id.btnLogin);
        checkbox = findViewById(R.id.checkbox);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performLogin(txtEmail.getText().toString(), txtPassword.getText().toString());
            }
        });
    }

    public void Resgister(View view) {
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }

    private void performLogin(String email, String password) {
        if (isValidInput(email, password)) {
            new LoginTask().execute(email, password);
        } else {
            Toast.makeText(this, "Invalid input. Please check your email and password.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValidInput(String email, String password) {
        return !email.isEmpty() && !password.isEmpty();
    }

    //private class LoginTask extends AsyncTask<String, Void, String> {
//    @Override
//    protected String doInBackground(String... params) {
//        String email = params[0];
//        String password = params[1];
//
//        try {
//            // Establish a database connection
//            try (Connection connect = new DatabaseConnection().getCon()) {
//
//                // Execute a query to get the hashed password and user ID for the given email
//                String query = "SELECT PasswordHash, Id,Fullname FROM AspNetUsers WHERE Email = ? ";
//                try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
//                    preparedStatement.setString(1, email);
//
//                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                        if (resultSet.next()) {
//                            // Retrieve the hashed password and user ID from the database
//                            String storedHashedPassword = resultSet.getString("PasswordHash");
//                            String userId = resultSet.getString("Id");
//                            String Fullname = resultSet.getString("Fullname");
//
//                            // Compare the entered password with the stored password
//                            if (password.equals(storedHashedPassword)) {
//                                // Return the user ID if the password is correct
//                                return userId,Fullname;
//                            }
//                        }
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            // Handle SQL exceptions
//            e.printStackTrace();
//        } catch (Exception e) {
//            // Handle other exceptions
//            e.printStackTrace();
//        }
//
//        return null; // Return null if the login fails
//    }
//
//    @Override
//    protected void onPostExecute(String userId) {
//        if (userId != null) {
//            Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
//
//            // Save user ID to SharedPreferences
//            saveUserIdToPreferences(userId);
//
//            // Navigate to MainActivity and pass user ID
//            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//            intent.putExtra("USER_ID", userId);
//            intent.putExtra("FULL_NAME",Fullname)
//
//            startActivity(intent);
//
//            // Finish the current LoginActivity to prevent the user from going back to it
//            finish();
//        } else {
//            Toast.makeText(LoginActivity.this, "Login failed. Please check your credentials.", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    private void saveUserIdToPreferences(String userId) {
//        SharedPreferences preferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.putString("USER_ID", userId);
//        editor.apply();
//    }
//
//}
//
//}
    private class LoginTask extends AsyncTask<String, Void, String[]> {
        @Override
        protected String[] doInBackground(String... params) {
            String email = params[0];
            String password = params[1];

            try {
                // Establish a database connection
                try (Connection connect = new DatabaseConnection().getCon()) {

                    // Execute a query to get the hashed password and user ID for the given email
                    String query = "SELECT PasswordHash, Id, Fullname ,Email,UserName,IsApproved,Adress,PhoneNumber FROM AspNetUsers WHERE Email = ? ";
                    try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
                        preparedStatement.setString(1, email);

                        try (ResultSet resultSet = preparedStatement.executeQuery()) {
                            if (resultSet.next()) {
                                // Retrieve the hashed password, user ID, and Fullname from the database
                                String storedHashedPassword = resultSet.getString("PasswordHash");
                                String userId = resultSet.getString("Id");
                                String Email = resultSet.getString("Email");
                                String Fullname = resultSet.getString("Fullname");
                                String UserName = resultSet.getString("UserName");
                                String IsApproved = resultSet.getString("IsApproved");
                                String Adress = resultSet.getString("Adress");
                                String PhoneNumber = resultSet.getString("PhoneNumber");

                                // Compare the entered password with the stored password
                                if (password.equals(storedHashedPassword)) {
                                    // Return an array with user ID and Fullname if the password is correct
                                    return new String[]{userId, Fullname,Email,UserName,IsApproved,Adress,PhoneNumber};
                                }
                            }
                        }
                    }
                }
            } catch (SQLException e) {
                // Handle SQL exceptions
                e.printStackTrace();
            } catch (Exception e) {
                // Handle other exceptions
                e.printStackTrace();
            }

            return null; // Return null if the login fails
        }

        @Override
        protected void onPostExecute(String[] result) {
            if (result != null) {
                String userId = result[0];
                String Fullname = result[1];
                String Email = result[2];
                String UserName = result[3];
                String IsApproved = result[4];
                String Adress = result[5];
                String PhoneNumber = result[6];



                Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();

                // Save user ID to SharedPreferences
                saveUserIdToPreferences(userId);
                saveFullnameToPreferences(Fullname);
                saveEmailToPreferences(Email);
                saveUserNameToPreferences(UserName);
                saveAdressToPreferences(Adress);
                savePhoneToPreferences(PhoneNumber);
                saveIsApprovedToPreferences(IsApproved);


                // Navigate to MainActivity and pass user ID
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("USER_ID", userId);
                intent.putExtra("FULL_NAME", Fullname);
                intent.putExtra("EMAIL_USER",Email);
                intent.putExtra("USN_USER",UserName);
                intent.putExtra("AD_USER",Adress);
                intent.putExtra("PHONE_USER",PhoneNumber);
                intent.putExtra("ISA_USER",IsApproved);

                startActivity(intent);

                // Finish the current LoginActivity to prevent the user from going back to it
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Login failed. Please check your credentials.", Toast.LENGTH_SHORT).show();
            }
        }
        private void saveIsApprovedToPreferences(String IsApproved) {
            SharedPreferences preferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("ISA_USER", IsApproved);
            editor.apply();
        }
        private void saveUserNameToPreferences(String UserName) {
            SharedPreferences preferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("USN_USER", UserName);
            editor.apply();
        }
        private void saveAdressToPreferences(String Adress) {
            SharedPreferences preferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("AD_USER", Adress);
            editor.apply();
        }
        private void savePhoneToPreferences(String PhoneNumber) {
            SharedPreferences preferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("PHONE_USER", PhoneNumber);
            editor.apply();
        }
        private void saveEmailToPreferences(String Email) {
            SharedPreferences preferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("EMAIL_USER", Email);
            editor.apply();
        }
        private void saveFullnameToPreferences(String Fullname) {
            SharedPreferences preferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("FULL_NAME", Fullname);
            editor.apply();
        }
        private void saveUserIdToPreferences(String userId) {
            SharedPreferences preferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("USER_ID", userId);
            editor.apply();
        }
    }
}