package com.britishBroadcast.hotelreservation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.britishBroadcast.hotelreservation.model.DBHelper;
import com.britishBroadcast.hotelreservation.model.data.Reservation;
import com.britishBroadcast.hotelreservation.view.MainActivity;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddReservation extends AppCompatActivity {
    DatePickerDialog picker;

    DBHelper dbHelper;

    @BindView(R.id.reservation_checking_edittext)
    public EditText checking_edittext;


    @BindView(R.id.reservation_checkout_edittext)
    public EditText checkout_edittext;

    @BindView(R.id.reservation_name_edittext)
    public EditText name_edittext;

    @BindView(R.id.reservation_price_edittext)
    public EditText price_edittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reservation);
        ButterKnife.bind(this);

        dbHelper = new DBHelper(this);


        checking_edittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(AddReservation.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                checking_edittext.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        checkout_edittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(AddReservation.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                checkout_edittext.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });


    }




    @OnClick(R.id.add_button)
    public void onCLick(View view){
        String reservationName = name_edittext.getText().toString().trim();
        String reservationChecking = checking_edittext.getText().toString().trim();
        String reservationCheckout = checkout_edittext.getText().toString().trim();
        Double reservationPrice;


        if(reservationName.isEmpty() || reservationChecking.isEmpty() || reservationCheckout.isEmpty()) {
            Toast.makeText(this, "One of more input is missing", Toast.LENGTH_SHORT).show();
        }else{
            if(!price_edittext.getText().toString().isEmpty()){
                reservationPrice = Double.parseDouble(price_edittext.getText().toString().trim());
                final Reservation reservation = new Reservation(reservationName,reservationChecking,reservationCheckout,reservationPrice);

                dbHelper.insertNewReservation(reservation);
                finish();
            }else{
                Toast.makeText(this, "Reservation price missing", Toast.LENGTH_SHORT).show();
            }

        }




    }


    }
