package com.example.goldendreamsbowling;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Login extends AppCompatActivity {
    TextView textViewSignUp ,name,password;
    Button Login;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://workshop2-d8198-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Login = findViewById(R.id.login);
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        textViewSignUp = findViewById(R.id.SignInTextt);

        name = findViewById(R.id.name);
        password = findViewById(R.id.password);

        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),signUp.class);
                startActivity(intent);
                finish();
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String User = name.getText().toString();
                final String pass = password.getText().toString();

                if(User.isEmpty()||pass.isEmpty())
                {
                    Toast.makeText(Login.this,"Please enter Username and Password",Toast.LENGTH_LONG).show();
                }
                else
                {
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(User)) {
                                final String getPassword = snapshot.child(User).child("password").getValue(String.class);
                                if (getPassword.equals(pass)) {

                                    Toast.makeText(Login.this, "Successfully login", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(getApplicationContext(), MainInterface.class);
                                    startActivity(intent);
                                    finish();

                                } else {

                                    Toast.makeText(Login.this, "Wrong Password", Toast.LENGTH_LONG).show();
                                }
                            } else {

                                Toast.makeText(Login.this, "h", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

    }

}