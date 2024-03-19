package com.example.shopeeappconnections;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopeeappconnections.Adapter.TymAdapter;
import com.example.shopeeappconnections.Connection.DatabaseConnection;
import com.example.shopeeappconnections.Model.Heart;

import java.util.ArrayList;

public class TymActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TymAdapter tymAdapter;
    private ArrayList<Heart> tymItemList;
    private String Userid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tym);
        Userid = getUserIdFromPreferences();
        tymItemList = new ArrayList<>();
        recyclerView = findViewById(R.id.tym);
        tymAdapter = new TymAdapter(this, tymItemList);
        recyclerView.setAdapter(tymAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ImageView quaylai = findViewById(R.id.imageView2);

        // Thiết lập sự kiện nhấp vào cho ImageView
        quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // Call AsyncTask or use a ViewModel here to fetch data
        new GetTymItemsTask().execute();
    }

    private class GetTymItemsTask extends AsyncTask<Void, Void, ArrayList<Heart>> {

        @Override
        protected ArrayList<Heart> doInBackground(Void... voids) {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            return databaseConnection.getTymItems(Userid);
        }

        @Override
        protected void onPostExecute(ArrayList<Heart> result) {
            super.onPostExecute(result);

            tymItemList.clear();
            tymItemList.addAll(result);

            // Notify the adapter that the data set has changed
            tymAdapter.notifyDataSetChanged();
        }
    }

    private String getUserIdFromPreferences() {
        SharedPreferences preferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        return preferences.getString("USER_ID", null);
    }
}

