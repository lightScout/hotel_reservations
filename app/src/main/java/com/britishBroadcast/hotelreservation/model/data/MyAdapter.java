package com.britishBroadcast.hotelreservation.model.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.britishBroadcast.hotelreservation.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<Reservation> allReservationsList;
    Context context;

    public MyAdapter(Context ct, ArrayList<Reservation> list) {
context = ct;
allReservationsList = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
               View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Reservation reservation = allReservationsList.get(position);

        holder.reservationID.setText(reservation.getId()+ "");
        holder.reservationInfo.setText(reservation.toString());

    }

    @Override
    public int getItemCount() {
        return allReservationsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView reservationID, reservationInfo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            reservationID = itemView.findViewById(R.id.reservation_id_textview);
            reservationInfo = itemView.findViewById(R.id.reservation_info_textview);

        }
    }
}
