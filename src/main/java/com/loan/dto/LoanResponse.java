package com.loan.dto;

public class LoanResponse {

    private String status;
    private double emi;
    private String message;

    public LoanResponse(String status, double emi, String message) {
        this.status = status;
        this.emi = emi;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public double getEmi() {
        return emi;
    }

    public String getMessage() {
        return message;
    }
}