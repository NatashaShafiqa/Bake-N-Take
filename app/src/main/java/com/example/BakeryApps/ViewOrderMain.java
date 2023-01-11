package com.example.BakeryApps;

import androidx.annotation.NonNull;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.BakeryApps.databinding.ActivityVieworderMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewOrderMain extends Drawer_base {

    ActivityVieworderMainBinding binding;
    FirebaseAuth ID;
    String UID;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://bakery-application-b53de-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ID = FirebaseAuth.getInstance();
        UID = ID.getCurrentUser().getUid();
        ProgressDialog progressDialog = new ProgressDialog(ViewOrderMain.this);
        progressDialog.setTitle("Uploading Recourse ");
        progressDialog.setMessage("Please wait for a while!");
        progressDialog.show();

        databaseReference.child("Payment").child(UID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.child("CakeImg").getValue(String.class) != null)
                {
                    databaseReference.child("Payment").child(UID).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            final String dataUrl = snapshot.child("CakeImg").getValue(String.class);
                            final String dataCakeName = snapshot.child("CakeName").getValue(String.class);
                            final String dataCandle = snapshot.child("Candle").getValue(String.class);
                            final String dataDate = snapshot.child("Date").getValue(String.class);
                            final String dataMethodPay = snapshot.child("MethodPay").getValue(String.class);
                            final String dataPaymentID = snapshot.child("PaymentID").getValue(String.class);
                            final String dataMessage = snapshot.child("Message").getValue(String.class);
                            final String dataPrice = snapshot.child("Price").getValue(String.class);
                            final String dataQuantity = snapshot.child("Quantity").getValue(String.class);
                            final String dataAddress = snapshot.child("Address").getValue(String.class);
                            binding.cakename.setText(dataCakeName);
                            binding.cakePrice.setText("Price : RM"+dataPrice);
                            binding.quantity.setText("Quantity : "+dataQuantity);
                            binding.Datee.setText("Date : " + dataDate);
                            binding.Address.setText(dataAddress);
                            binding.messages.setText(dataMessage);
                            binding.paymentID.setText(dataPaymentID);
                            binding.PaymentMethod.setText(dataMethodPay);
                            binding.candle.setText("Candle : " + dataCandle);
                            Glide.with(getApplicationContext()).load(dataUrl).into(binding.cakeimage);
                            Toast.makeText(getApplicationContext(),"Upload Success",Toast.LENGTH_LONG).show();

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
                else
                {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            Toast.makeText(getApplicationContext(),"No record detected",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(ViewOrderMain.this, HomePage.class));
                            finish();
                        }
                    }, 1300);

                }
                progressDialog.dismiss();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}