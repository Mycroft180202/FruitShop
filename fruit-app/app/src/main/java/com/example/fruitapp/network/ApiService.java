package com.example.fruitapp.network;

import com.example.fruitapp.model.ApiResponse;
import com.example.fruitapp.model.Category;
import com.example.fruitapp.model.Product;
import com.example.fruitapp.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

public interface ApiService {
    @GET("/api/products")
    Call<List<Product>> getProducts();

    @GET("/api/categories")
    Call<List<Category>> getCategories();

    @POST("/api/products")
    Call<Product> addProduct(@Body Product product);

    @POST("/api/login")
    Call<ApiResponse> loginUser(@Body User user);

    @POST("/api/register")
    Call<ApiResponse> registerUser(@Body User user);
}