package com.example.goldendreamsbowling.Guest;

import android.content.Intent;
import android.os.Bundle;

import com.example.goldendreamsbowling.LoggedInUser.Drawer_base;
import com.example.goldendreamsbowling.databinding.ActivityAboutusFragmentBinding;
import com.example.goldendreamsbowling.databinding.ActivityMerchFrag2Binding;

public class merchFrag2 extends DrawerGuest {

    ActivityMerchFrag2Binding activityMerchFragmentBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMerchFragmentBinding = activityMerchFragmentBinding.inflate(getLayoutInflater());
        setContentView(activityMerchFragmentBinding.getRoot());
        allocateActivityTitle("Merchandise");
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainInterface.class));
        overridePendingTransition(0,0);
        finish();
    }

    public static class aboutusFragment extends Drawer_base {

        ActivityAboutusFragmentBinding activityAboutusFragmentBinding;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            activityAboutusFragmentBinding = activityAboutusFragmentBinding.inflate(getLayoutInflater());
            setContentView(activityAboutusFragmentBinding.getRoot());
            allocateActivityTitle("About Us");
        }

        @Override
        public void onBackPressed() {
            startActivity(new Intent(this,MainInterface.class));
            overridePendingTransition(0,0);
            finish();
        }
    }
}