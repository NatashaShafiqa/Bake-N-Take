package com.example.BakeryApps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences(Login.Pref_Name,0);
                boolean hasLogin = sharedPreferences.getBoolean("hasLoggedIn",false);
                if(hasLogin)
                {
                    startActivity(new Intent(MainActivity.this, HomePage.class));
                    finish();
                }
                else
                {
                    startActivity(new Intent(MainActivity.this, Login.class));
                    finish();
                }

            }
        },650);

    }
}
