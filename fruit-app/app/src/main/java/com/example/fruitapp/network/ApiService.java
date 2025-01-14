package com.example.fruitapp.network;

import com.example.fruitapp.model.ApiResponse;
import com.example.fruitapp.model.Category;
import com.example.fruitapp.model.Product;
import com.example.fruitapp.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import java.util.List;

public interface ApiService {
    @GET("/api/products")
    Call<List<Product>> getProducts();

    @GET("/api/products/{id}")
    Call<Product> getProductById(@Path("id") Long productId);

    @POST("/api/products")
    Call<Product> addProduct(@Body Product product);

    @PUT("/api/products/{id}")
    Call<Product> updateProduct(@Path("id") Long id, @Body Product product);

    @GET("/api/categories")
    Call<List<Category>> getCategories();

    @POST("/api/login")
    Call<ApiResponse> loginUser(@Body User user);

    @POST("/api/register")
    Call<ApiResponse> registerUser(@Body User user);
}