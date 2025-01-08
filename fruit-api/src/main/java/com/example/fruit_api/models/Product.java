package com.example.fruit_api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "Products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @NotBlank(message = "Product name is required")
    private String productName;

    private String description;

    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price must be greater than or equal to 0")
    private BigDecimal price;

    @NotNull(message = "Quantity in stock is required")
    @Min(value = 0, message = "Quantity in stock must be greater than or equal to 0")
    private Integer quantityInStock;

    private String imageURL;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;
}