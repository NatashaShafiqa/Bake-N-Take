package com.example.goldendreamsbowling.Guest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.goldendreamsbowling.HomePage;
import com.example.goldendreamsbowling.Login;
import com.example.goldendreamsbowling.R;
import com.example.goldendreamsbowling.databinding.ActivityDrawerBaseBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;

public class DrawerGuest extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setContentView(View view) {
        drawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_drawer_guest,null);
        FrameLayout container = drawerLayout.findViewById(R.id.activityContainer);
        container.addView(view);
        super.setContentView(drawerLayout);

        Toolbar toolbar = drawerLayout.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = drawerLayout.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        actionBarDrawerToggle  = new ActionBarDrawerToggle(this, drawerLayout,R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        switch (item.getItemId())
        {
            case R.id.nav_home:
                startActivity(new Intent(this, MainInterface.class));
                overridePendingTransition(0,0);
                break;
            case R.id.nav_announce:
                startActivity(new Intent(this, AnnounceFrag2.class));
                overridePendingTransition(0,0);
                break;
            case R.id.nav_merch:
                startActivity(new Intent(this, merchFrag2.class));
                overridePendingTransition(0,0);
                break;
            case R.id.nav_login:
                SharedPreferences sharedPreferences = getSharedPreferences(Login.Pref_Name,0);
                boolean hasLogin = sharedPreferences.getBoolean("hasLoggedIn",false);
                if(hasLogin)
                {
                    startActivity(new Intent(this, HomePage.class));
                }
                else
                {
                    startActivity(new Intent(this,Login.class));
                }
                overridePendingTransition(0,0);
                break;
            case R.id.nav_aboutus:
                startActivity(new Intent(this, About2.class));
                overridePendingTransition(0,0);
                break;
        }

        return false;
    }

    protected void allocateActivityTitle(String StringTitle){
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(StringTitle);
        }
    }


}