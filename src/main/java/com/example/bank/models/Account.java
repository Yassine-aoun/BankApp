package com.example.bank.models;

public class Account {
    private String accountId;
    private User user;
    private double balance;

    public Account(String accountId, User user, double balance) {
        this.accountId = accountId;
        this.user = user;
        this.balance = balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Method to deposit funds into the account
    public void deposit(double amount) {
        this.balance += amount;
    }

    // Method to withdraw funds from the account
    public void withdraw(double amount) {
        if (balance >= amount) {
            this.balance -= amount;
        } else {
            System.out.println("Insufficient funds!");
        }
    }
}
