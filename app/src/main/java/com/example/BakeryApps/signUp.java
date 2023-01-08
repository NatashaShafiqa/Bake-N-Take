package com.example.BakeryApps;


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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class signUp extends AppCompatActivity {

    private FirebaseAuth mAuth;
    String UserID;
    TextView textViewSignIn,Fullname,mail,Pass,phone,User;
    Button SignupButton;
    ProgressBar bar;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://bakery-application-b53de-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        textViewSignIn = findViewById(R.id.SignInTextt);
        Fullname = findViewById(R.id.NameSUp);
        mail = findViewById(R.id.emailSUp);
        Pass = findViewById(R.id.passwordSUp);
        User = findViewById(R.id.Username);
        phone = findViewById(R.id.phoneSUp);
        SignupButton = findViewById(R.id.Reg);
        bar = (ProgressBar) findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();

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
        final String Username = User.getText().toString();

        if(Name.isEmpty()||Phone.isEmpty()||Email.isEmpty()||pass.isEmpty()||Username.isEmpty())
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
            mAuth.createUserWithEmailAndPassword(Email,pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_LONG).show();
                                bar.setVisibility(View.GONE);
                                UserID = mAuth.getCurrentUser().getUid();
                                databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        bar.setVisibility(View.INVISIBLE);
                                        if (snapshot.hasChild(UserID)) {
                                            Toast.makeText(signUp.this, "User already registered", Toast.LENGTH_LONG).show();
                                        } else {
                                            databaseReference.child("users").child(UserID).child("username").setValue(Username);
                                            databaseReference.child("users").child(UserID).child("fullname").setValue(Name);
                                            databaseReference.child("users").child(UserID).child("email").setValue(Email);
                                            databaseReference.child("users").child(UserID).child("phone").setValue(Phone);
                                            databaseReference.child("users").child(UserID).child("password").setValue(pass);
                                            Toast.makeText(signUp.this, "Registration successful", Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(getApplicationContext(),Login.class);
                                            startActivity(intent);
                                            finish();

                                        }
                                    }


                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Registration failed! Please try again later", Toast.LENGTH_LONG).show();
                                bar.setVisibility(View.GONE);
                            }
                        }
                    });

        }

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, Login.class));
        overridePendingTransition(0,0);
        finish();
    }
}