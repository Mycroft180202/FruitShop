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
        Glide.with(this).load(R.drawable.searchicon2);
        Glide.with(this).load(R.drawable.vegetable);
        Glide.with(this).load(R.drawable.oil);
        Glide.with(this).load(R.drawable.meat);
        Glide.with(this).load(R.drawable.bread);
        Glide.with(this).load(R.drawable.milk);
        Glide.with(this).load(R.drawable.drink);
        Glide.with(this).load(R.drawable.shopicon);
        Glide.with(this).load(R.drawable.searchicon);
        Glide.with(this).load(R.drawable.shopicon1);
        Glide.with(this).load(R.drawable.favouriteicon);
        Glide.with(this).load(R.drawable.usericon);

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