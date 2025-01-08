package com.example.fruitapp.model;

import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;

public class Product {
    @SerializedName("productId")
    private Long productId;

    @SerializedName("productName")
    private String productName;

    @SerializedName("description")
    private String description;

    @SerializedName("price")
    private BigDecimal price;

    @SerializedName("quantityInStock")
    private Integer quantityInStock;

    @SerializedName("imageURL")
    private String imageURL;

    @SerializedName("categoryId")
    private Long categoryId;

    @SerializedName("categoryName")
    private String categoryName;

    // Constructor, getters, and setters

    public Product(Long productId, String productName, String description, BigDecimal price, Integer quantityInStock, String imageURL, Long categoryId, String categoryName) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.quantityInStock = quantityInStock;
        this.imageURL = imageURL;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    // Getters and setters for each field

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}