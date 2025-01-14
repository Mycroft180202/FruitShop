package com.example.fruitapp.model;

import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName("categoryId")
    private Long categoryId;

    @SerializedName("categoryName")
    private String categoryName;

    // Constructors
    public Category() {
    }

    public Category(Long categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    // Getters and Setters
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