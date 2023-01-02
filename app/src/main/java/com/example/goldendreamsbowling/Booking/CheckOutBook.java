package com.example.goldendreamsbowling.Booking;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.goldendreamsbowling.HomePage;
import com.example.goldendreamsbowling.LoggedInUser.promoFragment;
import com.example.goldendreamsbowling.Login;
import com.example.goldendreamsbowling.R;
import com.example.goldendreamsbowling.SubsMember;
import com.example.goldendreamsbowling.databinding.ActivityCheckOutBookBinding;
import com.example.goldendreamsbowling.detailPay;
import com.example.goldendreamsbowling.signUp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class CheckOutBook extends AppCompatActivity {


    ActivityCheckOutBookBinding binding;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://workshop2-d8198-default-rtdb.firebaseio.com/");
    FirebaseAuth ID;
    String UID;
    CheckBox c1,c2,c3;
    String payMethod="",PayID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ID = FirebaseAuth.getInstance();
        Intent intent = getIntent();
        String date = intent.getStringExtra("DATE_TEXT");
        String noPlayer = intent.getStringExtra("PLAYER_TEXT");
        String noGames = intent.getStringExtra("GAME_TEXT");
        String pickShoes = intent.getStringExtra("SHOES_TEXT");
        String time = intent.getStringExtra("TIME_TEXT");
        String priceCheck = intent.getStringExtra("PRICE_TEXT");
        String Discount = intent.getStringExtra("Diss");
        String Taxx = intent.getStringExtra("tax");
        String totalPrice = intent.getStringExtra("Total_Price");
        binding.DatePay.setText("Date : " + date);
        binding.NoPlayer.setText("Number of Player : "+ noPlayer);
        binding.noGame.setText("Number of Games : "+ noGames);
        binding.ShoesPay.setText("Number of Shoes : "+ pickShoes);
        binding.TimePay.setText("Time : "+ time);
        binding.subtotal.setText("Subtotal : RM"+ priceCheck);
        binding.discount.setText("Discount : RM"+ Discount);
        binding.tax.setText("Tax : RM"+ Taxx);
        binding.totall.setText("Total Price : RM"+ totalPrice);
        Intent i = new Intent(CheckOutBook.this, ViewBooking.class);
        i.putExtra("DATE_TEXT", date);

        c1 = findViewById(R.id.Debit);
        c2 = findViewById(R.id.Visa);
        c3 = findViewById(R.id.onlineBank);

        Random random = new Random();
        int value = random.nextInt(10000);
        PayID = Integer.toString(value);
        String pp = "GDB005"+PayID;




        binding.payy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UID = ID.getCurrentUser().getUid();

                if(c1.isChecked())
                {
                    payMethod += c1.getText().toString().trim();
                }
                if(c2.isChecked())
                {
                    payMethod += c2.getText().toString().trim();
                }
                if(c3.isChecked())
                {
                    payMethod += c3.getText().toString().trim();
                }
                String methodpay = payMethod;

                databaseReference.child("users").child(UID).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        final String dataName=snapshot.child("fullname").getValue().toString();
                        final String dataEmail=snapshot.child("email").getValue().toString();

                        databaseReference.child("Booking").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                databaseReference.child("Booking").child(UID).child("Date").setValue(date);
                                databaseReference.child("Booking").child(UID).child("Time").setValue(time);
                                databaseReference.child("Booking").child(UID).child("FullName").setValue(dataName);
                                databaseReference.child("Booking").child(UID).child("Email").setValue(dataEmail);
                                databaseReference.child("Booking").child(UID).child("NumberPlayer").setValue(noPlayer);
                                databaseReference.child("Booking").child(UID).child("NumberGame").setValue(noGames);
                                databaseReference.child("Booking").child(UID).child("NumberShoes").setValue(pickShoes);
                                databaseReference.child("Booking").child(UID).child("TotalPrice").setValue(totalPrice);
                                databaseReference.child("Booking").child(UID).child("PaymentID").setValue(pp);
                                Toast.makeText(CheckOutBook.this, "successful", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), HomePage.class);
                                startActivity(intent);
                                finish();

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        databaseReference.child("Payment").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                databaseReference.child("Payment").child(UID).child("Date").setValue(date);
                                databaseReference.child("Payment").child(UID).child("Time").setValue(time);
                                databaseReference.child("Payment").child(UID).child("PaymentID").setValue(pp);
                                databaseReference.child("Payment").child(UID).child("Email").setValue(dataEmail);
                                databaseReference.child("Payment").child(UID).child("NumberPlayer").setValue(noPlayer);
                                databaseReference.child("Payment").child(UID).child("NumberGame").setValue(noGames);
                                databaseReference.child("Payment").child(UID).child("NumberShoes").setValue(pickShoes);
                                databaseReference.child("Payment").child(UID).child("TotalPrice").setValue(totalPrice);
                                databaseReference.child("Payment").child(UID).child("PaymentMethod").setValue(methodpay);
                                Toast.makeText(CheckOutBook.this, "successful", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), HomePage.class);
                                startActivity(intent);
                                finish();

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

    }
}