package com.example.shopeeappconnections.Model;

public class comment_SP {
    private int id;
    private String content;
    private int product_id;

    private String user_id;

    private int rating;

    private String image;

    public comment_SP(int id, String content, int productId, String userId, int rating, String image) {
        this.id = id;
        this.content = content;
        product_id = productId;
        user_id = userId;
        this.rating = rating;
        this.image = image;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
