package com.example.bank.models;

public class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(String accountId, User user, double balance, double interestRate) {
        super(accountId, user, balance);
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    // Method to add interest to the savings account
    public void addInterest() {
        double interest = getBalance() * (interestRate / 100);
        deposit(interest);
    }
}

