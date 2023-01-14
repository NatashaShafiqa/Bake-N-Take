package com.example.BakeryApps;


import android.os.Bundle;

import com.example.BakeryApps.databinding.ActivityBakeryMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BakeryMain extends Drawer_base {

    ActivityBakeryMainBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind= ActivityBakeryMainBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        getSupportFragmentManager().beginTransaction().replace(R.id.wrapper,new shopCakeMain()).commit();

    }

}