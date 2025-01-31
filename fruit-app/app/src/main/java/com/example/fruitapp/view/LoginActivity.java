package com.example.fruitapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.fruitapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText txtEmail, txtPassword;
    private LinearLayout loginButton;

    private static final String KEYCLOAK_SERVER_URL = "http://192.168.1.14:8080/api/auth/login"; // Ví dụ: "http://your-backend-api:8080/api/auth/login"

    private final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ImageView backgroundImageView = findViewById(R.id.r7ch6hrxa8ni);
        ImageView logoImageView = findViewById(R.id.r5vs71k7r26c);

        Glide.with(this)
                .load(R.drawable.bk_login)
                .into(backgroundImageView);

        Glide.with(this)
                .load(R.drawable.carot_login)
                .into(logoImageView);

        txtEmail = findViewById(R.id.txt_Email);
        txtPassword = findViewById(R.id.txt_Password);
        loginButton = findViewById(R.id.rztd9equtk3);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập email và mật khẩu", Toast.LENGTH_SHORT).show();
                    return;
                }

                performLoginWithOkHttp(username, password);
            }
        });
    }

    private void performLoginWithOkHttp(String username, String password) {
        RequestBody formBody = new FormBody.Builder()
                // **CHỈ GỬI username và password CHO BACKEND API**
                .add("username", username)
                .add("password", password)
                .build();

        Request request = new Request.Builder()
                // **URL API LOGIN BACKEND - KHÔNG TRỰC TIẾP ĐẾN KEYCLOAK**
                .url(KEYCLOAK_SERVER_URL) // Sử dụng KEYCLOAK_SERVER_URL (trỏ đến API Backend Login)
                .post(formBody)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("LoginActivity", "OkHttp onFailure: " + e.getMessage());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LoginActivity.this, "Lỗi kết nối mạng hoặc đăng nhập thất bại.", Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseBody = response.body().string();
                final int responseCode = response.code();

                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonResponse = new JSONObject(responseBody);
                        final String accessToken = jsonResponse.getString("access_token");
                        final String refreshToken = jsonResponse.getString("refresh_token");

                        Log.d("LoginActivity", "OkHttp Login successful!");
                        Log.d("LoginActivity", "Access Token: " + accessToken);
                        Log.d("LoginActivity", "Refresh Token: " + refreshToken);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                                // **TODO: Xử lý token (ví dụ: lưu vào SharedPreferences, chuyển sang màn hình chính)**
                                // **Ví dụ: Chuyển sang MainActivity sau khi đăng nhập thành công**
                                Intent intent = new Intent(LoginActivity.this, ExploreActivity.class);
                                startActivity(intent);
                                finish(); // Đóng LoginActivity
                            }
                        });

                    } catch (JSONException e) {
                        Log.e("LoginActivity", "JSONException parsing response: " + e.getMessage());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this, "Lỗi xử lý dữ liệu từ server.", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                } else {
                    Log.e("LoginActivity", "OkHttp Login failed with response code: " + responseCode + ", body: " + responseBody);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, "Đăng nhập thất bại. Vui lòng kiểm tra email và mật khẩu.", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }
}
