package com.example.goldendreamsbowling.LoggedInUser;

import android.content.Intent;
import android.os.Bundle;

import com.example.goldendreamsbowling.HomePage;
import com.example.goldendreamsbowling.databinding.ActivityPromoFragmentBinding;

public class promoFragment extends Drawer_base {

    ActivityPromoFragmentBinding activityPromoFragmentBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPromoFragmentBinding = activityPromoFragmentBinding.inflate(getLayoutInflater());
        setContentView(activityPromoFragmentBinding.getRoot());
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, HomePage.class));
        overridePendingTransition(0,0);
        finish();
    }
}