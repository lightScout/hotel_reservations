package com.britishBroadcast.hotelreservation.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.britishBroadcast.hotelreservation.R;
import com.britishBroadcast.hotelreservation.model.data.Reservation;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;


public class ReservationBaseAdapter extends BaseAdapter {

    List<Reservation> allReservationsList;
    Context context;

    public ReservationBaseAdapter(List<Reservation> allReservationsList, Context context) {
        this.allReservationsList = allReservationsList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return allReservationsList.size();
    }

    @Override
    public Object getItem(int i) {
        return allReservationsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return (long) i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View reservationView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.reservation_item_layout, viewGroup, false);

        TextView reservationNameTextView = reservationView.findViewById(R.id.reservation_name_textview);
        reservationNameTextView.setText(allReservationsList.get(i).getReservationName());

        TextView reservationCheckingTextView = reservationView.findViewById(R.id.reservation_checking_textview);
        reservationCheckingTextView.setText(allReservationsList.get(i).getCheckingDate());

        TextView reservationCheckoutTextView = reservationView.findViewById(R.id.reservation_checkout_textview);
        reservationCheckoutTextView.setText(allReservationsList.get(i).getCheckoutData());

        TextView reservationPriceTextView = reservationView.findViewById(R.id.reservation_price_textView);
        reservationPriceTextView.setText(allReservationsList.get(i).getPrice()+"");

        ImageView reservationImage = reservationView.findViewById(R.id.reservation_imageview);


        Glide.with(reservationImage)
                .setDefaultRequestOptions(RequestOptions.circleCropTransform())
                .load(allReservationsList.get(i).getImage())
                .into(reservationImage);

        return reservationView;
    }
}
