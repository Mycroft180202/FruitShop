package com.example.fruitapp.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fruitapp.R;
import com.example.fruitapp.model.ApiResponse;
import com.example.fruitapp.model.User;
import com.example.fruitapp.network.ApiService;
import com.example.fruitapp.network.RetrofitClient;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView registerTextView;

    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        apiService = RetrofitClient.getApiService();

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        registerTextView = findViewById(R.id.registerTextView);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hiển thị màn hình đăng ký (tạo RegisterActivity nếu chưa có)
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loginUser() {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
            return;
        }

        // Tạo một đối tượng User tạm thời với username và password
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        // Thực hiện cuộc gọi API để đăng nhập
        Call<ApiResponse> call = apiService.loginUser(user);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    ApiResponse apiResponse = response.body();
                    if (apiResponse.isSuccess()) {
                        // Xử lý đăng nhập thành công
                        Toast.makeText(LoginActivity.this, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();

                        // Lưu trạng thái đăng nhập
                        SharedPreferences sharedPreferences = getSharedPreferences("app_prefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("isLoggedIn", true);
                        editor.apply();

                        // Cập nhật menu
                        MainActivity.invalidateMenu = true;

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish(); // Đóng LoginActivity
                    } else {
                        // Xử lý lỗi đăng nhập
                        Toast.makeText(LoginActivity.this, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Xử lý lỗi từ server (không success)
                    try {
                        String errorBody = response.errorBody().string();
                        Toast.makeText(LoginActivity.this, "Login failed: " + errorBody, Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        Log.e("LoginActivity", "Error reading error body", e);
                    }
                }
            }


            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                // Xử lý lỗi kết nối
                Log.e("LoginActivity", "Error: " + t.getMessage());
                Toast.makeText(LoginActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}