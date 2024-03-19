package com.example.shopeeappconnections.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopeeappconnections.Adapter.GetcategoryAdapter;
import com.example.shopeeappconnections.Connection.DatabaseConnection;
import com.example.shopeeappconnections.Model.StoreViewModel;
import com.example.shopeeappconnections.Model.TheLoaiSp;
import com.example.shopeeappconnections.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TheLoaiBanFragment extends Fragment {


    RecyclerView recyclerView;
    ArrayList<TheLoaiSp> theLoaiSps;

    GetcategoryAdapter getcategoryAdapter;

    private StoreViewModel storeViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        storeViewModel = new ViewModelProvider(requireActivity()).get(StoreViewModel.class);

        // Retrieve the storeid
        String Userid = storeViewModel.getStoreId();

        View view = inflater.inflate(R.layout.fragment_the_loai_ban, container, false);

        recyclerView = view.findViewById(R.id.categoryall);
        theLoaiSps = getcategory();
        getcategoryAdapter = new GetcategoryAdapter(requireContext(), theLoaiSps);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(getcategoryAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);


        return view;
    }

    private ArrayList<TheLoaiSp> getcategory() {
        String Userid = storeViewModel.getStoreId();

        if (Userid != null) {
            ArrayList<TheLoaiSp> theLoaiSps = new ArrayList<>();

            try (Connection connection = new DatabaseConnection().getCon()) {
                String query = "Select b.Categoryid,b.Categoryname,b.Linkanhtt from Product a , Category b Where a.Categoryid = b.Categoryid and Userid= ?";


                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, Userid);

                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                            int id = resultSet.getInt("Categoryid");
                            String name = resultSet.getString("Categoryname");
                            String anh = resultSet.getString("Linkanhtt");
                            TheLoaiSp category = new TheLoaiSp(id, name, anh);
                            theLoaiSps.add(category);
                        }
                    }
                }
            } catch (SQLException e) {
                Log.e("Error", "Error while retrieving products: " + e.getMessage());
                e.printStackTrace();
            }

            return theLoaiSps;
        }

        return new ArrayList<>();  // Return a
    }
}