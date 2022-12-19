package com.example.goldendreamsbowling.LoggedInUser;

import android.content.Intent;
import android.os.Bundle;

import com.example.goldendreamsbowling.HomePage;
import com.example.goldendreamsbowling.databinding.ActivityAnnounceFragmentBinding;

public class announceFragment extends Drawer_base {

    ActivityAnnounceFragmentBinding activityAnnounceFragmentBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAnnounceFragmentBinding =activityAnnounceFragmentBinding.inflate(getLayoutInflater());
        setContentView(activityAnnounceFragmentBinding.getRoot());
        allocateActivityTitle("Announcement");
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, HomePage.class));
        overridePendingTransition(0,0);
        finish();
    }
}