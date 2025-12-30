package org.example;

import java.time.LocalDateTime;

public class TransactionItem extends InvoiceItem{
    private double transactionAmount;

    public TransactionItem(int id, double transactionAmount, LocalDateTime invoiceDate) {
        super(id, invoiceDate);

        this.transactionAmount = transactionAmount;
    }

    @Override
    public double getInvoiceValue() {
        return transactionAmount;
    }

    @Override
    public String toString() {
        return String.format(
                "%d - TOPUP - %s - amount: %.2f",
                getInvoiceItemId(),
                getFormattedDate(),
                transactionAmount
        );
    }
}
