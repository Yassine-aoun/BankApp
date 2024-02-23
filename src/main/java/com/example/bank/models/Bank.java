package com.example.bank.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Bank {
    private List<User> users;
    private List<Account> accounts;
    private List<Transaction> transactions;

    public Bank() {
        this.users = new ArrayList<>();
        this.accounts = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    // Methods for user management
    public void addUser(User user) {
        users.add(user);
    }

    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    // Methods for account management
    public void addAccount(Account account) {
        accounts.add(account);
    }

    public Account getAccountById(String accountId) {
        for (Account account : accounts) {
            if (account.getAccountId().equals(accountId)) {
                return account;
            }
        }
        return null;
    }

    // Method for depositing funds into an account
    public void deposit(Account account, double amount) {
        account.setBalance(account.getBalance() + amount);
    }

    // Method for withdrawing funds from an account
    public void withdraw(Account account, double amount) {
        if (account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    // Method for fund transfer
    public void transferFunds(Account sender, Account receiver, double amount) {
        try {
            if (authorizeTransaction(sender, amount)) {
                Transaction transaction = new Transaction(
                        "TXN_" + System.currentTimeMillis(),
                        sender,
                        receiver,
                        amount
                );
                transactions.add(transaction);
                sender.withdraw(amount);
                receiver.deposit(amount);
                transaction.setStatus("Completed");
            } else {
                throw new IllegalArgumentException("Transaction authorization failed: Insufficient funds!");
            }
        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
            // Log the exception
        }
    }

    // Method to retrieve transaction history for a particular account
    public List<Transaction> getTransactionHistory(Account account) {
        return transactions.stream()
                .filter(transaction -> transaction.getSender().equals(account) || transaction.getReceiver().equals(account))
                .collect(Collectors.toList());
    }

    // Method to add interest to savings accounts
    public void addInterestToSavingsAccounts(double interestRate) {
        for (Account account : accounts) {
            if (account instanceof SavingsAccount) {
                double interest = account.getBalance() * (interestRate / 100);
                account.deposit(interest);
            }
        }
    }

    // Method to authenticate user
    public boolean authenticateUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    // Method to authorize transaction
    public boolean authorizeTransaction(Account sender, double amount) {
        // Implement your authorization logic here, e.g., checking account balance, transaction limits, etc.
        return sender.getBalance() >= amount;
    }
}
