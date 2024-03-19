package com.example.shopeeappconnections;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shopeeappconnections.Connection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TextActivity extends AppCompatActivity {
    Connection connect;
    String ConnectionResult="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        Button checkConnectionButton = findViewById(R.id.button);
        checkConnectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetTextFromSQL();
            }
        });
    }
    public void GetTextFromSQL() {
        TableLayout tableLayout = findViewById(R.id.tableLayout);

        try {
            DatabaseConnection connectionHelper = new DatabaseConnection();
            Connection connect = connectionHelper.getCon();

            if (connect != null) {
                String query = "SELECT * FROM Product";
                PreparedStatement st = connect.prepareStatement(query);
                ResultSet rs = st.executeQuery();

                while (rs.next()) {
                    TableRow row = new TableRow(this);

                    // Create TextViews for each column
                    TextView idTextView = new TextView(this);
                    idTextView.setText(rs.getString("Productid"));

                    TextView emailTextView = new TextView(this);
                    emailTextView.setText(rs.getString("Productname"));

                    // Add TextViews to the TableRow
                    row.addView(idTextView);
                    row.addView(emailTextView);

                    // Add the TableRow to the TableLayout
                    tableLayout.addView(row);
                }
            }
        } catch (Exception ex) {
            Log.e("error", ex.getMessage());
        }
    }



}
