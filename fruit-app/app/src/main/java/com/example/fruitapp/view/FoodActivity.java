package com.example.fruitapp.view; // Thay <your>.<application> bằng package của bạn

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView; // Import ImageView
import com.bumptech.glide.Glide;
import com.example.fruitapp.R; // Make sure you have R imported

public class FoodActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food); // Giả định tên file layout là activity_food.xml

        // Load images using Glide
        Glide.with(this).load(R.drawable.navicon1)
                .into((ImageView) findViewById(R.id.rdlykz54xfnt)); // Back Arrow
        Glide.with(this).load(R.drawable.navicon2)
                .into((ImageView) findViewById(R.id.ryigvh46fwo)); // Filter
        Glide.with(this).load(R.drawable.dietcoke)
                .into((ImageView) findViewById(R.id.rb3bckbsw7e)); // Diet Coke
        Glide.with(this).load(R.drawable.sprite)
                .into((ImageView) findViewById(R.id.rcrw0z6k4hs)); // Sprite Can
        Glide.with(this).load(R.drawable.treetop2)
                .into((ImageView) findViewById(R.id.rjxfg0s1uas)); // Apple & Grape
        Glide.with(this).load(R.drawable.treetop)
                .into((ImageView) findViewById(R.id.r3jru8zts0j)); // Orange Juice
        Glide.with(this).load(R.drawable.cocacola)
                .into((ImageView) findViewById(R.id.rym59rphzurf)); // Coca Cola Can
        Glide.with(this).load(R.drawable.pepsi)
                .into((ImageView) findViewById(R.id.rt5rott1qx1l)); // Pepsi Can

        // Set onClickListeners for buttons (if needed)
        findViewById(R.id.rdlykz54xfnt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle back arrow click
                System.out.println("Back Arrow Pressed");
            }
        });

        findViewById(R.id.ryigvh46fwo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle filter click
                System.out.println("Filter Pressed");
            }
        });
    }
}