package com.neoteric.voucherdemo.avootavoucher;

public class FareSummary {
    private double baseFare;
    private double taxesAndFees;

    public String getTotalAmountPayable() {
        return totalAmountPayable;
    }

    public void setTotalAmountPayable(String totalAmountPayable) {
        this.totalAmountPayable = totalAmountPayable;
    }

    private  String totalAmountPayable;

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTaxesAndFees() {
        return taxesAndFees;
    }

    public void setTaxesAndFees(double taxesAndFees) {
        this.taxesAndFees = taxesAndFees;
    }

    public double getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(double baseFare) {
        this.baseFare = baseFare;
    }

    private double totalAmount;

}
