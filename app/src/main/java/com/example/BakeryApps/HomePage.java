package com.example.BakeryApps;

import android.content.Intent;
import android.os.Bundle;

import com.example.BakeryApps.LoggedInUser.Drawer_base;
import com.example.BakeryApps.databinding.ActivityHomePageBinding;

public class HomePage extends Drawer_base {

    ActivityHomePageBinding bind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind =bind.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

       // allocateActivityTitle("Home");

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);

    }
}
