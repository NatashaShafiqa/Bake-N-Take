package com.example.BakeryApps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.BakeryApps.AccMain;
import com.example.BakeryApps.BakeryMain;
import com.example.BakeryApps.ViewOrderMain;
import com.example.BakeryApps.HomePage;
import com.example.BakeryApps.R;
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
            case R.id.nav_Shop:
                startActivity(new Intent(this, BakeryMain.class));
                overridePendingTransition(0, 0);
                break;
            case R.id.nav_account:
                startActivity(new Intent(this, AccMain.class));
                overridePendingTransition(0, 0);
                break;
            case R.id.nav_history:
                startActivity(new Intent(this, ViewOrderMain.class));
                overridePendingTransition(0, 0);
                break;
        }
        return false;
    }
    
}
