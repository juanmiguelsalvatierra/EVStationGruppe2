package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class InvoiceItem {
    private LocalDateTime invoiceDate;
    private int invoiceItemId;


    public  InvoiceItem(int id, LocalDateTime invoiceDate){
        this.invoiceItemId = id;
        this.invoiceDate = invoiceDate;
    }

    public LocalDateTime getInvoiceDate() {
        return invoiceDate;
    }

    public int getInvoiceItemId() {
        return invoiceItemId;
    }

    public abstract double getInvoiceValue();
}
