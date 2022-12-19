package com.example.goldendreamsbowling.Guest;

import android.content.Intent;
import android.os.Bundle;

import com.example.goldendreamsbowling.Guest.DrawerGuest;
import com.example.goldendreamsbowling.databinding.ActivityMainInterfaceBinding;

public class MainInterface extends DrawerGuest {

    private long pressedTime;
    ActivityMainInterfaceBinding bind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind =bind.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        allocateActivityTitle("Home");

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);

    }
}