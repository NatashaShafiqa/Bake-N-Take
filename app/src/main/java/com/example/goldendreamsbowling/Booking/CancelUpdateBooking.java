package com.example.goldendreamsbowling.Booking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.goldendreamsbowling.R;
import com.example.goldendreamsbowling.databinding.ActivityCancelUpdateBookingBinding;

public class CancelUpdateBooking extends AppCompatActivity {

    ActivityCancelUpdateBookingBinding bind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind =bind.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
    }
}