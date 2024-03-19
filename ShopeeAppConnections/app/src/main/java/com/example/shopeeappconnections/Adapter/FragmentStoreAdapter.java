package com.example.shopeeappconnections.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.shopeeappconnections.Fragment.BanChayFragment;
import com.example.shopeeappconnections.Fragment.HotStoreFragment;
import com.example.shopeeappconnections.Fragment.SaleproductFragment;
import com.example.shopeeappconnections.Fragment.SanphamStoreFragment;
import com.example.shopeeappconnections.Fragment.TheLoaiBanFragment;

public class FragmentStoreAdapter extends FragmentStateAdapter {
    public FragmentStoreAdapter(@NonNull FragmentActivity fa) {
        super(fa);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Depending on the position, return the corresponding fragment
        switch (position) {
            case 0:
                return new BanChayFragment(); // Replace with your actual fragment class
            case 1:
                return new HotStoreFragment();
            case 2:
                return new SaleproductFragment();
            case 3:
                return new SanphamStoreFragment();
            case 4:
                return new TheLoaiBanFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 5; // Return the total number of fragments
    }
}
