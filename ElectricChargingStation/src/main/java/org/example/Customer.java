package org.example;

public class Customer {
    public int customerId;
    public String name;
    public String email;

    public Customer( String name, String email){
        this.name = name;
        this.email = email;
    }

    public void updateAccount(String name, String email){
        this.name = name;
        this.email = email;
    }

    public void setId(int newId) {
        this.customerId = newId;
    }

    @Override
    public String toString(){
        return "Name " + name + " / ID: " + customerId;
    }
}
