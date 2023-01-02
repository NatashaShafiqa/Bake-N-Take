package com.example.goldendreamsbowling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.goldendreamsbowling.Guest.merchFrag2;
import com.example.goldendreamsbowling.LoggedInUser.merchFragment;

public class Cap_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap_page);
    }

    @Override
    public void onBackPressed() {
        SharedPreferences sharedPreferences = getSharedPreferences(Login.Pref_Name,0);
        boolean hasLogin = sharedPreferences.getBoolean("hasLoggedIn",false);
        if(hasLogin)
        {
            startActivity(new Intent(Cap_page.this, merchFragment.class));
            finish();
        }
        else
        {
            startActivity(new Intent(Cap_page.this, merchFrag2.class));
            finish();
        }
    }
}