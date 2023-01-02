package com.example.goldendreamsbowling.Booking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.goldendreamsbowling.HomePage;
import com.example.goldendreamsbowling.LoggedInUser.BookingFragment;
import com.example.goldendreamsbowling.databinding.ActivityCancelBinding;
import com.example.goldendreamsbowling.signUp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Cancel extends AppCompatActivity {

    ActivityCancelBinding bind;
    FirebaseAuth ID;
    String UID;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://workshop2-d8198-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind =bind.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        ID = FirebaseAuth.getInstance();

        bind.yess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UID = ID.getCurrentUser().getUid();
                bind.cancelEmail.setVisibility(View.VISIBLE);
                bind.cancelID.setVisibility(View.VISIBLE);
                bind.submit.setVisibility(View.VISIBLE);
                databaseReference.child("Payment").child(UID).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        final String dataPayID=snapshot.child("PaymentID").getValue().toString().trim();
                        final String dataEmail=snapshot.child("Email").getValue().toString().trim();
                        bind.submit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final String email = bind.cancelEmail.getText().toString();
                                final String PayID = bind.cancelID.getText().toString();
                                if(dataEmail.equals(email) && dataPayID.equals(PayID) )
                                {
                                    for (DataSnapshot appleSnapshot: snapshot.getChildren()) {
                                        appleSnapshot.getRef().removeValue();
                                    }
                                    databaseReference.child("Booking").child(UID).addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            for (DataSnapshot appleSnapshot: snapshot.getChildren()) {
                                                appleSnapshot.getRef().removeValue();
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                                    Toast.makeText(Cancel.this, "Your refund will  be sent by 14 days work", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(getApplicationContext(), HomePage.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else
                                {
                                    Toast.makeText(Cancel.this, "Enter Correct ID or Email", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        bind.nope.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BookingFragment.class);
                startActivity(intent);
                finish();
            }
        });
    }
}