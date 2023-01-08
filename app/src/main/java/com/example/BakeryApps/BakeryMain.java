package com.example.BakeryApps;


import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.BakeryApps.LoggedInUser.Drawer_base;
import com.example.BakeryApps.databinding.ActivityBakeryMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class BakeryMain extends Drawer_base {

    private BottomNavigationView bottomNavigationView;
    ActivityBakeryMainBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind= ActivityBakeryMainBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        getSupportFragmentManager().beginTransaction().replace(R.id.wrapper,new recfragment()).commit();

       // bottomNavigationView = findViewById(R.id.bottomNavigationView);
        //bottomNavigationView.setLabelVisibilityMode(NavigationBarView.LABEL_VISIBILITY_LABELED);
        //bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
    }

   /* private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()){
                        case R.id.nav_offer:
                            selectedFragment = new OfferFragment();
                            break;
                        case R.id.nav_buy:
                            selectedFragment = new BuyFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
    };

    */
}