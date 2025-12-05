package org.example;

public class ChargingItem extends InvoiceItem {

    private double chargingAmount;
    private int duration;

    public ChargingItem(int id, double chargingAmount) {
        super(id);
        this.chargingAmount = chargingAmount;
    }

    public int getChargingTime(){
        return this.duration;
    }

    @Override
    public double getInvoiceValue() {
        return chargingAmount;
    }
}
