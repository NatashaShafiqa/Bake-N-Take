package com.example.BakeryApps;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CheckOutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheckOutFragment extends Fragment {

    String  purl,name,description,price,candle,address;


    public CheckOutFragment(String purl , String name, String description, String price) {
        this.purl=purl;
        this.name = name;
        this.description=description;
        this.price = price;

    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CheckOutFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CheckOutFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CheckOutFragment newInstance(String param1, String param2) {
        CheckOutFragment fragment = new CheckOutFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    String payMethod="",PayID;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    FirebaseAuth ID;
    String UID;
    private String url,Name,Price,Quant,Message,Date;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_check_out, container, false);
        ID = FirebaseAuth.getInstance();

       ImageView imageCake = view.findViewById(R.id.cakeimage);
       TextView cakename = view.findViewById(R.id.cakename);
       TextView price = view.findViewById(R.id.cakePrice);
       TextView quantity = view.findViewById(R.id.quantity);
       TextView edtmessage = view.findViewById(R.id.messages);
       TextView datee = view.findViewById(R.id.Datee);
       TextView Address = view.findViewById(R.id.AddressCheckout);
       CheckBox c1,c2,c3;
        c1 = view.findViewById(R.id.Debit);
        c2 = view.findViewById(R.id.Visa);
        c3 = view.findViewById(R.id.onlineBank);

        Bundle data = getArguments();
        if(data!=null){
            url = data.getString("url");
            Name = data.getString("Name");
            Price = data.getString("Price");
            Date = data.getString("Date");
            Quant = data.getString("Cake");
            Message = data.getString("Message");
            candle = data.getString("Candle");
            address = data.getString("Address");
        }
        else {
            Toast.makeText(getContext(),"error",Toast.LENGTH_LONG).show();
        }

        Glide.with(getContext()).load(url).into(imageCake);
        cakename.setText(Name);
        price.setText("Price : " + Price);
        quantity.setText("Quantity : "+Quant);
        edtmessage.setText(Message);
        datee.setText("Date : "+Date);
        Address.setText(address);


        Random random = new Random();
        int value = random.nextInt(10000);
        PayID = Integer.toString(value);
        String pp = "GDB005"+PayID;

        Button pay = view.findViewById(R.id.Pay);
        pay.setOnClickListener(new View.OnClickListener() {
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

                        databaseReference.child("Payment").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                databaseReference.child("Payment").child(UID).child("Date").setValue(Date);
                                databaseReference.child("Payment").child(UID).child("CakeName").setValue(Name);
                                databaseReference.child("Payment").child(UID).child("Price").setValue(Price);
                                databaseReference.child("Payment").child(UID).child("Quantity").setValue(Quant);
                                databaseReference.child("Payment").child(UID).child("PaymentID").setValue(pp);
                                databaseReference.child("Payment").child(UID).child("Candle").setValue(candle);
                                databaseReference.child("Payment").child(UID).child("MethodPay").setValue(methodpay);
                                Toast.makeText(getContext(), "successful", Toast.LENGTH_LONG).show();
                                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                                activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper,new recfragment());

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

                Intent intent = new Intent(getContext(),HomePage.class);
                startActivity(intent);

            }
        });
       return view;
    }
}