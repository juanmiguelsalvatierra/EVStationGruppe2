package org.example.Database.Entities;

public class Invoice {
    public int invoiceId;
    public int customerId;

    public Invoice(int invoiceId, int customerId){
        this.invoiceId = invoiceId;
        this.customerId = customerId;
    }
}
