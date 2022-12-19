package com.example.goldendreamsbowling.LoggedInUser;

import android.content.Intent;
import android.os.Bundle;

import com.example.goldendreamsbowling.HomePage;
import com.example.goldendreamsbowling.databinding.ActivityMembersFragmentBinding;

public class membersFragment extends Drawer_base {

    ActivityMembersFragmentBinding activityMembersFragmentBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMembersFragmentBinding = activityMembersFragmentBinding.inflate(getLayoutInflater());
        setContentView(activityMembersFragmentBinding.getRoot());
        allocateActivityTitle("Members");
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, HomePage.class));
        overridePendingTransition(0,0);
        finish();
    }
}