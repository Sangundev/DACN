package com.example.shopeeappconnections.Model;

public class ProductSale {
    private String productId;
    private String productName;
    private String linkimage;
    private float price;
    private float DiscountedPrice;
    private int DiscountPercent;

    public ProductSale(String productId, String productName, String linkimage, float price, float discountedPrice, int discountPercent) {
        this.productId = productId;
        this.productName = productName;
        this.linkimage = linkimage;
        this.price = price;
        this.DiscountedPrice = discountedPrice;
        this.DiscountPercent = discountPercent;
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

    public String getLinkimage() {
        return linkimage;
    }

    public void setLinkimage(String linkimage) {
        this.linkimage = linkimage;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDiscountedPrice() {
        return DiscountedPrice;
    }

    public void setDiscountedPrice(float discountedPrice) {
        DiscountedPrice = discountedPrice;
    }

    public int getDiscountPercent() {
        return DiscountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        DiscountPercent = discountPercent;
    }

}
