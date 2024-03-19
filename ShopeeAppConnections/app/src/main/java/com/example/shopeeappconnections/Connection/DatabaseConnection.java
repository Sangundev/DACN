package com.example.shopeeappconnections.Connection;

import android.os.StrictMode;
import android.util.Log;

import com.example.shopeeappconnections.Model.Bellindex;
import com.example.shopeeappconnections.Model.Cartitem;
import com.example.shopeeappconnections.Model.Heart;
import com.example.shopeeappconnections.Model.Order_Deatails;
import com.example.shopeeappconnections.Model.Product;
import com.example.shopeeappconnections.Model.ProductDeals;
import com.example.shopeeappconnections.Model.ProductSale;
import com.example.shopeeappconnections.Model.User;
import com.example.shopeeappconnections.Model.Voucher;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DatabaseConnection {
    Connection con;
    String uname, pass, ip, port, database;

    public DatabaseConnection() {
        connectionclass();
    }

    // @SuppressLint("NewApi")
    public void connectionclass() {
        ip = "10.0.2.2";//192.168.0.114 | 127.0.0.1
        database = "Food_Web";
        uname = "Sang";
        pass = "261102";
        port = "1433";
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
//        Connection connection = null;
        String ConnectionURL = null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";databasename=" + database + ";user=" + uname + ";password=" + pass;
            con = DriverManager.getConnection(ConnectionURL);
        } catch (Exception ex) {
            Log.e("Error", ex.getMessage());
        }
    }

    public Connection getCon() {
        return con;
    }

    public ArrayList<Product> getRecentProducts() {
        ArrayList<Product> productList = new ArrayList<>();
        Connection connection = getCon();

        try {
            if (checkConnection()) {
                String query = "SELECT * FROM Product WHERE DateCreated >= DATEADD(DAY, -30, GETDATE())";

                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    String productId = resultSet.getString("Productid");
                    String productName = resultSet.getString("Productname");
                    float price = resultSet.getFloat("Price");
                    String anhsanpham = resultSet.getString("Linkimage");
                    Log.d("ProductDeals", "Linkimage: " + anhsanpham);
                    // Assuming you have a constructor in your Product class
                    Product product = new Product(productId, productName, price, anhsanpham);

                    productList.add(product);
                }
            }
        } catch (SQLException e) {
            Log.e("Error", e.getMessage());
        }
        return productList;
    }

    public ArrayList<ProductSale> getRecentProductSale() {
        ArrayList<ProductSale> productList = new ArrayList<>();
        Connection connection = getCon();

        try {
            if (checkConnection()) {
                String query = "Select * from Product where DiscountPercent > 0";

                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    String productId = resultSet.getString("Productid");
                    String productName = resultSet.getString("Productname");
                    float price = resultSet.getFloat("Price");
                    int phantramgiamgia = resultSet.getInt("DiscountPercent");
                    float giaGiamTheoKhungGio = resultSet.getFloat("DiscountedPrice");
                    String anhsanpham = resultSet.getString("Linkimage");
                    Log.d("ProductDeals", "Linkimage: " + anhsanpham);
                    // Assuming you have a constructor in your Product class
                    ProductSale product = new ProductSale(productId, productName, anhsanpham, price, giaGiamTheoKhungGio, phantramgiamgia);
                    productList.add(product);
                }
            }
        } catch (SQLException e) {
            Log.e("Error", e.getMessage());
        }
        return productList;
    }

    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> productList = new ArrayList<>();
        Connection connection = getCon();

        try {
            if (checkConnection()) {
                String query = "SELECT * FROM Product"; // Removed the unnecessary closing parenthesis

                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    String productId = resultSet.getString("Productid");
                    String productName = resultSet.getString("Productname");
                    float price = resultSet.getFloat("Price");
                    String anhsanpham = resultSet.getString("Linkimage");
                    Log.d("ProductDeals", "Linkimage: " + anhsanpham);

                    Product product = new Product(productId, productName, price, anhsanpham);

                    productList.add(product);
                }
            }
        } catch (SQLException e) {
            Log.e("Error", e.getMessage());
        }
        return productList;
    }

    public ArrayList<ProductDeals> getDealsProducts() {
        ArrayList<ProductDeals> productList = new ArrayList<>();
        Connection connection = getCon();

        try {
            if (checkConnection()) {
                // Get the current date in the format "yyyy-MM-dd"
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                String currentDate = sdf.format(new Date());

                // Modify the query to include the condition DiscountStartTime < current date < DiscountEndTime
                String query = "SELECT * FROM Product " +
                        "WHERE phantramgiamgia IS NOT NULL " +
                        "AND Tinhtranggiamgia = 1 " +
                        "AND DiscountStartTime < ? " +
                        "AND DiscountEndTime > ?";

                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, currentDate);
                    statement.setString(2, currentDate);

                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                            String productId = resultSet.getString("Productid");
                            String productName = resultSet.getString("Productname");
                            float price = resultSet.getFloat("Price");
                            int phantramgiamgia = resultSet.getInt("phantramgiamgia");
                            float giaGiamTheoKhungGio = resultSet.getFloat("GiaGiamTheoKhungGio");
                            String anhsanpham = resultSet.getString("Linkimage");
                            Log.d("ProductDeals", "Linkimage: " + anhsanpham);


                            ProductDeals productDeals = new ProductDeals(productId, productName, price, giaGiamTheoKhungGio, phantramgiamgia, anhsanpham);

                            productList.add(productDeals);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            Log.e("Error", e.getMessage());
        } finally {
            // Close the connection here if needed
        }
        return productList;
    }


    private boolean checkConnection() {
        boolean isConnected = false;
        try {
            if (con != null && !con.isClosed()) {
                isConnected = true;
            }
        } catch (SQLException e) {
            Log.e("Connection Error", e.getMessage());
        }
        return isConnected;
    }

    public ArrayList<Cartitem> getCartItemsByUserId(String userId) {
        ArrayList<Cartitem> cartitems = new ArrayList<>();
        Connection connection = getCon();

        try {
            if (checkConnection()) {
                String query = "SELECT * FROM CartItem WHERE IdUser = ?";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, userId);

                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                            int id = resultSet.getInt("Id");
                            String productName = resultSet.getString("ProductName");
                            float price = resultSet.getFloat("Price");
                            int Quantity = resultSet.getInt("Quantity");
                            int prodID = resultSet.getInt("Productid");
                            String Linkimage = resultSet.getString("Linkimage");
                            // Create Cartitem object with the retrieved data
                            Cartitem cartitem = new Cartitem(id, productName, price, null, Quantity, 0.0f, prodID, null, Linkimage);

                            cartitems.add(cartitem);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            Log.e("Error", e.getMessage());
        } finally {
            // Close the connection here if needed
        }
        return cartitems;
    }

    //    public void addToCart(String userId, String productId, String productName, BigDecimal price, int quantity, String image) {
//        Connection connection = getCon();
//
//        try {
//            String query = "INSERT INTO CartItem (IdUser, ProductId, ProductName, Price, Quantity, Image) VALUES (?, ?, ?, ?, ?, ?)";
//            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//                preparedStatement.setString(1, userId);
//                preparedStatement.setString(2, productId);
//                preparedStatement.setString(3, productName);
//                preparedStatement.setBigDecimal(4, price); // Use setBigDecimal for decimal values
//                preparedStatement.setInt(5, quantity);
//                preparedStatement.setString(6, image);
//
//                preparedStatement.executeUpdate();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    public void addToCart(String userId, String productId, String productName, BigDecimal price, int quantity, String image, String Linkimage) {
        Connection connection = getCon();

        try {
            // Check if the item already exists in the cart
            String checkQuery = "SELECT * FROM CartItem WHERE IdUser = ? AND ProductId = ?";
            try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {
                checkStatement.setString(1, userId);
                checkStatement.setString(2, productId);

                try (ResultSet resultSet = checkStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Item already exists, update the quantity
                        int existingQuantity = resultSet.getInt("Quantity");
                        quantity += existingQuantity; // Update the quantity
                        updateCartItemQuantity(userId, productId, quantity);
                        return; // Exit the method as the item is already in the cart
                    }
                }
            }

            // Item doesn't exist in the cart, insert a new item
            String insertQuery = "INSERT INTO CartItem (IdUser, ProductId, ProductName, Price, Quantity, Image,Linkimage) VALUES (?, ?, ?, ?, ?, ?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, userId);
                preparedStatement.setString(2, productId);
                preparedStatement.setString(3, productName);
                preparedStatement.setBigDecimal(4, price);
                preparedStatement.setInt(5, quantity);
                preparedStatement.setString(6, image);
                preparedStatement.setString(7, Linkimage);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to update the quantity of an existing item in the cart
    public void updateCartItemQuantity(String userId, String productId, int quantity) {
        Connection connection = getCon();

        try {
            String updateQuery = "UPDATE CartItem SET Quantity = ? WHERE IdUser = ? AND ProductId = ?";
            try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                updateStatement.setInt(1, quantity);
                updateStatement.setString(2, userId);
                updateStatement.setString(3, productId);

                updateStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Payment(List<Cartitem> listCarts, String userId) throws SQLException {
        Connection connection = getCon();
        try {
            String queryOrder = "INSERT INTO Orders (Od_name, Od_status, idthanhtoan, VoidanOder,Od_date) VALUES (?, ?, ?, ?,?)";
            try (PreparedStatement addOrder = connection.prepareStatement(queryOrder, Statement.RETURN_GENERATED_KEYS)) {
                addOrder.setString(1, userId);
                addOrder.setBoolean(2, false);
                addOrder.setInt(3, 1);
                addOrder.setBoolean(4, true);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentDate = sdf.format(new Date());
                addOrder.setTimestamp(5, Timestamp.valueOf(currentDate));
                addOrder.executeUpdate();
                ResultSet generatedKeys = addOrder.getGeneratedKeys();

                int orderID = -1;
                if (generatedKeys.next()) {
                    orderID = generatedKeys.getInt(1);
                }

                for (Cartitem item : listCarts) {
                    String queryDetail = "INSERT INTO Order_detail (Od_id, Productid, price, num, tt_money,Storeid) VALUES (?, ?, ?, ?, ?,?)";
                    try (PreparedStatement addDetail = connection.prepareStatement(queryDetail)) {
                        addDetail.setInt(1, orderID);
                        addDetail.setInt(2, item.getProductId());
                        addDetail.setFloat(3, item.getPrice());
                        addDetail.setInt(4, item.getQuantity());
                        addDetail.setFloat(5, item.getPrice() * item.getQuantity());

                        String storeId = getStoreIdForProduct(item.getProductId());
                        addDetail.setString(6, storeId);

                        addDetail.executeUpdate();
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void Paymentonl(List<Cartitem> listCarts, String userId) throws SQLException {
        Connection connection = getCon();
        try {
            String queryOrder = "INSERT INTO Orders (Od_name, Od_status, idthanhtoan, VoidanOder, Od_date) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement addOrder = connection.prepareStatement(queryOrder, Statement.RETURN_GENERATED_KEYS)) {
                addOrder.setString(1, userId);
                addOrder.setBoolean(2, false);
                addOrder.setInt(3, 2);
                addOrder.setBoolean(4, true);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentDate = sdf.format(new Date());
                addOrder.setTimestamp(5, Timestamp.valueOf(currentDate));
                addOrder.executeUpdate();
                ResultSet generatedKeys = addOrder.getGeneratedKeys();

                int orderID = -1;
                if (generatedKeys.next()) {
                    orderID = generatedKeys.getInt(1);
                }

                for (Cartitem item : listCarts) {
                    String queryDetail = "INSERT INTO Order_detail (Od_id, Productid, price, num, tt_money, Storeid) VALUES (?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement addDetail = connection.prepareStatement(queryDetail)) {
                        addDetail.setInt(1, orderID);
                        addDetail.setInt(2, item.getProductId());
                        addDetail.setFloat(3, item.getPrice());
                        addDetail.setInt(4, item.getQuantity());
                        addDetail.setFloat(5, item.getPrice() * item.getQuantity());

                        String storeId = getStoreIdForProduct(item.getProductId());
                        addDetail.setString(6, storeId);

                        addDetail.executeUpdate();

                        // Update the quantity of the product in the Product table
                        updateProductQuantity(item.getProductId(), item.getQuantity());
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void updateProductQuantity(int productId, int quantityToSubtract) throws SQLException {
        Connection connection = getCon();
        String query = "UPDATE Product SET Soluong = Soluong - ? WHERE Productid = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, quantityToSubtract);
            preparedStatement.setInt(2, productId);
            preparedStatement.executeUpdate();
        }
    }


    public void removeItemsFromCart(List<Cartitem> purchasedItems, String userId) {
        Connection connection = getCon();

        try {
            if (checkConnection()) {
                String query = "DELETE FROM CartItem WHERE IdUser = ? AND ProductId = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    for (Cartitem purchasedItem : purchasedItems) {
                        preparedStatement.setString(1, userId);
                        preparedStatement.setInt(2, purchasedItem.getProductId());
                        preparedStatement.addBatch();
                    }

                    // Execute batch delete
                    preparedStatement.executeBatch();
                }
            }
        } catch (SQLException e) {
            Log.e("Error", e.getMessage());
        }
    }

    private String getStoreIdForProduct(int productId) throws SQLException {
        Connection connection = getCon();
        String query = "SELECT Userid FROM Product WHERE Productid = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, productId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("Userid");
                }
            }
        }
        return null; // Handle the case where 'storeid' is not found
    }

    public void removeFromCart(String userId, int productId) {
        Connection connection = getCon();

        try {
            if (checkConnection()) {
                String query = "DELETE FROM CartItem WHERE IdUser = ? AND ProductId = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, userId);
                    preparedStatement.setInt(2, productId);

                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            Log.e("Error", e.getMessage());
        }
    }


    // Add this method to your DatabaseConnection class
    public ArrayList<Voucher> getVouchers() throws SQLException {
        ArrayList<Voucher> voucherList = new ArrayList<>();
        Connection connection = getCon();

        try {
            if (checkConnection()) {
                String query = "SELECT * FROM Discount WHERE Status = 'true'"; // Replace "Voucher" with your actual table name

                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
//                            int id = resultSet.getInt("id");
                            String code = resultSet.getString("Code");
//                            String storeId = resultSet.getString("storeid");
//                            int quantity = resultSet.getInt("soluong");
                            float discountPercent = resultSet.getInt("DiscountPercent"); // Fix typo
//                            Date startDate = resultSet.getDate("StartDate");
//                            Date endDate = resultSet.getDate("Enddate");

                            Voucher voucher = new Voucher(0, code, null, 0, discountPercent, null, null);
                            voucherList.add(voucher);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            Log.e("Error", e.getMessage());
            throw e; // Handle the exception appropriately
        } finally {
            // Close the connection here if needed
        }

        return voucherList;
    }

    public ArrayList<Heart> getTymItems(String userId) {
        ArrayList<Heart> tymItemList = new ArrayList<>();
        Connection connection = getCon();

        if (!checkConnection()) {
            return tymItemList; // Return an empty list if the connection is not successful
        }

        String query = "SELECT * FROM Heartitem WHERE Userid = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String productName = resultSet.getString("ProductName");
                    float price = resultSet.getFloat("Price");
                    String image = resultSet.getString("Image");
                    int productId = resultSet.getInt("Productid");
                    String userIdFromDb = resultSet.getString("Userid");
                    String linkimage = resultSet.getString("Linkimage");

                    Heart heart = new Heart(id, productName, price, image, productId, userIdFromDb, linkimage);
                    tymItemList.add(heart);
                }
            }
        } catch (SQLException e) {
            Log.e("SQL_ERROR", "Error executing SQL query: " + e.getMessage());
            e.printStackTrace(); // Log the stack trace for more details
        } finally {
            // Close the connection here if needed
        }

        return tymItemList;
    }


    public float checkVoucher(String voucherCode) throws SQLException {
        Connection connection = getCon();

        try {
            if (checkConnection()) {
                String query = "SELECT DiscountPercent FROM Discount WHERE Code = ? AND Status = 'true'";

                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, voucherCode);

                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            return resultSet.getFloat("DiscountPercent");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            Log.e("Error", e.getMessage());
            throw e; // Handle the exception appropriately
        } finally {
            // Close the connection here if needed
        }

        // Return 0 if the voucher code is not found or if it is not valid
        return 0;
    }

    // Add this method to your DatabaseConnection class
    public ArrayList<Product> getProductsSortedByQuantitySold() {
        ArrayList<Product> productList = new ArrayList<>();
        Connection connection = getCon();

        try {
            if (checkConnection()) {
                String query = "WITH RankedProducts AS (\n" +
                        "    SELECT\n" +
                        "        a.ProductId,\n" +
                        "        a.ProductName,\n" +
                        "        a.Price,\n" +
                        "        a.Linkimage,\n" +
                        "        SUM(b.num) as TotalSold,\n" +
                        "        b.Storeid,\n" +
                        "        ROW_NUMBER() OVER (PARTITION BY b.storeid ORDER BY SUM(b.num) DESC) AS RowNum\n" +
                        "    FROM\n" +
                        "        Product a\n" +
                        "    JOIN\n" +
                        "        Order_detail b ON a.ProductId = b.ProductId\n" +
                        "    GROUP BY\n" +
                        "        a.ProductId, a.ProductName, a.Price, a.Linkimage, b.storeid\n" +
                        ")\n" +
                        "SELECT\n" +
                        "    ProductId,\n" +
                        "    ProductName,\n" +
                        "    Price,\n" +
                        "    Linkimage,\n" +
                        "    TotalSold,\n" +
                        "    Storeid\n" +
                        "FROM\n" +
                        "    RankedProducts\n" +
                        "WHERE\n" +
                        "    RowNum <= 5;\n";

                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                            String productId = resultSet.getString("ProductId");
                            String productName = resultSet.getString("ProductName");
                            float price = resultSet.getFloat("Price");
                            String anhsanpham = resultSet.getString("Linkimage");

                            Product product = new Product(productId, productName, price, anhsanpham);
                            productList.add(product);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            Log.e("Error", e.getMessage());
        }
        return productList;
    }

    // Update the getRecentStore method in DatabaseConnection class
    // Update the getRecentStore method in DatabaseConnection class
    public ArrayList<User> getRecentStore() {
        ArrayList<User> userArrayList = new ArrayList<>();
        Connection connection = getCon();

        try {
            if (checkConnection()) {
                // Update your query to correctly select the needed columns from AspNetUsers
                String query = "SELECT Id, Fullname, Email, Adress, Linkimage FROM AspNetUsers a, AspNetUserRoles b WHERE a.Id = b.UserId AND b.RoleId = 3";

                try (PreparedStatement statement = connection.prepareStatement(query);
                     ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String id = resultSet.getString("Id");
                        String fullname = resultSet.getString("Fullname");
                        String email = resultSet.getString("Email");
                        String address = resultSet.getString("Adress");
                        String linkimage = resultSet.getString("Linkimage");

                        User user = new User(email, id, address, 0, "", fullname, linkimage); // Set appropriate default values for int and String fields
                        userArrayList.add(user);
                    }
                }
            }
        } catch (SQLException e) {
            Log.e("Error", "Error fetching recent stores: " + e.getMessage());
            e.printStackTrace(); // Log the stack trace for debugging
        }
        return userArrayList;
    }


    public ArrayList<Order_Deatails> getDetails(int odId) {
        ArrayList<Order_Deatails> detailsList = new ArrayList<>();
        Connection connection = getCon();

        try {
            if (checkConnection()) {
                String query = "SELECT * FROM Orders a, Order_detail b WHERE a.Od_id = b.Od_id AND a.VoidanOder ='True' AND a.Od_id = ?";

                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setInt(1, odId); // Set the parameter for Od_id

                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                            int orderId = resultSet.getInt("Od_id");
                            String productId = resultSet.getString("Productid");
                            int quantity = resultSet.getInt("num");
                            float totalMoney = resultSet.getFloat("tt_money");
                            String storeId = resultSet.getString("Storeid");
                            String voucherCode = resultSet.getString("VoucherCode");
                            float totalInVoucher = resultSet.getFloat("Totalinvoucher");

                            Order_Deatails details = new Order_Deatails(orderId, productId, quantity, totalMoney, storeId, voucherCode, totalInVoucher);
                            detailsList.add(details);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            Log.e("Error", "Error fetching order details: " + e.getMessage());
            e.printStackTrace();
        }
        return detailsList;
    }


    public ArrayList<Bellindex> getbell() {
        ArrayList<Bellindex> productList = new ArrayList<>();
        Connection connection = getCon();

        try {
            if (checkConnection()) {
                String query = "SELECT * \n" +
                        "FROM Product a\n" +
                        "WHERE \n" +
                        "    DiscountStartTime > DATEADD(DAY, -7, GETDATE())\n" +
                        "    OR DateCreated > DATEADD(DAY, -7, GETDATE());"; // Removed the unnecessary closing parenthesis

                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    String productId = resultSet.getString("Productid");
                    String productName = resultSet.getString("Productname");
                    float price = resultSet.getFloat("Price");
                    String anhsanpham = resultSet.getString("Linkimage");
//                    int category = resultSet.getInt("Categoryid");
                    String discription = resultSet.getString("discription");
                    Date dateCreated = resultSet.getDate("dateCreated");
                    Bellindex product = new Bellindex(productId, productName, null, price, anhsanpham, discription, dateCreated);
                    productList.add(product);
                }
            }
        } catch (SQLException e) {
            Log.e("Error", e.getMessage());
        }
        return productList;

    }

    public int getCartQuantityForUser(String userId) {
        int cartQuantity = 0;
        Connection connection = getCon();

        try {
            if (checkConnection()) {
                String query = "SELECT COUNT(Quantity) FROM CartItem WHERE IdUser = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, userId); // Set the parameter value

                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    cartQuantity = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            Log.e("Error", e.getMessage());
        } finally {
//            closeConnection(connection);
        }
        return cartQuantity;
    }

    public ArrayList<Product> getPurchasedProducts(String userId) {
        ArrayList<Product> productList = new ArrayList<>();
        Connection connection = getCon();

        try {
            if (checkConnection()) {
                String query = "SELECT DISTINCT a.Productid, a.Productname, a.Price, a.Linkimage\n" +
                        "FROM Product a\n" +
                        "JOIN Order_detail b ON a.Productid = b.Productid\n" +
                        "JOIN Orders c ON b.Od_id = c.Od_id\n" +
                        "WHERE c.Od_name = ?";

                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, userId);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    String productId = resultSet.getString("Productid");
                    String productName = resultSet.getString("Productname");
                    float price = resultSet.getFloat("Price");
                    String linkImage = resultSet.getString("Linkimage");

                    // Assuming you have a constructor in your Product class
                    Product product = new Product(productId, productName, price, linkImage);
                    productList.add(product);
                }
            }
        } catch (SQLException e) {
            Log.e("Error", e.getMessage());
        }
        return productList;
    }

    public void addToTym(String userId, String productId, String productName, String image, String linkImage) {
        Connection connection = getCon();

        try {
            // Fetch the max ID from the Heartitem table
            int maxId = getMaxHeartItemId(connection);

            // Increment the max ID to get the new ID for the insertion
            int newId = maxId + 1;

            // Item doesn't exist in the heart (tym), insert a new item
            String insertQuery = "INSERT INTO Heartitem (ID, ProductName, Price, Image, Productid, Userid, Linkimage) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setInt(1, newId);
                preparedStatement.setString(2, productName);
                preparedStatement.setString(3, "50000");  // Assuming a default value for Price, you can modify this accordingly
                preparedStatement.setString(4, image);
                preparedStatement.setString(5, productId);
                preparedStatement.setString(6, userId);
                preparedStatement.setString(7, linkImage);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Helper method to get the max ID from the Heartitem table
    private int getMaxHeartItemId(Connection connection) throws SQLException {
        int maxId = 0;

        String getMaxIdQuery = "SELECT MAX(ID) FROM Heartitem";
        try (PreparedStatement maxIdStatement = connection.prepareStatement(getMaxIdQuery);
             ResultSet resultSet = maxIdStatement.executeQuery()) {
            if (resultSet.next()) {
                maxId = resultSet.getInt(1);
            }
        }

        return maxId;
    }


}
