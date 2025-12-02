package org.example;

import java.time.LocalDate;

public class BalanceItem extends  InvoiceItem{
    private double balanceAmount;

    public BalanceItem(LocalDate invoiceDate, double balanceAmount) {
        super(invoiceDate);

        this.balanceAmount = balanceAmount;
    }
}
