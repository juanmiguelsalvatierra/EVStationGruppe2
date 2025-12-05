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

    public InvoiceItem getLatestInvoiceItem(){
        return invoiceItems.get(invoiceItems.size());
    }

    public void chargeEv(int locationId, int chargerId, int minutes, String type){
        Location location = LocationManager.locationRepo.get(locationId);
        Price price = location.getCurrentPrice();
        Charger charger = location.chargersRepo.get(chargerId);
        ChargerType chargerType = ChargerType.valueOf(type);

        if (charger == null) {
            return;
        }

        if (charger.getStatus() != Status.IN_OPERATION_FREE) {
            return;
        }

        if(minutes <= 0) {
            return;
        }

        int newChargingItemId = invoiceItems.size()+1;
        ChargingItem chargingItem = new ChargingItem(newChargingItemId, minutes, price.getId(), locationId, chargerId, chargerType);

        double balanceOfCustomer = getBalance();
        double chargingPrice = chargingItem.getInvoiceValue();

        if(balanceOfCustomer < Math.abs(chargingPrice)){
           return;
        }

        invoiceItems.put(newChargingItemId, chargingItem);
    }



    @Override
    public String toString() {
        return customerId + " - " + name + " - " + email;
    }
}
