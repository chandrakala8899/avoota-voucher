package com.neoteric.voucherdemo.avootavoucher;

import java.util.List;

public class CancellationPolicy {
    private String cancelAfter;
    private String cancelBefore;
    private String cancellationCharges;

    private List<String> policies;


    private List<String> additionalNotes;
    public String getCancelAfter() {
        return cancelAfter;
    }

    public List<String> getPolicies() {
        return policies;
    }

    public void setPolicies(List<String> policies) {
        this.policies = policies;
    }

    public void setCancelAfter(String cancelAfter) {
        this.cancelAfter = cancelAfter;
    }

    public String getCancelBefore() {
        return cancelBefore;
    }

    public void setCancelBefore(String cancelBefore) {
        this.cancelBefore = cancelBefore;
    }

    public String getCancellationCharges() {
        return cancellationCharges;
    }

    public void setCancellationCharges(String cancellationCharges) {
        this.cancellationCharges = cancellationCharges;
    }

    public List<String> getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(List<String> additionalNotes) {
        this.additionalNotes = additionalNotes;
    }

}
