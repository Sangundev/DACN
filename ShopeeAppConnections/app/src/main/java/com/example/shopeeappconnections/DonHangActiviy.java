package com.example.shopeeappconnections;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.shopeeappconnections.Adapter.FragmentAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class DonHangActiviy extends AppCompatActivity {
    ViewPager2 simpleViewPager2;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donhang); // Replace with the correct layout file

        ImageView quaylai = findViewById(R.id.imageView2);

        // Thiết lập sự kiện nhấp vào cho ImageView
        quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // Initialize Views
        simpleViewPager2 = findViewById(R.id.tabChats);
        tabLayout = findViewById(R.id.tabStatus);

        // Create tabs
        TabLayout.Tab firstTab = tabLayout.newTab().setText("Đơn Hàng Đã Đặt").setIcon(R.drawable.ic_launcher_foreground);
        TabLayout.Tab secondTab = tabLayout.newTab().setText("Đơn Hàng Đã Thanh Toán").setIcon(R.drawable.ic_launcher_foreground);
        TabLayout.Tab thirdTab = tabLayout.newTab().setText("Đơn Hàng Nhận Hàng rồi thanh toán").setIcon(R.drawable.ic_launcher_foreground);
        TabLayout.Tab fourthTab = tabLayout.newTab().setText("Đơn hàng đã Hủy").setIcon(R.drawable.ic_launcher_foreground);

        // Add tabs to TabLayout
        tabLayout.addTab(firstTab);
        tabLayout.addTab(secondTab);
        tabLayout.addTab(thirdTab);
        tabLayout.addTab(fourthTab);

        // Set up ViewPager2
        FragmentAdapter adapter = new FragmentAdapter(this);
        simpleViewPager2.setAdapter(adapter);

        int selectedTabIndex = getIntent().getIntExtra("SELECTED_TAB_INDEX", -1);
        if (selectedTabIndex != -1) {
            // Set the ViewPager2 to the desired tab
            simpleViewPager2.setCurrentItem(selectedTabIndex);
        }

        // Connect TabLayout and ViewPager2
        new TabLayoutMediator(tabLayout, simpleViewPager2, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Đơn Hàng Đã Đặt");
                    tab.setIcon(R.drawable.ic_launcher_foreground);
                    break;
                case 1:
                    tab.setText("Đơn Hàng Đã Thanh Toán");
                    tab.setIcon(R.drawable.ic_launcher_foreground);
                    break;
                case 2:
                    tab.setText("Đơn Hàng Nhận Hàng rồi thanh toán");
                    tab.setIcon(R.drawable.ic_launcher_foreground);
                    break;
                case 3:
                    tab.setText("Đơn hàng đã Hủy");
                    tab.setIcon(R.drawable.ic_launcher_foreground);
                    break;
            }
        }).attach();

        // Load the selected fragment
        String selectedFragment = getIntent().getStringExtra("SELECTED_FRAGMENT");
        if (selectedFragment != null) {
            switch (selectedFragment) {
                case "Order_Fragment":
                    simpleViewPager2.setCurrentItem(0, true);
                    break;
                case "paymentorderFragment":
                    simpleViewPager2.setCurrentItem(1, true);
                    break;
                case "NhanRoiThanhToanFragment":
                    simpleViewPager2.setCurrentItem(2, true);
                    break;
                case "CancelOrderFragment":
                    simpleViewPager2.setCurrentItem(3, true);
                    break;
                default:
                    // Handle the default case or do nothing
            }
        }
    }
}
