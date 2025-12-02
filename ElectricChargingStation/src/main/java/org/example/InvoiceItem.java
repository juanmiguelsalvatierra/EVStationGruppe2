package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class InvoiceItem {
    private LocalDate invoiceDate;
    private int invoiceItemId;


    public  InvoiceItem(int id){
        this.invoiceItemId = id;
        this.invoiceDate = LocalDate.now();
    }

    public abstract double getInvoiceValue();
}
