package com.example.goldendreamsbowling.Booking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.goldendreamsbowling.LoggedInUser.BookingFragment;
import com.example.goldendreamsbowling.R;

import com.example.goldendreamsbowling.databinding.ActivityViewBookingBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewBooking extends AppCompatActivity {

    ///problemmmmmmmm
    ActivityViewBookingBinding bind;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://workshop2-d8198-default-rtdb.firebaseio.com/");
    FirebaseAuth ID;
    String UID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = bind.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        ID = FirebaseAuth.getInstance();

        UID = ID.getCurrentUser().getUid();
        databaseReference.child("Booking").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(UID))
                {
                    final String Name = snapshot.child(UID).child("FullName").getValue().toString();
                    final String Email = snapshot.child(UID).child("Email").getValue().toString();
                    final String Date = snapshot.child(UID).child("Date").getValue().toString();
                    final String Time = snapshot.child(UID).child("Time").getValue().toString();
                    final String Game = snapshot.child(UID).child("NumberGame").getValue().toString();
                    final String Player = snapshot.child(UID).child("NumberPlayer").getValue().toString();
                    final String Price = snapshot.child(UID).child("TotalPrice").getValue().toString();
                    final String PayID = snapshot.child(UID).child("PaymentID").getValue().toString();

                    bind.Date.setText("Date : " + Date);
                    bind.Name.setText("Name : " + Name);
                    bind.Email.setText("Email : " + Email);
                    bind.Player.setText("Number of Player : "+ Player);
                    bind.Game.setText("Number of Games : "+ Game);
                    bind.Time.setText("Time : "+ Time);
                    bind.payID.setText("Payment ID : " + PayID);
                    bind.Price.setText("Total Price : RM"+ Price);

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Please Book First! ", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), BookingFragment.class);
                    startActivity(intent);
                    finish();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}