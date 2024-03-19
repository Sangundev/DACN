package com.example.shopeeappconnections.Model;

import java.util.ArrayList;
import java.util.List;

public class Tinhtang {
    private String image;
    private String Name;

    public Tinhtang(String image, String name) {
        this.image = image;
        Name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<Tinhtang> tinhtangList() {
        List<Tinhtang> list = new ArrayList<>();
        list.add(new Tinhtang("cargo", "Đã Đặt"));
        list.add(new Tinhtang("order", "Dã Thanh Toán"));
        list.add(new Tinhtang("delivery", "Thanh toán khi nhận hàng"));
        list.add(new Tinhtang("cancel", "Đã Hủy"));
        return list;
    }
}
