package com.example.shopeeappconnections;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.shopeeappconnections.Adapter.FragmentStoreAdapter;
import com.example.shopeeappconnections.Model.StoreViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class DetailStoreActivity extends AppCompatActivity {

    TextView fullname;
    ViewPager2 simpleViewPager2;

    TabLayout tabLayout;
    private StoreViewModel storeViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deatailstore);
        fullname = findViewById(R.id.fullnamestore);
        simpleViewPager2 = findViewById(R.id.show);
        tabLayout = findViewById(R.id.tabLayoutq);
        ImageView storeImageView = findViewById(R.id.imageView8);
        TextView fullname = findViewById(R.id.fullnamestore);
        TextView diachi = findViewById(R.id.textView15);
        Intent intent = getIntent();
        if (intent != null) {
            String storeid = intent.getStringExtra("USER_ID");
            String storename = intent.getStringExtra("USER_NAME");
            String storeadress = intent.getStringExtra("USER_adress");
            String storeImage = intent.getStringExtra("USER_IMAGE");  // Get the store image URL

            // Display the store image using Glide
            if (storeImage != null && !storeImage.isEmpty()) {
                Glide.with(this).load(storeImage).into(storeImageView);
            } else {
                // If the image URL is not available, you can set a placeholder image or handle it accordingly
                storeImageView.setImageResource(R.drawable.baseline_drag_handle_24);
            }

            if (storename != null) {
                fullname.setText(storename);
                diachi.setText(storeadress);
            }

            // Initialize the ViewModel
            storeViewModel = new ViewModelProvider(this).get(StoreViewModel.class);
            storeViewModel.setStoreId(storeid);
        }
        ImageView quaylai = findViewById(R.id.imageView2);

        // Thiết lập sự kiện nhấp vào cho ImageView
        quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // Initialize the ViewModel

        TabLayout.Tab firstTab1 = tabLayout.newTab().setText("San Pham Ban nhieu ").setIcon(R.drawable.ic_launcher_foreground);
        TabLayout.Tab secondTab2 = tabLayout.newTab().setText("San Pham Hot").setIcon(R.drawable.ic_launcher_foreground);
        TabLayout.Tab thirdTab3 = tabLayout.newTab().setText("San Pham").setIcon(R.drawable.ic_launcher_foreground);
        TabLayout.Tab fourthTab4 = tabLayout.newTab().setText("The Loai ban").setIcon(R.drawable.ic_launcher_foreground);
//        TabLayout.Tab fivetab = tabLayout.newTab().setText("san pham sale").setIcon(R.drawable.ic_launcher_foreground);

        // Add tabs to TabLayout
        tabLayout.addTab(firstTab1);
        tabLayout.addTab(secondTab2);
        tabLayout.addTab(thirdTab3);
        tabLayout.addTab(fourthTab4);
//        tabLayout.addTab(fivetab);

        // Set up ViewPager2
        FragmentStoreAdapter adapter = new FragmentStoreAdapter(this);
        simpleViewPager2.setAdapter(adapter);

        // Connect TabLayout and ViewPager2
        new TabLayoutMediator(tabLayout, simpleViewPager2, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("");
                    tab.setIcon(R.drawable.ic_launcher_foreground);
                    break;
                case 1:
                    tab.setText("");
                    tab.setIcon(R.drawable.ic_launcher_foreground);
                    break;
                case 2:
                    tab.setText("");
                    tab.setIcon(R.drawable.ic_launcher_foreground);
                    break;
                case 3:
                    tab.setText("");
                    tab.setIcon(R.drawable.ic_launcher_foreground);
                    break;
//                case 4:
//                    tab.setText("");
//                    tab.setIcon(R.drawable.ic_launcher_foreground);
//                    break;
            }
        }).attach();

        // Load the selected fragment
        String selectedFragment = getIntent().getStringExtra("SELECTED_FRAGMENT");
        if (selectedFragment != null) {
            switch (selectedFragment) {
                case "BanChayFragment":
                    simpleViewPager2.setCurrentItem(0, true);
                    break;
                case "HotStoreFragment":
                    simpleViewPager2.setCurrentItem(1, true);
                    break;
                case "SaleproductFragment":
                    simpleViewPager2.setCurrentItem(2, true);
                    break;
                case "SanphamStoreFragment":
                    simpleViewPager2.setCurrentItem(3, true);
                case "TheLoaiBanFragment":
                    simpleViewPager2.setCurrentItem(4, true);
                default:
                    // Handle the default case or do nothing
            }
        }

    }

}
