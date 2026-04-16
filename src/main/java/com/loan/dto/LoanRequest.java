package com.loan.dto;

public class LoanRequest {

    private String name;
    private String email;
    private double income;
    private int creditScore;

    private double amount;
    private int tenure;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public double getIncome() {
        return income;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public double getAmount() {
        return amount;
    }

    public int getTenure() {
        return tenure;
    }
}