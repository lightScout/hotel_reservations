package com.britishBroadcast.hotelreservation.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.britishBroadcast.hotelreservation.AddReservation;
import com.britishBroadcast.hotelreservation.R;
import com.britishBroadcast.hotelreservation.model.DBHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private DBHelper dbHelper;

    @BindView(R.id.total_due_textview)
    public TextView total_due_textview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        dbHelper = new DBHelper(this);
//       deleteDatabase(DATABASE_NAME);

        loadReservations();

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadReservations();
    }

    private void loadReservations() {
        final ListView listview = (ListView) findViewById(R.id.reservation_listview);

        final ArrayList<String> allReservationsList = dbHelper.getAllReservations();

        // Create an ArrayAdapter from List
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, allReservationsList){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                // Get the Item from ListView
                View view = super.getView(position, convertView, parent);

                // Initialize a TextView for ListView each Item
                TextView tv = (TextView) view.findViewById(android.R.id.text1);



                // Set the text color of TextView (ListView Item)
                tv.setTextColor(Color.WHITE);
                // Set the text size
                tv.setTextSize(18);
                // Set view padding
                tv.setPadding(0,30,0,30);

//                Typeface font = Typeface.createFromFile("/res/font/poiret_font.ttf");
//                tv.setTypeface(font);




                // Generate ListView Item using TextView
                return view;
            }
        };

        // DataBind ListView with items from ArrayAdapter
        listview.setAdapter(arrayAdapter);

        total_due_textview.setText(getString(R.string.total_due_textview_text, dbHelper.getTotalAmountDue().toString()));

    }


    @OnClick(R.id.add_button)
    public void onClick(View view){

        Intent intent = new Intent(MainActivity.this, AddReservation.class);
        // Triggering the intent
        startActivity(intent);
        loadReservations();
    }
}

