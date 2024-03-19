package com.example.shopeeappconnections;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopeeappconnections.Adapter.DanhmucAdapter;
import com.example.shopeeappconnections.Adapter.ProductAdapter;
import com.example.shopeeappconnections.Adapter.ProductDealsAdapter;
import com.example.shopeeappconnections.Adapter.ProductSaleAdapter;
import com.example.shopeeappconnections.Connection.DatabaseConnection;
import com.example.shopeeappconnections.Model.Product;
import com.example.shopeeappconnections.Model.ProductDeals;
import com.example.shopeeappconnections.Model.ProductSale;
import com.example.shopeeappconnections.Model.danhmuc;
import com.google.android.material.tabs.TabLayout;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    Connection connect;
    String ConnectionResult = "";
    RecyclerView recyclerView, recyclerViewHotdeals, Theloaisp, recyclerViewsale;
    ProductAdapter productAdapter;
    ProductDealsAdapter productDealsAdapter;
    ArrayList<Product> productList;

    ArrayList<ProductDeals> productDealsList;

    ProductSaleAdapter productSaleAdapter;
    ArrayList<ProductSale> productSales;
    ViewFlipper viewFlipper, imageSaleHot;
    ImageView userimage;
    private TextView txtHotDealsCountdown;
    private long hotDealsEndTimeMillis;
    private String userId;
    TabLayout tabLayout;

    private final int UPDATE_CART_QUANTITY = 1;
    private final long CART_UPDATE_INTERVAL = 30000; // Update cart quantity every 30 seconds

    private final Handler cartUpdateHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            if (message.what == UPDATE_CART_QUANTITY) {
                updateCartQuantity();
                cartUpdateHandler.sendEmptyMessageDelayed(UPDATE_CART_QUANTITY, CART_UPDATE_INTERVAL);
                return true;
            }
            return false;
        }
    });

    TextView Gio, Phut, Giay;

    private long getMidnightMillis() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DAY_OF_YEAR, 1); // Move to the next day
        return calendar.getTimeInMillis();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userId = getIntent().getStringExtra("USER_ID");

        connect = new DatabaseConnection().getCon();
        viewFlipper = findViewById(R.id.viewlipper);
        imageSaleHot = findViewById(R.id.imageSaleHot);
        recyclerViewsale = findViewById(R.id.recyclerViewsale);

        productList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        productAdapter = new ProductAdapter(this, productList);
        recyclerView.setAdapter(productAdapter);

        productDealsList = new ArrayList<>();
        recyclerViewHotdeals = findViewById(R.id.recyclerViewHotdeals);
        productDealsAdapter = new ProductDealsAdapter(this, productDealsList);
        recyclerViewHotdeals.setAdapter(productDealsAdapter);

        productSales = new ArrayList<>();
        recyclerViewsale = findViewById(R.id.recyclerViewsale);
        productSaleAdapter = new ProductSaleAdapter(this, productSales);
        recyclerViewsale.setAdapter(productSaleAdapter);
        recyclerViewsale.setLayoutManager(new LinearLayoutManager(this));


        recyclerViewHotdeals.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        txtHotDealsCountdown = findViewById(R.id.txtHotDealsCountdown);
        // Set the target end time for HotDeals (Midnight of the next day)
        hotDealsEndTimeMillis = getMidnightMillis();

//        userimage = findViewById(R.id.Accout);
        // Start the HotDeals countdown timer
//        startHotDealsCountdownTimer();

        displayproductdealsData();
        ActionViewFlipper();
        displayData();

        showdanhmuclist();

        ActionViewFlipperSale();
        displayproductsaleData();
        updateCartQuantity();


        TabLayout tabLayout = findViewById(R.id.tableLayout);

// Create tabs
        TabLayout.Tab firstTab = tabLayout.newTab();
        firstTab.setIcon(R.drawable.like);
        tabLayout.addTab(firstTab);

        TabLayout.Tab secondTab = tabLayout.newTab();
        secondTab.setIcon(R.drawable.bell);
        tabLayout.addTab(secondTab);

        TabLayout.Tab thirdTab = tabLayout.newTab();
        thirdTab.setIcon(R.drawable.home);
        tabLayout.addTab(thirdTab);

        TabLayout.Tab fourthTab = tabLayout.newTab();
        fourthTab.setIcon(R.drawable.shoppingbag);
        tabLayout.addTab(fourthTab);

        TabLayout.Tab fifthTab = tabLayout.newTab();
        fifthTab.setIcon(R.drawable.user);
        tabLayout.addTab(fifthTab);

        tabLayout.selectTab(thirdTab);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab == firstTab) {
                    Intent intent = new Intent(MainActivity.this, TymActivity.class);
                    startActivity(intent);
                } else if (tab == secondTab) {
                    Intent intent = new Intent(MainActivity.this, BellActivity.class);
                    startActivity(intent);
                } else if (tab == thirdTab) {
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);

                } else if (tab == fourthTab) {
                    // The "Fourth" tab is selected, start the ActivityCart
                    Intent intent = new Intent(MainActivity.this, GioHangActivity.class);
                    startActivity(intent);
                } else if (tab == fifthTab) {
                    Intent intent = new Intent(MainActivity.this, UserActivity.class);
                    startActivity(intent);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Not needed for this example
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Not needed for this example
            }
        });

        Gio = findViewById(R.id.txtGio);
        Phut = findViewById(R.id.txtphut);
        Giay = findViewById(R.id.txtgiay);

        startHotDealsCountdownTimer();

        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle the submit action if needed
                // For now, just start AllproductActivity with the search query
                startAllProductActivity(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Handle text change if needed
                return false;
            }
        });
        ListView listView = findViewById(R.id.listviewmanhinhchinh);
        String[] listItems = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(adapter);
    }

    private void startAllProductActivity(String searchQuery) {
        // Perform the search and navigate to AllproductActivity
        Intent intent = new Intent(MainActivity.this, AllproductActivity.class);
        intent.putExtra("SEARCH_QUERY", searchQuery);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        cartUpdateHandler.sendEmptyMessage(UPDATE_CART_QUANTITY);
    }

    @Override
    protected void onPause() {
        super.onPause();
        cartUpdateHandler.removeMessages(UPDATE_CART_QUANTITY);
    }

    private void startHotDealsCountdownTimer() {
        new CountDownTimer(hotDealsEndTimeMillis - System.currentTimeMillis(), 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                // Update the text in the TextView with the remaining time
                long hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished);
                long minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60;
                long seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60;

                String gioText = String.format(Locale.getDefault(), "%02d", hours);
                String phutText = String.format(Locale.getDefault(), "%02d", minutes);
                String giayText = String.format(Locale.getDefault(), "%02d", seconds);

                // Update the corresponding TextViews
                Gio.setText(gioText);
                Phut.setText(phutText);
                Giay.setText(giayText);
            }

            @Override
            public void onFinish() {
                // You can handle any actions when the countdown finishes
                // For example, reset the countdown or perform some other task
                // For simplicity, we'll restart the countdown in this example
                startHotDealsCountdownTimer();
            }
        }.start();
    }

    private void showdanhmuclist() {
        // Use GridLayoutManager with 2 columns
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.HORIZONTAL, false);
        Theloaisp = findViewById(R.id.Theloaisp);
        Theloaisp.setLayoutManager(gridLayoutManager);

        ArrayList<danhmuc> danhmuclist = new ArrayList<>();
        danhmuclist.add(new danhmuc("Deals", "hotdeal"));
        danhmuclist.add(new danhmuc("voucher", "giftvoucher"));
        danhmuclist.add(new danhmuc("Hot", "hot"));
        danhmuclist.add(new danhmuc("Sale", "salehhh"));
        danhmuclist.add(new danhmuc("New", "newhehe"));
        danhmuclist.add(new danhmuc("Search", "search"));
        danhmuclist.add(new danhmuc("Store", "store"));
        danhmuclist.add(new danhmuc("Product", "newproduct"));
        danhmuclist.add(new danhmuc("Best Selling", "thebest"));

        // Modify this line in your MainActivity
        DanhmucAdapter danhmucAdapter = new DanhmucAdapter(this, danhmuclist);

        Theloaisp.setAdapter(danhmucAdapter);
    }


    private void displayproductsaleData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewsale = findViewById(R.id.recyclerViewsale);
        recyclerViewsale.setLayoutManager(linearLayoutManager);
        Log.d("MainActivity", "Displaying data");

        ArrayList<ProductSale> newProductList = new DatabaseConnection().getRecentProductSale();

        if (newProductList != null && !newProductList.isEmpty()) {
            // Clear the existing list before adding new data
            productSales.clear();

            for (ProductSale ProductSale : newProductList) {
                String idValue = ProductSale.getProductId();
                String nameValue = ProductSale.getProductName();
                String image = ProductSale.getLinkimage();
                Log.d("DisplayData", "ID: " + idValue + ", Name: " + nameValue + ", Image: " + image);
                if (idValue != null && nameValue != null) {
                    productSales.add(ProductSale);
                }
            }
            productDealsAdapter.notifyDataSetChanged();
        } else {
            Log.e("DisplayData", "ProductDeals list is null or empty");
        }
    }

    private void displayproductdealsData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewHotdeals = findViewById(R.id.recyclerViewHotdeals);
        recyclerViewHotdeals.setLayoutManager(linearLayoutManager);
        Log.d("MainActivity", "Displaying data");

        ArrayList<ProductDeals> newProductList = new DatabaseConnection().getDealsProducts();

        if (newProductList != null && !newProductList.isEmpty()) {
            // Clear the existing list before adding new data
            productDealsList.clear();

            for (ProductDeals productDeals : newProductList) {
                String idValue = productDeals.getProductId();
                String nameValue = productDeals.getProductName();
                String image = productDeals.getLinkimage();
                Log.d("DisplayData", "ID: " + idValue + ", Name: " + nameValue + ", Image: " + image);
                if (idValue != null && nameValue != null) {
                    productDealsList.add(productDeals);
                }
            }
            productDealsAdapter.notifyDataSetChanged();
        } else {
            Log.e("DisplayData", "ProductDeals list is null or empty");
        }
    }

    private void displayData() {
        Log.d("MainActivity", "Displaying data");
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        Log.d("MainActivity", "Displaying data");


        ArrayList<Product> newProductList = new DatabaseConnection().getRecentProducts();


        if (newProductList != null && !newProductList.isEmpty()) {
            // Clear the existing list before adding new data
            productList.clear();

            for (Product product : newProductList) {
                String idValue = product.getProductId();
                String nameValue = product.getProductName();
                String image = product.getLinkimage();
                Log.d("DisplayData", "ID: " + idValue + ", Name: " + nameValue + ", Image: " + image);
                if (idValue != null && nameValue != null) {
                    productList.add(product);
                }
            }
            productAdapter.notifyDataSetChanged();
        } else {
            Log.e("DisplayData", "Product list is null or empty");
        }
    }


    public void ActionViewFlipperSale() {
        List<String> banner = new ArrayList<>();
        banner.add("https://img.freepik.com/free-psd/black-friday-super-sale-social-media-banner-template_106176-1486.jpg");
        banner.add("https://www.manilaonsale.com/wp-content/uploads/2020/08/True-Value-3M-Products-Sale-2020-poster-800x800.jpg");
        banner.add("https://www.manilaonsale.com/wp-content/uploads/2020/08/True-Value-3M-Products-Sale-2020-poster-800x800.jpg");
        for (int i = 0; i < banner.size(); i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(banner.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageSaleHot.addView(imageView);
        }
        imageSaleHot.setFlipInterval(6000);
        imageSaleHot.setAutoStart(true);
        Animation slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);


        imageSaleHot.setInAnimation(slide_in);
        imageSaleHot.setOutAnimation(slide_out);

    }

    public void ActionViewFlipper() {
        List<String> banner = new ArrayList<>();
        banner.add("https://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-Le-hoi-phu-kien-800-300.png");
        banner.add("https://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-HC-Tra-Gop-800-300.png");
        banner.add("https://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-big-ky-nguyen-800-300.jpg");
        for (int i = 0; i < banner.size(); i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(banner.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(6000);
        viewFlipper.setAutoStart(true);
        Animation slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

//        GridLayoutManager hotDealsLayoutManager = new GridLayoutManager(this, 2);
//        recyclerViewHotdeals.setLayoutManager(hotDealsLayoutManager);

        viewFlipper.setInAnimation(slide_in);
        viewFlipper.setOutAnimation(slide_out);

    }

    public void giohang(View view) {
        Intent i = new Intent(this, GioHangActivity.class);
        startActivity(i);
    }

    public void User(View view) {
        Intent i = new Intent(this, UserActivity.class);
        startActivity(i);
    }

    public void Heat(View view) {
        Intent i = new Intent(this, TymActivity.class);
        startActivity(i);
    }

    private String saveUserIdToPreferences() {
        SharedPreferences preferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        return preferences.getString("USER_ID", null);
    }

    private void updateCartQuantity() {
        String currentUserId = saveUserIdToPreferences();

        // Lấy số lượng giỏ hàng từ cơ sở dữ liệu
        int cartQuantity = new DatabaseConnection().getCartQuantityForUser(currentUserId);

        // Ánh xạ TextView từ layout XML
        TextView cartQuantityTextView = findViewById(R.id.number);

        // Hiển thị số lượng giỏ hàng lên TextView
        cartQuantityTextView.setText(String.valueOf(cartQuantity));
    }
}
