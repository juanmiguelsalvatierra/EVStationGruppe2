package org.example;

import java.time.LocalDate;

public class BalanceItem extends InvoiceItem{
    private double balanceAmount;

    public BalanceItem(int id, double balanceAmount) {
        super(id);

        this.balanceAmount = balanceAmount;
    }

    @Override
    public double getInvoiceValue() {
        return balanceAmount;
    }

    @Override
    public String toString() {
        return String.format(
                "%d - TOPUP - amount: %.2f",
                getInvoiceItemId(),
                balanceAmount
        );
    }
}
