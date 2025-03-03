package com.neoteric.voucherdemo.avootavoucher;

public class BookingDetails {
    private String checkIn;
    private String checkOut;
    private int totalRooms;
    private String totalStay;

    public CancellationPolicy getPolicy() {
        return policy;
    }

    public void setPolicy(CancellationPolicy policy) {
        this.policy = policy;
    }

    private  CancellationPolicy policy;

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public int getTotalRooms() {
        return totalRooms;
    }

    public void setTotalRooms(int totalRooms) {
        this.totalRooms = totalRooms;
    }

    public String getTotalStay() {
        return totalStay;
    }

    public void setTotalStay(String totalStay) {
        this.totalStay = totalStay;
    }

}
