package com.example.fruitapp.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.example.fruitapp.R

class Welcome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        Glide.with(this).load(R.drawable.zzz).into(findViewById(R.id.rv3ou0iybfor))
        Glide.with(this).load(R.drawable.crot).into(findViewById(R.id.rc0caei6azh7))

        val getStartedButton = findViewById<LinearLayout>(R.id.r9postzchf97)

        // Set OnClickListener cho LinearLayout "Get Started"
        getStartedButton.setOnClickListener {
            // Tạo Intent để chuyển sang LoginActivity
            val intent = Intent(this, LoginActivity::class.java)

            // Khởi chạy LoginActivity
            startActivity(intent)
        }
    }
}