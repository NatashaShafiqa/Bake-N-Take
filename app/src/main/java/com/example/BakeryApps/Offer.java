package com.example.BakeryApps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.BakeryApps.LoggedInUser.Drawer_base;
import com.example.BakeryApps.databinding.ActivityOfferBinding;

public class Offer extends Drawer_base {

    ActivityOfferBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}