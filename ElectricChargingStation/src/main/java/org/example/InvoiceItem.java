package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class InvoiceItem {
    private LocalDateTime invoiceDate;
    private int invoiceItemId;

    protected static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");


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

    protected String getFormattedDate() {
        return invoiceDate.format(DATE_FORMAT);
    }

    public abstract double getInvoiceValue();
}
