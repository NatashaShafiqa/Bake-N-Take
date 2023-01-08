package com.example.BakeryApps;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Calendar;

public class descfragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    String  purl,name,description,price;
    public descfragment() {

    }

    public descfragment(String purl ,String name, String description, String price) {
        this.purl=purl;
        this.name = name;
        this.description=description;
        this.price = price;

    }


    public static descfragment newInstance(String param1, String param2) {
        descfragment fragment = new descfragment();
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

    int countCake = 0;
    int countCandle=0;
    TextView valueCake,valueCandle,btChange,txtmore,txtless;
    EditText chooseCake;
    Button btnIncCake,btnIncCandle,btnDecCake,btnDecCandle;
    LinearLayout hide;

    private DatePickerDialog datePicker;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_descfragment, container, false);

        ImageView imageholder=view.findViewById(R.id.imagegholder);
        TextView nameholder=view.findViewById(R.id.nameholder);
        TextView desholder=view.findViewById(R.id.courseholder);
        TextView priceholder=view.findViewById(R.id.emailholder);
        Button btnCheckOut = view.findViewById(R.id.CheckOutCake);
        EditText edtMessage = view.findViewById(R.id.messages);
        EditText edtAddress = view.findViewById(R.id.address);
        hide = view.findViewById(R.id.Hidee);
        btChange = view.findViewById(R.id.txtChange);
        txtless = view.findViewById(R.id.txtexpandless);
        txtmore = view.findViewById(R.id.txtexpandmore);

        nameholder.setText(name);
        btChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Isvisible = hide.getVisibility();
                if(Isvisible==View.VISIBLE){
                    hide.setVisibility(View.GONE);
                    txtless.setVisibility(View.GONE);
                    txtmore.setVisibility(View.VISIBLE);
                }
                else{
                    hide.setVisibility(View.VISIBLE);
                    txtless.setVisibility(View.VISIBLE);
                    txtmore.setVisibility(View.GONE);
                    desholder.setText(description);
                }
            }
        });

        priceholder.setText(price);

        Glide.with(getContext()).load(purl).into(imageholder);
        chooseCake = view.findViewById(R.id.ChooseDate);
        chooseCake.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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

        valueCake = view.findViewById(R.id.valueCake);
        valueCandle = view.findViewById(R.id.valueCandle);

        btnIncCake = view.findViewById(R.id.btnIncQuant);
        btnIncCake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countCake++;
                valueCake.setText(""+countCake);

            }
        });
        btnDecCake = view.findViewById(R.id.btnDecQuant);
        btnDecCake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(countCake <=0){
                    countCake=0;
                }
                else
                {
                    countCake--;
                    valueCake.setText(""+countCake);
                }
            }
        });
        btnIncCandle = view.findViewById(R.id.btnIncCand);
        btnIncCandle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countCandle++;
                valueCandle.setText(""+countCandle);

            }
        });
        btnDecCandle = view.findViewById(R.id.btnDecCand);
        btnDecCandle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(countCandle <=0){
                    countCandle=0;
                }
                else
                {
                    countCandle--;
                    valueCandle.setText(""+countCandle);
                }

            }
        });


        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = nameholder.getText().toString();
                String price = priceholder.getText().toString();
                String date = chooseCake.getText().toString().trim();
                String Cake = valueCake.getText().toString().trim();
                String Messages = edtMessage.getText().toString().trim();
                String Address = edtAddress.getText().toString().trim();
                String candle = valueCandle.getText().toString().trim();
                int quantity = Integer.parseInt(Cake);
                double Price = Double.parseDouble(price);
                double Total = quantity * Price;
                String TotalPrice = String.valueOf(Total);


                Bundle data = new Bundle();
                data.putString("url",purl);
                data.putString("Name",name);
                data.putString("Price",TotalPrice);
                data.putString("Date",date);
                data.putString("Cake",Cake);
                data.putString("Message",Messages);
                data.putString("Address",Address);
                data.putString("Candle",candle);

                Fragment frag3 = new CheckOutFragment();
                frag3.setArguments(data);
                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                fm.replace(R.id.wrapper,frag3).commit();
            }
        });

        return  view;
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

        datePicker = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                chooseCake.setText(dayOfMonth + "/" + (month+1) + "/" + year);
            }
        },year,month,day);
        datePicker.show();
    }



    public void onBackPressed()
    {
        AppCompatActivity activity=(AppCompatActivity)getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper,new recfragment()).addToBackStack(null).commit();

    }
}