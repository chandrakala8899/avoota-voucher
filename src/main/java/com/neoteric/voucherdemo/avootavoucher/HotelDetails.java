package com.neoteric.voucherdemo.avootavoucher;
class HotelDetails {
    private String hotelName;
    private double hotelRating;

    public HotelDetails(String hotelName, double hotelRating) {
        this.hotelName = hotelName;
        this.hotelRating = hotelRating;
    }

    public String getHotelName() {
        return hotelName;
    }

    public double getHotelRating() {
        return hotelRating;
    }
}
