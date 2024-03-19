package com.example.shopeeappconnections.Model;

import java.io.Serializable;

public class Cartitem implements Serializable {
    private int id;
    private String productName;
    private float price;
    private String image;
    private int quantity;
    private float money;
    private int productId;
    private String userId;

    private String Linkimage;

    public Cartitem(int id, String productName, float price, String image, int quantity, float money, int productId, String userId,String linkimage) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.image = image;
        this.quantity = quantity;
        this.money = money;
        this.productId = productId;
        this.userId = userId;
        this.Linkimage=linkimage;
    }
    public float getTotalPrice() {
        return price * quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getUserId() {
        return userId;
    }

    public String getLinkimage() {
        return Linkimage;
    }

    public void setLinkimage(String linkimage) {
        Linkimage = linkimage;
    }
}
