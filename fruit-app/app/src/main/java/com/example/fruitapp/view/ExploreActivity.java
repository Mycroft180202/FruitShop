package com.example.fruitapp.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.fruitapp.R;

public class ExploreActivity extends AppCompatActivity {

    private String editTextValue1 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        // Load images using Glide
        Glide.with(this).load(R.drawable.v6);
        Glide.with(this).load(R.drawable.img5);
        Glide.with(this).load(R.drawable.img3);
        Glide.with(this).load(R.drawable.img2);
        Glide.with(this).load(R.drawable.img6);
        Glide.with(this).load(R.drawable.img7);
        Glide.with(this).load(R.drawable.img1);
        Glide.with(this).load(R.drawable.i1);
        Glide.with(this).load(R.drawable.i7);
        Glide.with(this).load(R.drawable.i4);
        Glide.with(this).load(R.drawable.i5);
        Glide.with(this).load(R.drawable.i2);

        // Set up TextWatcher for EditText
        EditText editText1 = findViewById(R.id.r1qgvuyepuxx);
        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // before Text Changed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editTextValue1 = s.toString();  // on Text Changed
            }

            @Override
            public void afterTextChanged(Editable s) {
                // after Text Changed
            }
        });
    }
}