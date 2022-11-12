package com.example.goldendreamsbowling;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class signUp extends AppCompatActivity {
    TextView textViewSignIn,Fullname,mail,Pass,phone,username;
    Button SignupButton;
    ProgressBar bar;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://workshop2-d8198-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        textViewSignIn = findViewById(R.id.SignInText);
        Fullname = findViewById(R.id.NameSUp);
        mail = findViewById(R.id.emailSUp);
        Pass = findViewById(R.id.passwordSUp);
        phone = findViewById(R.id.phoneSUp);
        SignupButton = findViewById(R.id.Reg);
        username = findViewById(R.id.Userrrr);
        bar = (ProgressBar) findViewById(R.id.progressBar);

        textViewSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View w) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();
            }
        });

        SignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();

            }
        });
    }

    public void Register(){
        final String Name = Fullname.getText().toString();
        final String Email = mail.getText().toString();
        final String Phone = phone.getText().toString();
        final String pass = Pass.getText().toString();
        final String User =username.getText().toString();

        if(Name.isEmpty()||Phone.isEmpty()||Email.isEmpty()||pass.isEmpty()||User.isEmpty())
        {
            Toast.makeText(signUp.this,"Please fill all fields",Toast.LENGTH_LONG).show();
        }
        else if(!Patterns.PHONE.matcher(Phone).matches())
        {
            phone.setError("invalid phone number");
            phone.requestFocus();
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches())
        {
            mail.setError("Please provide correct Email");
            mail.requestFocus();
            return;
        }
        else if(pass.length()<6)
        {
            Pass.setError("Password must be more than 6 character");
            Pass.requestFocus();
            return;
        }
        else
        {
            bar.setVisibility(View.VISIBLE);
            databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    bar.setVisibility(View.INVISIBLE);
                    if (snapshot.hasChild(User)) {
                        Toast.makeText(signUp.this, "User already registered", Toast.LENGTH_LONG).show();
                    } else {

                        databaseReference.child("users").child(User).child("fullname").setValue(Name);
                        databaseReference.child("users").child(User).child("email").setValue(Email);
                        databaseReference.child("users").child(User).child("phone").setValue(Phone);
                        databaseReference.child("users").child(User).child("username").setValue(User);
                        databaseReference.child("users").child(User).child("password").setValue(pass);
                        Toast.makeText(signUp.this, "Registration successful", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),MainInterface.class);
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
}