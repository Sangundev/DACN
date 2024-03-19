package com.example.shopeeappconnections.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.shopeeappconnections.Fragment.CancelOrderFragment;
import com.example.shopeeappconnections.Fragment.NhanRoiThanhToanFragment;
import com.example.shopeeappconnections.Fragment.Order_Fragment;
import com.example.shopeeappconnections.Fragment.paymentorderFragment;

public class FragmentAdapter extends FragmentStateAdapter {
    public FragmentAdapter(@NonNull FragmentActivity fa) {
        super(fa);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Depending on the position, return the corresponding fragment
        switch (position) {
            case 0:
                return new Order_Fragment(); // Replace with your actual fragment class
            case 1:
                return new paymentorderFragment();
            case 2:
                return new NhanRoiThanhToanFragment();
            case 3:
                return new CancelOrderFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 4; // Return the total number of fragments
    }
}
