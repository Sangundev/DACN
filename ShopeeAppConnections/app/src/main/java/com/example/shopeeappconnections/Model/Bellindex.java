package com.example.shopeeappconnections.Model;

import java.util.Date;

public class Bellindex {

    private String productId;
    private String productName;
    private String Category;
    private float price;

    private String Linkimage;

    private String discription;
    private Date DateCreated;

    public Bellindex(String productId, String productName, String category, float price, String linkimage, String discription, Date dateCreated) {
        this.productId = productId;
        this.productName = productName;
        Category = category;
        this.price = price;
        Linkimage = linkimage;
        this.discription = discription;
        DateCreated = dateCreated;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getLinkimage() {
        return Linkimage;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public Date getDateCreated() {
        return DateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        DateCreated = dateCreated;
    }
}
