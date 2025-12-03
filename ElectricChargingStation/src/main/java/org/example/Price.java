package org.example;
import java.time.LocalDateTime;

public class Price {
    double price_per_kWh_AC;
    double price_per_kWh_DC;
    double parking_price_AC;
    double parking_price_DC;
    LocalDateTime valid_From;

    public Price(double price_per_kWh_AC, double price_per_kWh_DC, double parking_price_AC, double parking_price_DC, LocalDateTime valid_From) {
        this.price_per_kWh_AC = price_per_kWh_AC;
        this.price_per_kWh_DC = price_per_kWh_DC;
        this.parking_price_AC = parking_price_AC;
        this.parking_price_DC = parking_price_DC;
        this.valid_From = valid_From;
    }
}
