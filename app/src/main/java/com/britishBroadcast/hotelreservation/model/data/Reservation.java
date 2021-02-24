package com.britishBroadcast.hotelreservation.model.data;

public class Reservation {
    private int id;
    private String reservationName;
    private String checkingDate;
    private String checkoutData;
    private Double price;
    private String image;


    public Reservation(int id, String reservationName, String checkInDate, String checkOutData, Double price, String image) {
        this.id = id;
        this.reservationName = reservationName;
        this.checkingDate = checkInDate;
        this.checkoutData = checkOutData;
        this.price = price;
        this.image = image;
    }


    public Reservation(String reservationName, String checkingDate, String checkoutData, Double price, String image) {
        this.reservationName = reservationName;
        this.checkingDate = checkingDate;
        this.checkoutData = checkoutData;
        this.price = price;
        this.image = image;
    }

    public String getImage(){return image;}

    public int getId() {
        return id;
    }

    public String getReservationName() {
        return reservationName;
    }

    public void setReservationName(String reservationName) {
        this.reservationName = reservationName;
    }

    public String getCheckingDate() {
        return checkingDate;
    }

    public void setCheckingDate(String checkingDate) {
        this.checkingDate = checkingDate;
    }

    public String getCheckoutData() {
        return checkoutData;
    }

    public void setCheckoutData(String checkoutData) {
        this.checkoutData = checkoutData;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return

                "Reservation name: "+ reservationName + "\n" +
                "Checking: " + checkingDate + "\n" +
                "Checkout: " + checkoutData + "\n" +
                "Price: £" + price;

    }
}
