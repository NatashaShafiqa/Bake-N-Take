package com.example.goldendreamsbowling.Guest;

import android.content.Intent;
import android.os.Bundle;

import com.example.goldendreamsbowling.databinding.ActivityAbout2Binding;

public class About2 extends DrawerGuest {

    ActivityAbout2Binding activityAboutusFragmentBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAboutusFragmentBinding = activityAboutusFragmentBinding.inflate(getLayoutInflater());
        setContentView(activityAboutusFragmentBinding.getRoot());
        allocateActivityTitle("About Us");
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainInterface.class));
        overridePendingTransition(0,0);
        finish();
    }
}