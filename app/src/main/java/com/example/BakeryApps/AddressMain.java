package com.example.BakeryApps;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class AddressMain extends FragmentActivity implements OnMapReadyCallback, LocationListener {
    private GoogleMap mMap;
    LocationManager locationManager;
    private String addLoc;
    static String Pref_Name = "MyFile";
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://bakery-application-b53de-default-rtdb.firebaseio.com/");
    FirebaseAuth ID;
    String UID;
    String address,city,country;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        Button button = findViewById(R.id.addLocation);
        ID = FirebaseAuth.getInstance();
        UID = ID.getCurrentUser().getUid();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        requestPermission();
        isLocationEnabledOrNot();
        getLocation();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(addLoc!=null)
                {
                    databaseReference.child("Address").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            databaseReference.child("Address").child(UID).child("RoadAddress").setValue(addLoc);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    SharedPreferences sharedPreferences = getSharedPreferences(Pref_Name, 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("hasSetLoc", true);
                    editor.commit();
                }
                else
                {
                    databaseReference.child("Address").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            databaseReference.child("Address").child(UID).child("RoadAddress").setValue("");
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                startActivity(new Intent(AddressMain.this, AccMain.class));
                finish();


            }
        });

    }
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
    @Override
    public void onLocationChanged(Location location) {
        try {
            ProgressDialog progressDialog = new ProgressDialog(AddressMain.this);
            progressDialog.setTitle("Uploading Recourse ");
            progressDialog.setMessage("Please wait for a while!");
            progressDialog.show();

            Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
            List<android.location.Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            double longitude = addresses.get(0).getLongitude();
            double latitude = addresses.get(0).getLatitude();
            LatLng latLng=new LatLng(latitude,longitude);
            mMap.addMarker(new MarkerOptions().position(latLng).title("My Current Location:").snippet("Latitude: "+latitude +","+"Longitude "+longitude).icon(BitmapDescriptorFactory.fromResource(R.drawable.rsz_map2)));
            float zoomLevel = 16.0f; //This goes up to 21
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel));
            if (addresses != null && addresses.size() > 0) {
                 address = addresses.get(0).getAddressLine(0);
                 city = addresses.get(0).getLocality();
                 country = addresses.get(0).getCountryName();
                 addLoc= (address + " " + city + " " + country);
                progressDialog.dismiss();

            }
            else{
                addLoc= (".");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras){

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
    private void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(locationManager.NETWORK_PROVIDER, 0, 10, (LocationListener) this);

        } catch (SecurityException e) {
            e.printStackTrace();

        }
    }

    private void requestPermission() {
        if ((ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 400);


        }
    }

    private void isLocationEnabledOrNot() {
        LocationManager ln = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean gpsEnabled = false;
        boolean networkEnabled = false;
        try {
            gpsEnabled = ln.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            networkEnabled = ln.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!gpsEnabled && !networkEnabled) {
            new AlertDialog.Builder(this).setTitle("Enable GPS Service:").setCancelable(false).setPositiveButton("Enable", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                        }
                    }).setNegativeButton("Cancel", null)
                    .show();
        }

    }
}