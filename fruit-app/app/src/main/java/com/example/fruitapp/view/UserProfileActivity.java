package com.example.fruitapp.view;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.fruitapp.R;

public class UserProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        ImageView r900havd3wrt = findViewById(R.id.r900havd3wrt);
        ImageView rya0o8o3m7fm = findViewById(R.id.rya0o8o3m7fm);
        ImageView roopzgon3ib = findViewById(R.id.roopzgon3ib);
        ImageView rbjzc4znhist = findViewById(R.id.rbjzc4znhist);
        ImageView r42iwctib2g2 = findViewById(R.id.r42iwctib2g2);
        ImageView rw51pdj1hq9 = findViewById(R.id.rw51pdj1hq9);
        ImageView r9fgyv2boqmg = findViewById(R.id.r9fgyv2boqmg);
        ImageView rhpg6umxugm = findViewById(R.id.rhpg6umxugm);
        ImageView rjv3rls3ucf = findViewById(R.id.rjv3rls3ucf);
        ImageView rq0mhb3r5qn = findViewById(R.id.rq0mhb3r5qn);
        ImageView rrj3uc011xd = findViewById(R.id.rrj3uc011xd);
        ImageView ru75hcmv5qt = findViewById(R.id.ru75hcmv5qt);
        ImageView r3r32fkokd1v = findViewById(R.id.r3r32fkokd1v);
        ImageView rn6qqf21n27h = findViewById(R.id.rn6qqf21n27h);
        ImageView rda0ya94b6ks = findViewById(R.id.rda0ya94b6ks);
        ImageView rply3dy33y0p = findViewById(R.id.rply3dy33y0p);
        ImageView ra9k9d8cnj6c = findViewById(R.id.ra9k9d8cnj6c);
        ImageView ruokwqr2vh3 = findViewById(R.id.ruokwqr2vh3);
        ImageView rs70xwedgfw = findViewById(R.id.rs70xwedgfw);
        ImageView r2tt7umlfzfg = findViewById(R.id.r2tt7umlfzfg);
        ImageView ryx50o2rhvs = findViewById(R.id.ryx50o2rhvs);
        ImageView rpo7lj8tuaf = findViewById(R.id.rpo7lj8tuaf);
        ImageView rdzdegu4qeq5 = findViewById(R.id.rdzdegu4qeq5);
        ImageView rfpp8qv7i5oo = findViewById(R.id.rfpp8qv7i5oo);

        Glide.with(this).load(R.drawable.avatar).into(r900havd3wrt);
        Glide.with(this).load(R.drawable.greenpencilicon).into(rya0o8o3m7fm);
        Glide.with(this).load(R.drawable.shoppingicon1).into(roopzgon3ib);
        Glide.with(this).load(R.drawable.navicon3).into(rbjzc4znhist);
        Glide.with(this).load(R.drawable.userdetailicon).into(r42iwctib2g2);
        Glide.with(this).load(R.drawable.navicon3).into(rw51pdj1hq9);
        Glide.with(this).load(R.drawable.mapicon1).into(r9fgyv2boqmg);
        Glide.with(this).load(R.drawable.navicon3).into(rhpg6umxugm);
        Glide.with(this).load(R.drawable.cardicon1).into(rjv3rls3ucf);
        Glide.with(this).load(R.drawable.navicon3).into(rq0mhb3r5qn);
        Glide.with(this).load(R.drawable.ticketicon1).into(rrj3uc011xd);
        Glide.with(this).load(R.drawable.navicon3).into(ru75hcmv5qt);
        Glide.with(this).load(R.drawable.noticeicon2).into(r3r32fkokd1v);
        Glide.with(this).load(R.drawable.navicon3).into(rn6qqf21n27h);
        Glide.with(this).load(R.drawable.questionmarkicon).into(rda0ya94b6ks);
        Glide.with(this).load(R.drawable.navicon3).into(rply3dy33y0p);
        Glide.with(this).load(R.drawable.noticeicon1).into(ra9k9d8cnj6c);
        Glide.with(this).load(R.drawable.navicon3).into(ruokwqr2vh3);
        Glide.with(this).load(R.drawable.logouticon).into(rs70xwedgfw);
        Glide.with(this).load(R.drawable.shopicon).into(r2tt7umlfzfg);
        Glide.with(this).load(R.drawable.searchicon3).into(ryx50o2rhvs);
        Glide.with(this).load(R.drawable.shopicon1).into(rpo7lj8tuaf);
        Glide.with(this).load(R.drawable.favouriteicon).into(rdzdegu4qeq5);
        Glide.with(this).load(R.drawable.usergreenicon).into(rfpp8qv7i5oo);

        // Set click listener for rya0o8o3m7fm
        rya0o8o3m7fm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Pressed");
            }
        });
    }
}