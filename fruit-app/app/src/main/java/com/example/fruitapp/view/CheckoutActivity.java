package com.example.fruitapp.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.fruitapp.R;

public class CheckoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        ImageView rgn3o8mqmw7q = findViewById(R.id.rgn3o8mqmw7q);
        ImageView re4d4fwfnr7g = findViewById(R.id.re4d4fwfnr7g);
        ImageView r022k4kvnjmuo = findViewById(R.id.r022k4kvnjmuo);
        ImageView rayo4pkgbqv = findViewById(R.id.rayo4pkgbqv);
        ImageView r1qfd984pkgx = findViewById(R.id.r1qfd984pkgx);
        ImageView ry862b3yhthm = findViewById(R.id.ry862b3yhthm);

        // Load images using Glide
        Glide.with(this).load("https://raw.githubusercontent.com/coredxor/images/main/z8.png").into(rgn3o8mqmw7q);
        Glide.with(this).load("https://raw.githubusercontent.com/coredxor/images/main/q2.png").into(re4d4fwfnr7g);
        Glide.with(this).load("https://raw.githubusercontent.com/coredxor/images/main/card.png").into(r022k4kvnjmuo);
        Glide.with(this).load("https://raw.githubusercontent.com/coredxor/images/main/q2.png").into(rayo4pkgbqv);
        Glide.with(this).load("https://raw.githubusercontent.com/coredxor/images/main/q2.png").into(r1qfd984pkgx);
        Glide.with(this).load("https://raw.githubusercontent.com/coredxor/images/main/q2.png").into(ry862b3yhthm);

        // Set OnClickListener for buttons
        View button1 = findViewById(R.id.rgn3o8mqmw7q);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Pressed");
            }
        });

        View button2 = findViewById(R.id.r576lolptmv4);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Pressed");
            }
        });

        View button3 = findViewById(R.id.r191bc5cnl2z);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Pressed");
            }
        });

        View button4 = findViewById(R.id.r63f6jtkgxpl);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Pressed");
            }
        });

        View button5 = findViewById(R.id.ra5rlkkoosb);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Pressed");
            }
        });

        View button6 = findViewById(R.id.rpse0s47qpjh);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Pressed");
            }
        });

        View button7 = findViewById(R.id.rngfsrtgqg3);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Pressed");
            }
        });

        View button8 = findViewById(R.id.rrpnupacd34);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Pressed");
            }
        });
    }
}