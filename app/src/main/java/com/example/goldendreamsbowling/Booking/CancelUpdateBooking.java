package com.example.goldendreamsbowling.Booking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.goldendreamsbowling.LoggedInUser.BookingFragment;
import com.example.goldendreamsbowling.databinding.ActivityCancelUpdateBookingBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class CancelUpdateBooking extends AppCompatActivity {

    ActivityCancelUpdateBookingBinding bind;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://workshop2-d8198-default-rtdb.firebaseio.com/");
    FirebaseAuth ID;
    String UID;
    DatePickerDialog datePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind =bind.inflate(getLayoutInflater());
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

                    bind.Date.setText("Date : " + Date);
                    bind.Name.setText("Name : " + Name);
                    bind.Email.setText("Email : " + Email);
                    bind.Player.setText("Number of Player : "+ Player);
                    bind.Game.setText("Number of Games : "+ Game);
                    bind.Time.setText("Time : "+ Time);
                    bind.Price.setText("Total Price : RM"+ Price);
                    bind.edtDate.setText(Date);
                    bind.edtDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View v, boolean b) {
                            if(b) {
                                fnInvokeDatePicker();
                            }
                            if(!b)
                            {
                                fnFormValidaton();
                            }
                        }
                    });
                    bind.edtDate.setOnClickListener(new View.OnClickListener(){

                        @Override
                        public void onClick(View v) {
                            fnInvokeDatePicker();
                        }
                    });
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
        bind.UpdateBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dates = bind.edtDate.getText().toString().trim();
                databaseReference.child("Booking").child(UID).child("Date").setValue(dates);
                Toast.makeText(getApplicationContext(), "Update Success! ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), BookingFragment.class);
                startActivity(intent);
                finish();
            }
        });

        bind.cancelBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Cancel.class);
                startActivity(intent);
                finish();
            }
        });


    }

    private void fnFormValidaton() {
    }

    private void fnInvokeDatePicker()
    {
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        // date picker dialog

        datePicker = new DatePickerDialog(CancelUpdateBooking.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                bind.edtDate.setText(dayOfMonth + "-" + (month+1) + "-" + year);
            }
        },year,month,day);
        datePicker.show();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, BookingFragment.class));
        overridePendingTransition(0,0);
        finish();
    }
}