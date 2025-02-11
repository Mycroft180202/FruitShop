package com.example.fruitapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.fruitapp.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        ImageView backgroundImageView = (ImageView) findViewById(R.id.background1);
        Glide.with(this).load(R.drawable.sleepbackground).into(backgroundImageView);

        ImageView iconLoginImageView = (ImageView) findViewById(R.id.iconLogin);
        Glide.with(this).load(R.drawable.crot).into(iconLoginImageView);

        LinearLayout getStartedButton = (LinearLayout) findViewById(R.id.LNLogin3);

        // Set OnClickListener cho LinearLayout "Get Started"
        getStartedButton.setOnClickListener(v -> {
            // Tạo Intent để chuyển sang LoginActivity
            Intent intent = new Intent(this, LoginActivity.class);

            // Khởi chạy LoginActivity
            startActivity(intent);
        });
    }
}