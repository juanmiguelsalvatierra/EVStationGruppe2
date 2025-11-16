package org.example;

public class Customer {
    public int customerId;
    public String name;
    public String email;
    private double balance;

    public Customer(int customerId, String name, String email){
        this.customerId = customerId;
        this.name = name;
        this.email = email;
    }

    public void updateAccount(String name, String email){
        this.name = name;
        this.email = email;
    }

    public double topUpBalance(double amount){
        this.balance += amount;
        return this.balance;
    }

    public double withdrawBalance(double amount){
        if(this.balance >= amount){
            this.balance -= amount;
        }
        return this.balance;
    }

    public double viewBalance(){
        return this.balance;
    }
}
