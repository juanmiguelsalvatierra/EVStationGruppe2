package org.example;

public class ChargingItem extends InvoiceItem{
    public double duration;
    public double pricePerUnit;

    public ChargingItem(String itemId, double duration, double pricePerUnit){
        super(itemId, InvoiceItemType.CHARGING);
        this.duration = duration;
        this.pricePerUnit = pricePerUnit;
    }

    @Override
    public double getBalanceAmount(){
        return duration * pricePerUnit;
    }
}
