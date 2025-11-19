package org.example.Database.Entities;

public class ChargingItem extends InvoiceItem {
    public double duration;
    public double pricePerKwh;

    public ChargingItem(String itemId, double duration, double pricePerKwh){
        super(itemId, InvoiceItemType.CHARGING);
        this.duration = duration;
        this.pricePerKwh = pricePerKwh;
    }

    @Override
    public double getBalanceAmount(){
        return (duration / 60) * pricePerKwh;
    }
}
