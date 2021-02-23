package com.britishBroadcast.hotelreservation.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.britishBroadcast.hotelreservation.model.data.Reservation;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "reservation.db";
    public static int DATABASE_VERSION = 1; // ALWAYS GREATER THAN ZERO

    // Table name
    public static final String TABLE_NAME = "reservation";
    // Column names
    public static final String RESERVATION_ID_COLUMN = "reservation_id";
    public static final String RESERVATION_NAME_COLUMN = "reservation_name";
    public static final String RESERVATION_CHECKING_COLUMN = "reservation_checking";
    public static final String RESERVATION_CHECKOUT_COLUMN = "reservation_checkout";
    public static final String RESERVATION_PRICE_COLUMN = "reservation_price";


    // Constructor
    // Simplified using class defined arguments
    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String createCommand = "CREATE TABLE " + TABLE_NAME + " ("+
                RESERVATION_ID_COLUMN  + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                RESERVATION_NAME_COLUMN + " TEXT, "+
                RESERVATION_CHECKING_COLUMN + " TEXT, "+
                RESERVATION_CHECKOUT_COLUMN + " TEXT, "+
                RESERVATION_PRICE_COLUMN + " FLOAT);";

        sqLiteDatabase.execSQL(createCommand); // Table created in the database!


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        String upgradeCommand = " DROP TABLE IF EXISTS " + TABLE_NAME ;
        sqLiteDatabase.execSQL(upgradeCommand);
        DATABASE_VERSION++;
        onCreate(sqLiteDatabase);

    }

    public void insertNewReservation(Reservation reservation){

        ContentValues values = new ContentValues();
        values.put(RESERVATION_NAME_COLUMN, reservation.getReservationName());
        values.put(RESERVATION_CHECKING_COLUMN, reservation.getCheckingDate());
        values.put(RESERVATION_CHECKOUT_COLUMN, reservation.getCheckoutData());
        values.put(RESERVATION_PRICE_COLUMN, reservation.getPrice());


        getWritableDatabase().insert(TABLE_NAME, null, values);

    }


    public ArrayList<Reservation> getAllReservations(){
        String getAll = "SELECT * FROM "+TABLE_NAME;
        Cursor allReservations =  getReadableDatabase().rawQuery(getAll, null);
        ArrayList<Reservation> reservationsArrayList = new ArrayList<>();

        while(allReservations.moveToNext()){
            int reservationID = allReservations.getInt(allReservations.getColumnIndex(RESERVATION_ID_COLUMN));
            String name = allReservations.getString(allReservations.getColumnIndex(RESERVATION_NAME_COLUMN));
            String checking = allReservations.getString(allReservations.getColumnIndex(RESERVATION_CHECKING_COLUMN));
            String checkout =  allReservations.getString(allReservations.getColumnIndex(RESERVATION_CHECKOUT_COLUMN));
            Double price =  allReservations.getDouble(allReservations.getColumnIndex(RESERVATION_PRICE_COLUMN));

            Reservation reservation = new Reservation(reservationID,name, checking, checkout, price);
            reservationsArrayList.add(reservation);

        }
        allReservations.close();
        return reservationsArrayList;
    }

    public Double getTotalAmountDue(){
        Double result = 0.0;
        String getAll = "SELECT reservation_price FROM "+TABLE_NAME;
        Cursor totalAmountDue =  getReadableDatabase().rawQuery(getAll, null);

        while(totalAmountDue.moveToNext()){
            result = result + totalAmountDue.getDouble(totalAmountDue.getColumnIndex(RESERVATION_PRICE_COLUMN));

        }

        return  result;
    }

}
