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

    public void chargeEVSession(Location location, int chargerId, int minutes){
        double pricePerMinute = 0.50; // BEISPIELHAFT BIS PREIS LOGIK IMPLEMENTIERT IST
        double totalCost = pricePerMinute * minutes; // BEISPIELHAFT BIS PREIS LOGIK IMPLEMENTIERT IST
        Charger charger = location.getChargersRepo().get(chargerId);

        if (charger == null) {
            System.out.println("Invalid chargerId — please insert correct chargerId");
            return;
        }

        if (charger.getStatus() == Status.OCCUPIED) {
            return;
        }

        if(getBalance() < totalCost ){
           System.out.println("Insufficient balance — please top up");
           return;
       }
        if(minutes <= 0) {
            System.out.println("Invalid time — please insert correct time");
            return;
        }

        //If checks are successful:
        charger.setStatus(Status.OCCUPIED);
        System.out.println("Charging started on charger " + chargerId + " for " + minutes + " minutes.");

        int newId = invoiceItems.size() + 1;
        invoiceItems.put(newId, new ChargingItem(newId, -totalCost));

        new Thread(() -> {
            try {
                Thread.sleep((long) minutes * 60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            charger.setStatus(Status.FREE);
            System.out.println("Charging completed — charger " + chargerId + " is now FREE.");
        }).start();
    }



    @Override
    public String toString() {
        return customerId + " - " + name + " - " + email;
    }
}
