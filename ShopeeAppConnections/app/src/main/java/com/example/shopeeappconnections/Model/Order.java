package com.example.shopeeappconnections.Model;

import com.example.shopeeappconnections.Connection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Order {
    private int Od_id;
    private String Od_name;
    private String Od_email;
    private float phone;
    private String diachi;
    private String note;
    private String Date;
    private int thanhtoan;
    private boolean trangthai;
    private List<Order_Deatails> orderDetailsList;
    private  boolean VoidanOder;
    public List<Order_Deatails> getOrderDetailsList() {
        return orderDetailsList;
    }

    public void setOrderDetailsList(List<Order_Deatails> orderDetailsList) {
        this.orderDetailsList = orderDetailsList;
    }


    public Order(int od_id, String od_name, String od_email, float phone, String diachi, String note, String date, int thanhtoan, boolean trangthai, boolean voidanOder) {
        Od_id = od_id;
        Od_name = od_name;
        Od_email = od_email;
        this.phone = phone;
        this.diachi = diachi;
        this.note = note;
        Date = date;
        this.thanhtoan = thanhtoan;
        this.trangthai = trangthai;
        this.VoidanOder =voidanOder;
    }

    public int getOd_id() {
        return Od_id;
    }

    public void setOd_id(int od_id) {
        Od_id = od_id;
    }

    public String getOd_name() {
        return Od_name;
    }

    public void setOd_name(String od_name) {
        Od_name = od_name;
    }

    public String getOd_email() {
        return Od_email;
    }

    public void setOd_email(String od_email) {
        Od_email = od_email;
    }

    public float getPhone() {
        return phone;
    }

    public void setPhone(float phone) {
        this.phone = phone;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getThanhtoan() {
        return thanhtoan;
    }

    public void setThanhtoan(int thanhtoan) {
        this.thanhtoan = thanhtoan;
    }

    public boolean isTrangthai() {
        return trangthai;
    }

    public void setTrangthai(boolean trangthai) {
        this.trangthai = trangthai;
    }


    public boolean isVoidanOder() {
        return VoidanOder;
    }

    public void setVoidanOder(boolean voidanOder) {
        VoidanOder = voidanOder;
    }
    public void updateOrderStatusInDatabase() {
        // Assuming you have a method named getCon in your DatabaseConnection class
        try (Connection connection = new DatabaseConnection().getCon();
             PreparedStatement statement = connection.prepareStatement("UPDATE Orders SET Od_status = 'false' WHERE Od_id = ?")) {
            statement.setInt(2, getOd_id());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Order status updated successfully.");
            } else {
                System.out.println("Failed to update order status.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
