package org.example;

import java.util.HashMap;

public class Customer {
    private int customerId;
    private String name;
    private String email;
    public HashMap<Integer, InvoiceItem> invoiceItems = new HashMap<>();

    public Customer(String name, String email){
        this.name = name;
        this.email = email;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id){
        this.customerId = id;
    }

    @Override
    public String toString() {
        return customerId + " - " + name + " - " + email;
    }
}
