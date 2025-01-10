package com.example.fruitapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.fruitapp.adapter.ProductAdapter;
import com.example.fruitapp.R;
import com.example.fruitapp.model.Product;
import com.example.fruitapp.network.ApiService;
import com.example.fruitapp.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView productsRecyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList = new ArrayList<>();
    public static boolean invalidateMenu = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productsRecyclerView = findViewById(R.id.productsRecyclerView);
        productsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        productAdapter = new ProductAdapter(productList);
        productsRecyclerView.setAdapter(productAdapter);

        fetchProducts();
    }

    private void fetchProducts() {
        ApiService apiService = RetrofitClient.getApiService();
        Call<List<Product>> call = apiService.getProducts();

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    productList = response.body();
                    productAdapter.setProducts(productList);
                } else {
                    Log.e("API Error", "Response not successful or body is null");
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("API Error", "Failed to fetch products", t);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchProducts(); // Cập nhật danh sách sản phẩm khi quay lại MainActivity
        if (invalidateMenu) {
            invalidateOptionsMenu(); // Cập nhật menu
            invalidateMenu = false; // Reset lại biến
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        SharedPreferences sharedPreferences = getSharedPreferences("app_prefs", MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        MenuItem loginMenuItem = menu.findItem(R.id.login_menu_item);
        MenuItem registerMenuItem = menu.findItem(R.id.register_menu_item);
        MenuItem logoutMenuItem = menu.findItem(R.id.logout_menu_item);

        // Ẩn/hiện menu item dựa trên trạng thái đăng nhập
        loginMenuItem.setVisible(!isLoggedIn);
        registerMenuItem.setVisible(!isLoggedIn);
        logoutMenuItem.setVisible(isLoggedIn);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.register_menu_item) {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.logout_menu_item) {
            logoutUser();
            return true;
        } else if (item.getItemId() == R.id.login_menu_item) {
            // Chuyển đến LoginActivity
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logoutUser() {
        // Xóa trạng thái đăng nhập trong SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("app_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("isLoggedIn");
        editor.apply();

        // Chuyển về LoginActivity
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish(); // Đóng MainActivity

        // Cập nhật menu
        invalidateOptionsMenu();
    }
}