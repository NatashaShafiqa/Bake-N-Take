package com.example.goldendreamsbowling.LoggedInUser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.goldendreamsbowling.Booking.CancelUpdateBooking;
import com.example.goldendreamsbowling.Booking.StartBook;
import com.example.goldendreamsbowling.Booking.ViewBooking;
import com.example.goldendreamsbowling.HomePage;
import com.example.goldendreamsbowling.R;
import com.example.goldendreamsbowling.databinding.ActivityBookingFragmentBinding;

public class BookingFragment extends Drawer_base {

    Button addBook,ViewBook,CancelUpdate,BookShoes;

    ActivityBookingFragmentBinding activityBookingFragmentBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBookingFragmentBinding= activityBookingFragmentBinding.inflate(getLayoutInflater());
        setContentView(activityBookingFragmentBinding.getRoot());

        addBook = findViewById(R.id.addBook);
        ViewBook = findViewById(R.id.viewBooking);
        CancelUpdate = findViewById(R.id.cancelUpdate);

        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StartBook.class);
                startActivity(intent);
                finish();
            }
        });

        ViewBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewBooking.class);
                startActivity(intent);
                finish();
            }
        });

        CancelUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CancelUpdateBooking.class);
                startActivity(intent);
                finish();
            }
        });


    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, HomePage.class));
        overridePendingTransition(0,0);
        finish();
    }
}