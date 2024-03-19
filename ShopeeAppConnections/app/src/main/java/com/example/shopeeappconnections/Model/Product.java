package com.example.shopeeappconnections.Model;

public class Product implements Comparable<Product>{
    @Override
    public int compareTo(Product other) {
        // Compare products based on their prices in descending order
        return Float.compare(other.getPrice(), this.getPrice());
    }


    private String productId;
    private String productName;
    private String Category;
    private float price;

    private String Linkimage;
    // Default constructor
    public Product() {

    }

    // Parameterized constructor
    public Product(String productId, String productName, float price,String Linkimage) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.Linkimage= Linkimage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getLinkimage() {
        return Linkimage;
    }

    public void setLinkimage(String linkimage) {
        Linkimage = linkimage;
    }
}
