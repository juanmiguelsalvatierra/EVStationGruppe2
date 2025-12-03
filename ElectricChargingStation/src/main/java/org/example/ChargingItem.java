package org.example;

public class ChargingItem extends InvoiceItem {

    private double chargingAmount;

    public ChargingItem(int id, double chargingAmount) {
        super(id);
        this.chargingAmount = chargingAmount;
    }


    @Override
    public double getInvoiceValue() {
        return chargingAmount;
    }
}
