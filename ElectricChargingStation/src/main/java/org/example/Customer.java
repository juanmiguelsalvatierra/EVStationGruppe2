package org.example;

import java.util.HashMap;
import java.util.List;

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

    public int getId(){
        return this.customerId;
    }

    public double getBalance(){
        return this.invoiceItems
                .values()
                .stream()
                .mapToDouble(InvoiceItem::getInvoiceValue)
                .sum();
    }

    public void topUp(double topUpValue){
        if(topUpValue < 0) {
            return;
        }

        int newId = this.invoiceItems.size() + 1;

        InvoiceItem invoiceItem = new BalanceItem(newId, topUpValue);
        this.invoiceItems.put(newId, invoiceItem);
    }

    @Override
    public String toString() {
        return customerId + " - " + name + " - " + email;
    }
}
