package com.example.goldendreamsbowling;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.goldendreamsbowling.LoggedInUser.Drawer_base;
import com.example.goldendreamsbowling.databinding.ActivityAboutusFragmentBinding;

public class AboutUsFragment extends Drawer_base {

    ActivityAboutusFragmentBinding bind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind =bind.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
    }
}