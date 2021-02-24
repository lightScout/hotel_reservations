package com.britishBroadcast.hotelreservation.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.britishBroadcast.hotelreservation.R;
import com.britishBroadcast.hotelreservation.model.DBHelper;
import com.britishBroadcast.hotelreservation.view.adapter.ReservationBaseAdapter;
import com.britishBroadcast.hotelreservation.model.data.Reservation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.britishBroadcast.hotelreservation.model.DBHelper.DATABASE_NAME;

public class MainActivity extends AppCompatActivity {

    private DBHelper dbHelper;


    // List with hotel image to be used by reservation base adapter
//    private List<Reservation> reservationWithImageList = new ArrayList<>(Arrays.asList(
//            new Reservation(0, "Alex Marcus", "01/02/2022", "01/03/2022", 20.0,"https://www.gannett-cdn.com/presto/2020/05/18/USAT/9ae54ef7-217d-4877-a614-4760325f9a9b-Park-Hyatt-New-York-Manhattan-Sky-Suite-Master-Bedroom.jpg?crop=1199,675,x0,y59&width=660&height=372&format=pjpg&auto=webp")
//    ));

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

        // Recycler View binding
//        final RecyclerView recyclerView =  findViewById(R.id.reservation_recycleview);
//        final ArrayList<Reservation> allReservationsList = dbHelper.getAllReservations();
//        ReservationRecyclerViewAdapter reservationRecyclerViewAdapter = new ReservationRecyclerViewAdapter(this, allReservationsList);
//        recyclerView.setAdapter(reservationRecyclerViewAdapter);
//        recyclerView.setLayoutManager( new LinearLayoutManager(this));


        // List View binding
        final ListView mainListView = findViewById(R.id.reservation_listview);
        final List<Reservation> allReservationsList = dbHelper.getAllReservations();
        ReservationBaseAdapter reservationBaseAdapter = new ReservationBaseAdapter(allReservationsList, this);
        mainListView.setAdapter(reservationBaseAdapter);


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


