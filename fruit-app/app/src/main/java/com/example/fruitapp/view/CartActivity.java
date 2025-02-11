package com.example.fruitapp.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.fruitapp.R;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        ImageView rultiimchin = findViewById(R.id.rultiimchin);
        ImageView r52fsgujxuvq = findViewById(R.id.r52fsgujxuvq);
        ImageView rpzyhi6lgin = findViewById(R.id.rpzyhi6lgin);
        ImageView ryy2lj54pn9p = findViewById(R.id.ryy2lj54pn9p);
        ImageView rzyxn0o3nzyn = findViewById(R.id.rzyxn0o3nzyn);
        ImageView r0u0qnjdi2epg = findViewById(R.id.r0u0qnjdi2epg);
        ImageView rm95jx4pc66n = findViewById(R.id.rm95jx4pc66n);
        ImageView rvqdycff26x7 = findViewById(R.id.rvqdycff26x7);
        ImageView ry4od51oa7c = findViewById(R.id.ry4od51oa7c);
        ImageView rx6eaii7egze = findViewById(R.id.rx6eaii7egze);
        ImageView reqpy2ysp3q = findViewById(R.id.reqpy2ysp3q);
        ImageView rcderpzdn1a9 = findViewById(R.id.rcderpzdn1a9);
        ImageView r097b3jdb8p93 = findViewById(R.id.r097b3jdb8p93);
        View r8yol555q7gb = findViewById(R.id.r8yol555q7gb);
        View riw9v1zc1ng = findViewById(R.id.riw9v1zc1ng);
        View ru4hh2lw91w8 = findViewById(R.id.ru4hh2lw91w8);
        View r3jd4zkji7q2 = findViewById(R.id.r3jd4zkji7q2);
        View runn2hnofg5 = findViewById(R.id.runn2hnofg5);
        View rugrvvdowva = findViewById(R.id.rugrvvdowva);

        Glide.with(this).load(R.drawable.bellpepper).into(rultiimchin);
        Glide.with(this).load(R.drawable.xicon).into(r52fsgujxuvq);
        Glide.with(this).load(R.drawable.egg).into(rpzyhi6lgin);
        Glide.with(this).load(R.drawable.xicon).into(ryy2lj54pn9p);
        Glide.with(this).load(R.drawable.banana).into(rzyxn0o3nzyn);
        Glide.with(this).load(R.drawable.xicon).into(r0u0qnjdi2epg);
        Glide.with(this).load(R.drawable.ginger).into(rm95jx4pc66n);
        Glide.with(this).load(R.drawable.xicon).into(rvqdycff26x7);
        Glide.with(this).load(R.drawable.shopicon).into(ry4od51oa7c);
        Glide.with(this).load(R.drawable.searchicon3).into(rx6eaii7egze);
        Glide.with(this).load(R.drawable.greenshoppingicon).into(reqpy2ysp3q);
        Glide.with(this).load(R.drawable.favouriteicon).into(rcderpzdn1a9);
        Glide.with(this).load(R.drawable.usericon).into(r097b3jdb8p93);

        // Set click listeners for the buttons
        r52fsgujxuvq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Pressed r52fsgujxuvq");
            }
        });

        ryy2lj54pn9p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Pressed ryy2lj54pn9p");
            }
        });

        r0u0qnjdi2epg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Pressed r0u0qnjdi2epg");
            }
        });

        rvqdycff26x7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Pressed rvqdycff26x7");
            }
        });

        r8yol555q7gb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Pressed r8yol555q7gb");
            }
        });

        riw9v1zc1ng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Pressed riw9v1zc1ng");
            }
        });

        ru4hh2lw91w8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Pressed ru4hh2lw91w8");
            }
        });

        r3jd4zkji7q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Pressed r3jd4zkji7q2");
            }
        });

        runn2hnofg5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Pressed runn2hnofg5");
            }
        });

        rugrvvdowva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Pressed rugrvvdowva");
            }
        });
    }
}