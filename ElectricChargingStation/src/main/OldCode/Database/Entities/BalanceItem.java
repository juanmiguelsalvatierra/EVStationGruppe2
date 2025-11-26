package org.example.Database.Entities;

public class BalanceItem extends InvoiceItem {
    public double amount;

    public BalanceItem(String itemId, double amount){
        super(itemId, InvoiceItemType.TOPUP);
        this.amount = amount;
    }

    @Override
    public double getBalanceAmount(){
        return amount;
    }
}
