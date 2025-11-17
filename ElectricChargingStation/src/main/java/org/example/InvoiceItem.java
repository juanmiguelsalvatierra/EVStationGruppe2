package org.example;

public abstract class InvoiceItem {
    public String itemId;
    public InvoiceItemType type;

    public InvoiceItem(String itemId, InvoiceItemType type){
        this.itemId = itemId;
        this.type = type;
    }

    public abstract double getBalanceAmount();
}
