package com.example.goldendreamsbowling.LoggedInUser;

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
import android.widget.Toast;

import com.example.goldendreamsbowling.Guest.merchFrag2;
import com.example.goldendreamsbowling.HomePage;
import com.example.goldendreamsbowling.Login;
import com.example.goldendreamsbowling.R;
import com.example.goldendreamsbowling.SubsMember;
import com.example.goldendreamsbowling.databinding.ActivityDrawerBaseBinding;
import com.google.android.material.navigation.NavigationView;

public class Drawer_base extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

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
        drawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_drawer_base,null);
        FrameLayout container = drawerLayout.findViewById(R.id.activityContainer);
        container.addView(view);
        super.setContentView(drawerLayout);

        Toolbar toolbar = drawerLayout.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = drawerLayout.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

         actionBarDrawerToggle  = new ActionBarDrawerToggle(this, drawerLayout,R.string.nav_open, R.string.nav_close);
        actionBarDrawerToggle.syncState();
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        switch (item.getItemId()) {
            case R.id.nav_home:
                startActivity(new Intent(this, HomePage.class));
                overridePendingTransition(0, 0);
                break;
            case R.id.nav_booking:
                SharedPreferences sharedPreferences = getSharedPreferences(Login.Pref_Name, 0);
                boolean hasLogin = sharedPreferences.getBoolean("hasLoggedIn", false);
                if (hasLogin) {
                    startActivity(new Intent(this, BookingFragment.class));
                } else {
                    startActivity(new Intent(this, Login.class));
                }
                overridePendingTransition(0, 0);
                break;

            case R.id.nav_member:
                SharedPreferences sharedPreferences1 = getSharedPreferences(Login.Pref_Name, 0);
                boolean hasLogin1 = sharedPreferences1.getBoolean("hasSubbed", false);
                if (hasLogin1) {
                    startActivity(new Intent(this, membersFragment.class));
                } else {
                    startActivity(new Intent(this, SubsMember.class));
                }
                overridePendingTransition(0, 0);
                break;
            case R.id.nav_announce:
                startActivity(new Intent(this, announceFragment.class));
                overridePendingTransition(0, 0);
                break;
            case R.id.nav_merch:
                startActivity(new Intent(this, merchFragment.class));
                overridePendingTransition(0, 0);
                break;
            case R.id.nav_promo:
                SharedPreferences sharedPreferences2 = getSharedPreferences(Login.Pref_Name, 0);
                boolean hasLogin2 = sharedPreferences2.getBoolean("hasSubbed", false);
                if (hasLogin2) {
                    startActivity(new Intent(this, promoFragment.class));
                } else {
                    startActivity(new Intent(this, SubsMember.class));
                }
                overridePendingTransition(0, 0);
                break;
            case R.id.nav_setting:
                Toast.makeText(getApplicationContext(), "You navigated to settings screen", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.nav_aboutus:
                startActivity(new Intent(this, merchFrag2.aboutusFragment.class));
                overridePendingTransition(0, 0);
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