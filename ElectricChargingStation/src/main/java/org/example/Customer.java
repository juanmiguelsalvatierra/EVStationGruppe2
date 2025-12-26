package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.time.LocalDateTime;

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

    public void topUp(double topUpValue, LocalDateTime topUpDateTime){
        if(topUpValue < 0) {
            throw new IllegalArgumentException("Exception - negativ top up value is not allowed");
        }

        if(isOlderThanNewest(topUpDateTime)){
            throw new IllegalArgumentException("Exception - invalid Date");
        }

        int newId = this.invoiceItems.size() + 1;

        InvoiceItem invoiceItem = new TransactionItem(newId, topUpValue, topUpDateTime);
        this.invoiceItems.put(newId, invoiceItem);
    }

    public InvoiceItem getLatestInvoiceItem(){
        return invoiceItems.get(invoiceItems.size());
    }

    public void chargeEv(int locationId, int chargerId, int minutes, String type, LocalDateTime chargeDateTime){
        Location location = LocationManager.locationRepo.get(locationId);
        Price price = location.getCurrentPrice();
        Charger charger = location.chargersRepo.get(chargerId);
        ChargerType chargerType = ChargerType.valueOf(type);

        if (charger == null) {
            throw new IllegalArgumentException("Exception - invalid Charger Id");
        }

        if (charger.getStatus() != Status.IN_OPERATION_FREE) {
            throw new IllegalArgumentException("Exception - invalid charging status");
        }

        if(minutes <= 0) {
            throw new IllegalArgumentException("Exception - invalid charging time");
        }

        if(isOlderThanNewest(chargeDateTime)){
            throw new IllegalArgumentException("Exception - invalid Date");
        }

        int newChargingItemId = invoiceItems.size()+1;
        ChargingItem chargingItem = new ChargingItem(newChargingItemId, minutes, price.getId(), locationId, chargerId, chargerType, chargeDateTime);

        double balanceOfCustomer = getBalance();
        double chargingPrice = chargingItem.getInvoiceValue();

        if(balanceOfCustomer < Math.abs(chargingPrice)){
            throw new IllegalArgumentException("Exception - insufficient balance");
        }

        invoiceItems.put(newChargingItemId, chargingItem);
    }

    private boolean isOlderThanNewest(LocalDateTime newInvoiceDate) {
        if (invoiceItems.isEmpty()) {
            return false;
        }

        LocalDateTime newestExisting = invoiceItems.values().stream()
                .map(InvoiceItem::getInvoiceDate)
                .max(LocalDateTime::compareTo)
                .orElseThrow();

        return newInvoiceDate.isBefore(newestExisting);
    }


    @Override
    public String toString() {
        return customerId + " - " + name + " - " + email;
    }

    public String getAllInvoiceItemsAsString() {
        StringBuilder sb = new StringBuilder();
        double runningBalance = 0;

        // Sortieren nach ID, damit 1, 2, 3 … korrekt ausgegeben werden
        List<Integer> keys = new ArrayList<>(invoiceItems.keySet());
        Collections.sort(keys);

        for (Integer id : keys) {
            InvoiceItem item = invoiceItems.get(id);
            double value = item.getInvoiceValue();
            runningBalance += value;

            sb.append(item.toString());
            sb.append(String.format(" - balance after: %.2f%n", runningBalance));
        }

        return sb.toString();
    }
}
