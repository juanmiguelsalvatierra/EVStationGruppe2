package org.example;

import java.util.List;

public class Invoice {
    public int invoiceId;
    public int customerId;

    public Invoice(int invoiceId, int customerId){
        this.invoiceId = invoiceId;
        this.customerId = customerId;
    }

    public List<InvoiceItem> readInvoiceItem(){
        //ToDo
        return List.of();
    }
}
