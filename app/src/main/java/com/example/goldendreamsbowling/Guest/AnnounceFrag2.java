package com.example.goldendreamsbowling.Guest;

import android.content.Intent;
import android.os.Bundle;

import com.example.goldendreamsbowling.databinding.ActivityAnnounceFrag2Binding;

public class AnnounceFrag2 extends DrawerGuest {

    ActivityAnnounceFrag2Binding activityAnnounceFragmentBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAnnounceFragmentBinding =activityAnnounceFragmentBinding.inflate(getLayoutInflater());
        setContentView(activityAnnounceFragmentBinding.getRoot());
        allocateActivityTitle("Announcement");
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainInterface.class));
        overridePendingTransition(0,0);
        finish();}
}