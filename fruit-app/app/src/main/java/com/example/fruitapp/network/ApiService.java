package com.example.fruitapp.network;

import com.example.fruitapp.model.Product;
import retrofit2.Call;
import retrofit2.http.GET;
import java.util.List;

public interface ApiService {
    @GET("/api/products")
    Call<List<Product>> getProducts();
}