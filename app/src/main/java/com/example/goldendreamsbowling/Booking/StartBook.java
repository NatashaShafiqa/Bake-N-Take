package com.example.goldendreamsbowling.Booking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;
import com.example.goldendreamsbowling.R;
import com.example.goldendreamsbowling.SubsMember;
import com.example.goldendreamsbowling.databinding.ActivityStartBookBinding;
import com.example.goldendreamsbowling.detailPay;

import java.text.DecimalFormat;
import java.util.Calendar;

public class StartBook extends AppCompatActivity {

    ActivityStartBookBinding binding;
    DatePickerDialog datePicker;
    int NumGame,NumPerson;
    Button button;
    TextView price;
    int k,n;
    double TotalPrice,tax=0.18,discount;
    Double PricePerGame = 9.90, PriceShoes,PriceRM;
    String Times,gk,Shoes;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        price = (TextView) findViewById(R.id.Price);

        binding.Date.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean b)
            {
                if(b) {
                    fnInvokeDatePicker();
                }
                if(!b)
                {
                    fnFormValidaton();
                }

            }

        });
        binding.Date.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                fnInvokeDatePicker();
            }
        });

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price.setText("");
                binding.spnTime.setVisibility(View.INVISIBLE);
                binding.spnTime2.setVisibility(View.INVISIBLE);
                binding.spnTime3.setVisibility(View.INVISIBLE);
                NumPerson = binding.spnNumPlayer.getSelectedItemPosition();
                String[] size =getResources().getStringArray(R.array.numOfPeople_arrays);
                k = Integer.valueOf(size[NumPerson]);
                Shoes = binding.spnShoes.getSelectedItem().toString();
                //problem sini dia tak compare value..tak tahu kenapa
                if(Shoes != "No")
                {
                    PriceShoes = 4.30;
                }
                else
                {
                    PriceShoes = 0.00;
                }

                NumGame = binding.spnNumGame.getSelectedItemPosition();
                String[] size_values =getResources().getStringArray(R.array.numGame);
                n = Integer.valueOf(size_values[NumGame]);

                PriceRM = (PricePerGame*n*k)+PriceShoes;
                gk= df.format(PriceRM);
                price.setText(gk);

                if(n==1){
                    binding.spnTime.setVisibility(View.VISIBLE);
                    binding.checkout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Times = binding.spnTime.getSelectedItem().toString().trim();
                            SharedPreferences sharedPreferences = getSharedPreferences(SubsMember.Subb,0);
                            boolean hasSubbed = sharedPreferences.getBoolean("hasSubbed",false);
                            if(hasSubbed)
                            {
                                discount = PriceRM*0.15;
                            }
                            else
                            {
                                discount=0.0;
                            }
                            tax = PriceRM*tax;
                            String t = df.format(tax);
                            TotalPrice = (PriceRM-discount)+tax;
                            String totalPrice = df.format(TotalPrice);
                            String TotPrice = (totalPrice);
                            String Taxx = (t);
                            String Dis = String.valueOf(discount);
                            String date = binding.Date.getText().toString().trim();
                            String Numplayer = String.valueOf(k);
                            String Numgames = String.valueOf(n);
                            String pickShoes = binding.spnShoes.getSelectedItem().toString().trim();
                            String time = Times;
                            String pricecheck = gk;

                            Intent intent = new Intent(StartBook.this, CheckOutBook.class);
                            intent.putExtra("DATE_TEXT", date);
                            intent.putExtra("PLAYER_TEXT",Numplayer);
                            intent.putExtra("GAME_TEXT",Numgames);
                            intent.putExtra("SHOES_TEXT",pickShoes);
                            intent.putExtra("TIME_TEXT",time);
                            intent.putExtra("PRICE_TEXT",pricecheck);
                            intent.putExtra("Total_Price",TotPrice);
                            intent.putExtra("tax",Taxx);
                            intent.putExtra("Diss",Dis);
                            startActivity(intent);
                            finish();
                        }
                    });


                }
                else if(n==2)
                {
                    binding.spnTime2.setVisibility(View.VISIBLE);
                    binding.checkout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Times = binding.spnTime2.getSelectedItem().toString().trim();
                            SharedPreferences sharedPreferences = getSharedPreferences(SubsMember.Subb,0);
                            boolean hasSubbed = sharedPreferences.getBoolean("hasSubbed",false);
                            if(hasSubbed)
                            {
                                discount = PriceRM*0.15;
                            }
                            else
                            {
                                discount=0.0;
                            }
                            tax = PriceRM*tax;
                            String t = df.format(tax);
                            TotalPrice = (PriceRM-discount)+tax;
                            String totalPrice = df.format(TotalPrice);
                            String TotPrice = (totalPrice);
                            String Taxx = (t);
                            String Dis = String.valueOf(discount);
                            String date = binding.Date.getText().toString().trim();
                            String Numplayer = String.valueOf(k);
                            String Numgames = String.valueOf(n);
                            String pickShoes = binding.spnShoes.getSelectedItem().toString().trim();
                            String time = Times;
                            String pricecheck = gk;

                            Intent intent = new Intent(StartBook.this, CheckOutBook.class);
                            intent.putExtra("DATE_TEXT", date);
                            intent.putExtra("PLAYER_TEXT",Numplayer);
                            intent.putExtra("GAME_TEXT",Numgames);
                            intent.putExtra("SHOES_TEXT",pickShoes);
                            intent.putExtra("TIME_TEXT",time);
                            intent.putExtra("PRICE_TEXT",pricecheck);
                            intent.putExtra("Total_Price",TotPrice);
                            intent.putExtra("tax",Taxx);
                            intent.putExtra("Diss",Dis);
                            startActivity(intent);
                            finish();
                        }
                    });


                }
                else if(n==3)
                {
                    binding.spnTime3.setVisibility(View.VISIBLE);
                    binding.checkout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Times = binding.spnTime3.getSelectedItem().toString().trim();
                            SharedPreferences sharedPreferences = getSharedPreferences(SubsMember.Subb,0);
                            boolean hasSubbed = sharedPreferences.getBoolean("hasSubbed",false);
                            if(hasSubbed)
                            {
                                discount = PriceRM*0.15;
                            }
                            else
                            {
                                discount=0.0;
                            }
                            tax = PriceRM*tax;
                            String t = df.format(tax);
                            TotalPrice = (PriceRM-discount)+tax;
                            String totalPrice = df.format(TotalPrice);
                            String TotPrice = (totalPrice);
                            String Taxx = (t);
                            String Dis = String.valueOf(discount);
                            String date = binding.Date.getText().toString().trim();
                            String Numplayer = String.valueOf(k);
                            String Numgames = String.valueOf(n);
                            String pickShoes = binding.spnShoes.getSelectedItem().toString().trim();
                            String time = Times;
                            String pricecheck = gk;

                            Intent intent = new Intent(StartBook.this, CheckOutBook.class);
                            intent.putExtra("DATE_TEXT", date);
                            intent.putExtra("PLAYER_TEXT",Numplayer);
                            intent.putExtra("GAME_TEXT",Numgames);
                            intent.putExtra("SHOES_TEXT",pickShoes);
                            intent.putExtra("TIME_TEXT",time);
                            intent.putExtra("PRICE_TEXT",pricecheck);
                            intent.putExtra("Total_Price",TotPrice);
                            intent.putExtra("tax",Taxx);
                            intent.putExtra("Diss",Dis);
                            startActivity(intent);
                            finish();
                        }
                    });


                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Please Enter Valid Game", Toast.LENGTH_SHORT).show();
                }



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

        datePicker = new DatePickerDialog(StartBook.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                binding.Date.setText(dayOfMonth + "-" + (month+1) + "-" + year);
            }
        },year,month,day);
        datePicker.show();
    }
}