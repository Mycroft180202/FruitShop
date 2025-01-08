package com.example.fruit_api.dto;

import java.math.BigDecimal;

public class ProductDto {
    private Long productId;
    private String productName;
    private String description;
    private BigDecimal price;
    private Integer quantityInStock;
    private String imageURL;
    private Long categoryId;
    private String categoryName;

    // Constructor mặc định
    public ProductDto() {
    }

    // Constructor để mapping từ Product entity
    public ProductDto(com.example.fruit_api.models.Product product) {
        this.productId = product.getProductId();
        this.productName = product.getProductName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.quantityInStock = product.getQuantityInStock();
        this.imageURL = product.getImageURL();
        if (product.getCategory() != null) {
            this.categoryId = product.getCategory().getCategoryId();
            this.categoryName = product.getCategory().getCategoryName();
        }
    }

    // Getters and setters
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