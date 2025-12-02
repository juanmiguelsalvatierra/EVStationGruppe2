package org.example;

import java.time.LocalDate;

public abstract class InvoiceItem {
    private LocalDate invoiceDate;

    public  InvoiceItem(LocalDate invoiceDate){
        this.invoiceDate = invoiceDate;
    }
}
