package com.example.shopeeappconnections;

import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopeeappconnections.Adapter.AllproductAdapter;
import com.example.shopeeappconnections.Connection.DatabaseConnection;
import com.example.shopeeappconnections.Model.Product;

import java.util.ArrayList;

public class AllproductActivity extends AppCompatActivity {
    RecyclerView recyclerViewallsp;
    ArrayList<Product> productList;

    AllproductAdapter allproductAdapter;
    SearchView searchView;
    private Spinner spinner1, spinner2, spinner3, spinner4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allproduct_acticity);

        productList = new ArrayList<>();
        recyclerViewallsp = findViewById(R.id.recyclerViewallsp);
        allproductAdapter = new AllproductAdapter(this, productList);
        recyclerViewallsp.setAdapter(allproductAdapter);

        int spanCount = 2; // Number of columns in the grid
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, spanCount);
        recyclerViewallsp.setLayoutManager(layoutManager);

        // Set the adapter to your RecyclerView
        recyclerViewallsp.setAdapter(allproductAdapter);

        // Initialize and set up the SearchView
        searchView = findViewById(R.id.searchproduct);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                allproductAdapter.getFilter().filter(newText);
                return true;
            }
        });

        // Retrieve search query from intent (if any) and set it to the SearchView
        String searchQuery = getIntent().getStringExtra("SEARCH_QUERY");
        if (searchQuery != null && !searchQuery.isEmpty()) {
            searchView.setQuery(searchQuery, false);
            searchView.setIconified(false); // Open the SearchView with the query
        }
        displayallData(searchQuery);


    }

    private void displayallData(String searchQuery) {
        Log.d("MainActivity", "Displaying data");
        ArrayList<Product> newProductList = new DatabaseConnection().getAllProducts();

        if (newProductList != null && !newProductList.isEmpty()) {
            // Clear the existing list before adding new data
            productList.clear();

            for (Product product : newProductList) {
                String idValue = product.getProductId();
                String nameValue = product.getProductName();

                if (idValue != null && nameValue != null) {
                    productList.add(product);
                }
            }
            allproductAdapter.updateList(productList);
        } else {
            Log.e("DisplayData", "Product list is null or empty");
        }
    }
}
