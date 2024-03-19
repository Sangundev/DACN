package com.example.shopeeappconnections;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopeeappconnections.Adapter.comment_SPAdapter;
import com.example.shopeeappconnections.Connection.DatabaseConnection;
import com.example.shopeeappconnections.Model.comment_SP;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {
    private TextView txtProductName;
    private TextView txtPrice;
    private TextView txtDescription;

    RecyclerView binhluan;
    comment_SPAdapter commentSpAdapter;
    ArrayList<comment_SP> commentSps;

    EditText Nhapbinhluan;
    Button Thembinhluan;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ImageView quaylai = findViewById(R.id.imageView2);

        quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Initialize views
        txtProductName = findViewById(R.id.txttensanpham);
        txtPrice = findViewById(R.id.txtgiasanpham);
        ImageView imageView = findViewById(R.id.anhsanpham);
        // Add other view initialization if needed
        // Retrieve extras
        Intent intent = getIntent();
        if (intent != null) {
            String productId = intent.getStringExtra("PRODUCT_ID");
            String productName = intent.getStringExtra("PRODUCT_NAME");
            float productPrice = intent.getFloatExtra("PRODUCT_PRICE", 0.0f);
            String productImage = intent.getStringExtra("PRODUCT_IMAGE");

            // Display details in UI elements
            txtProductName.setText(productName);
            txtPrice.setText("Gia: " + productPrice + "d");
            // Add other details if needed

            if (productImage != null && !productImage.isEmpty()) {
                Glide.with(this).load(productImage).into(imageView);
            } else {
                // If the image URL is not available, you can set a placeholder image or handle it accordingly
                imageView.setImageResource(R.drawable.baseline_drag_handle_24);
            }
        }
        ImageView addTymImageView = findViewById(R.id.addtym);
        addTymImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the necessary information from your UI elements
                String userId = getUserIdFromPreferences();
                String productId = intent.getStringExtra("PRODUCT_ID");
                String productName = intent.getStringExtra("PRODUCT_NAME");
                BigDecimal price = BigDecimal.valueOf(intent.getFloatExtra("PRODUCT_PRICE", 0.0f));
                int quantity = 1;  // You can implement a method to get the selected quantity
                String Image = "img_1";
                String Linkimage = intent.getStringExtra("PRODUCT_IMAGE");
                // Call the addToCart method in DatabaseConnection
                DatabaseConnection databaseConnection = new DatabaseConnection();
                databaseConnection.addToTym(userId, productId, productName, Image, Linkimage);
                updateCartQuantity();
                // Optionally, you can provide feedback to the user, e.g., show a toast
                Toast.makeText(DetailsActivity.this, "Item added to tym successfully", Toast.LENGTH_SHORT).show();
            }
        });
        // Inside your DetailsActivity
        Button btnThemVaoGioHang = findViewById(R.id.btnthemvaogiohang);
        btnThemVaoGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the necessary information from your UI elements
                String userId = getUserIdFromPreferences();
                String productId = intent.getStringExtra("PRODUCT_ID");
                String productName = intent.getStringExtra("PRODUCT_NAME");
                BigDecimal price = BigDecimal.valueOf(intent.getFloatExtra("PRODUCT_PRICE", 0.0f));
                int quantity = 1;  // You can implement a method to get the selected quantity
                String Image = "img_1";
                String Linkimage = intent.getStringExtra("PRODUCT_IMAGE");
                // Call the addToCart method in DatabaseConnection
                DatabaseConnection databaseConnection = new DatabaseConnection();
                databaseConnection.addToCart(userId, productId, productName, price, quantity, Image, Linkimage);
                updateCartQuantity();
                // Optionally, you can provide feedback to the user, e.g., show a toast
                Toast.makeText(DetailsActivity.this, "Item added to cart successfully", Toast.LENGTH_SHORT).show();
            }
        });

        commentSps = new ArrayList<>();
        binhluan = findViewById(R.id.binhluan);
        commentSpAdapter = new comment_SPAdapter(this, commentSps);
        binhluan.setAdapter(commentSpAdapter);

        String productId = intent.getStringExtra("PRODUCT_ID");

        binhluan.setLayoutManager(new LinearLayoutManager(this));
        loadCommentsForProduct(productId);

        Nhapbinhluan = findViewById(R.id.nhapbinhluan);
        Thembinhluan = findViewById(R.id.btnbinhluan);
        Thembinhluan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productId = intent.getStringExtra("PRODUCT_ID");
                String content = Nhapbinhluan.getText().toString();
                insertCommentInProduct(productId, content);
            }
        });

        String userId = getUserIdFromPreferences();

    }


    private void insertCommentInProduct(String productId, String content) {
        String userId = getUserIdFromPreferences();
        try (Connection connection = new DatabaseConnection().getCon()) {
            // Fetch the max comment ID from the database
            String getMaxIdQuery = "SELECT MAX(id) FROM comment_SP";
            try (PreparedStatement maxIdStatement = connection.prepareStatement(getMaxIdQuery);
                 ResultSet resultSet = maxIdStatement.executeQuery()) {

                int maxId = 0;
                if (resultSet.next()) {
                    maxId = resultSet.getInt(1);
                }

                // Increment the max ID to get the new comment ID
                int newCommentId = maxId + 1;

                // Insert the comment with the new ID
                String insertQuery = "INSERT INTO comment_SP (id, [content], product_id, user_id, rating, clip, image) VALUES (?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    preparedStatement.setInt(1, newCommentId);
                    preparedStatement.setString(2, content);
                    preparedStatement.setString(3, productId);
                    preparedStatement.setString(4, userId);
                    preparedStatement.setString(5, "5");
                    preparedStatement.setString(6, null);
                    preparedStatement.setString(7, null);

                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        // Comment inserted successfully, now fetch the inserted comment
                        comment_SP insertedComment = getCommentById(newCommentId);
                        // Display the inserted comment immediately
                        commentSps.add(insertedComment);
                        commentSpAdapter.notifyDataSetChanged();

                        Toast.makeText(DetailsActivity.this, "Comment inserted successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DetailsActivity.this, "Failed to insert comment", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private comment_SP getCommentById(int commentId) {
        try (Connection connection = new DatabaseConnection().getCon()) {
            String selectQuery = "SELECT * FROM comment_SP WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setInt(1, commentId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String content = resultSet.getString("content");
                        // Retrieve other fields as needed
                        return new comment_SP(id, content, 10, null, 0, null);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private void loadCommentsForProduct(String productId) {
        ArrayList<comment_SP> comments = getCommentsFromDatabase(productId);

        // Update the commentSps list in the adapter
        commentSps.clear();
        commentSps.addAll(comments);
        commentSpAdapter.notifyDataSetChanged();
    }

    private ArrayList<comment_SP> getCommentsFromDatabase(String productId) {
        ArrayList<comment_SP> comments = new ArrayList<>();
        try (Connection connection = new DatabaseConnection().getCon()) {
            String selectQuery = "SELECT content FROM comment_SP WHERE product_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setString(1, productId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String content = resultSet.getString("content");
                        // You may want to add other fields and create a comment_SP object here
                        comment_SP comment = new comment_SP(1, content, 10, null, 0, null);
                        comments.add(comment);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comments;
    }

    private void saveAdressToPreferences(String productId) {
        SharedPreferences preferences = getSharedPreferences("product_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("SELECTED_PRODUCT_ID", productId);
        editor.apply();
    }

    private String getUserIdFromPreferences() {
        SharedPreferences preferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        return preferences.getString("USER_ID", null);
    }

    private void updateCartQuantity() {
        String currentUserId = getUserIdFromPreferences();

        // Lấy số lượng giỏ hàng từ cơ sở dữ liệu
        int cartQuantity = new DatabaseConnection().getCartQuantityForUser(currentUserId);

        // Ánh xạ TextView từ layout XML
        TextView cartQuantityTextView = findViewById(R.id.number);

        // Hiển thị số lượng giỏ hàng lên TextView
        cartQuantityTextView.setText(String.valueOf(cartQuantity));
    }

    public void giohang(View view) {
        Intent i = new Intent(this, GioHangActivity.class);
        startActivity(i);
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
}

