package com.neoteric.voucherdemo.avootavoucher;

import java.util.List;

public class VoucherDetails {
    private String bookingId;
    private String hotelName;
    private String address;
    private String phoneNumber;
    private String lastCancellationDate;
    private BookingDetails bookingDetails;
    private RoomDetails roomDetails;
    private ContactDetails contactDetails;
    private CancellationPolicy cancellationPolicy;
    private List<String> bookingNotes;
    private List<String> generalTerms;
    private FareSummary fareSummary;

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLastCancellationDate() {
        return lastCancellationDate;
    }

    public void setLastCancellationDate(String lastCancellationDate) {
        this.lastCancellationDate = lastCancellationDate;
    }

    public BookingDetails getBookingDetails() {
        return bookingDetails;
    }

    public void setBookingDetails(BookingDetails bookingDetails) {
        this.bookingDetails = bookingDetails;
    }

    public RoomDetails getRoomDetails() {
        return roomDetails;
    }

    public void setRoomDetails(RoomDetails roomDetails) {
        this.roomDetails = roomDetails;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }

    public CancellationPolicy getCancellationPolicy() {
        return cancellationPolicy;
    }

    public void setCancellationPolicy(CancellationPolicy cancellationPolicy) {
        this.cancellationPolicy = cancellationPolicy;
    }

    public List<String> getBookingNotes() {
        return bookingNotes;
    }

    public void setBookingNotes(List<String> bookingNotes) {
        this.bookingNotes = bookingNotes;
    }

    public List<String> getGeneralTerms() {
        return generalTerms;
    }

    public void setGeneralTerms(List<String> generalTerms) {
        this.generalTerms = generalTerms;
    }

    public FareSummary getFareSummary() {
        return fareSummary;
    }

    public void setFareSummary(FareSummary fareSummary) {
        this.fareSummary = fareSummary;
    }
}
