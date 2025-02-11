package com.example.fruitapp.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fruitapp.R;

public class FavouriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        // Load images using Glide
        ImageView rwktm2txz9e = findViewById(R.id.rwktm2txz9e);
        ImageView rfavtxe0ov3 = findViewById(R.id.rfavtxe0ov3);
        ImageView r29oirid5acl = findViewById(R.id.r29oirid5acl);
        ImageView r424kgqx5gce = findViewById(R.id.r424kgqx5gce);
        ImageView rafibb6snhn9 = findViewById(R.id.rafibb6snhn9);

        Glide.with(this).load(R.drawable.shopicon).into(rwktm2txz9e);
        Glide.with(this).load(R.drawable.searchicon3).into(rfavtxe0ov3);
        Glide.with(this).load(R.drawable.shopicon1).into(r29oirid5acl);
        Glide.with(this).load(R.drawable.bookmarklive).into(r424kgqx5gce);
        Glide.with(this).load(R.drawable.usericon).into(rafibb6snhn9);

        // Set click listeners for the buttons
        LinearLayout r94lw6hhbkj = findViewById(R.id.r94lw6hhbkj);
        LinearLayout rk4axxka3ecc = findViewById(R.id.rk4axxka3ecc);
        LinearLayout rev9z0dimi2k = findViewById(R.id.rev9z0dimi2k);
        LinearLayout rnvck2gm5xw = findViewById(R.id.rnvck2gm5xw);
        LinearLayout rytkf4vq1xy = findViewById(R.id.rytkf4vq1xy);
        LinearLayout rnzb9sgsxcbb = findViewById(R.id.rnzb9sgsxcbb);

        r94lw6hhbkj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Pressed r94lw6hhbkj");
            }
        });

        rk4axxka3ecc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Pressed rk4axxka3ecc");
            }
        });

        rev9z0dimi2k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Pressed rev9z0dimi2k");
            }
        });

        rnvck2gm5xw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Pressed rnvck2gm5xw");
            }
        });

        rytkf4vq1xy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Pressed rytkf4vq1xy");
            }
        });

        rnzb9sgsxcbb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Pressed rnzb9sgsxcbb");
            }
        });

        // Initialize RecyclerView
        RecyclerView lvr38xjjwrgu9b = findViewById(R.id.r38xjjwrgu9b);
        Object[] arr = new Object[5]; // Replace 5 with the actual number of items
        LinearLayoutManager layoutManager38xjjwrgu9b = new LinearLayoutManager(this);
        layoutManager38xjjwrgu9b.setOrientation(LinearLayoutManager.VERTICAL);
        lvr38xjjwrgu9b.setLayoutManager(layoutManager38xjjwrgu9b);
    }
}