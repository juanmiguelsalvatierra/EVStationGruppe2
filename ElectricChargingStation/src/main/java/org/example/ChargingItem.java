package org.example;

import java.time.LocalDateTime;

public class ChargingItem extends InvoiceItem {
    private int duration;
    private int priceId;
    private int locationId;
    private int chargerId;
    private ChargerType chargerType;

    public ChargingItem(int id, int duration, int priceId, int locationId, int chargerId, ChargerType chargerType, LocalDateTime invoiceDate) {
        super(id, invoiceDate);
        this.duration = duration;
        this.priceId = priceId;
        this.locationId = locationId;
        this.chargerId = chargerId;
        this.chargerType = chargerType;
    }

    public int getChargingTime(){
        return this.duration;
    }

    @Override
    public double getInvoiceValue() {
        Location location = LocationManager.locationRepo.get(locationId);
        Price price = location.priceList.get(priceId);
        Charger charger = location.chargersRepo.get(chargerId);

        double chargingCost = calculateChargingCost(price, charger);
        double parkingCost = calculateParkingCosts(price);

        return (chargingCost + parkingCost) * -1;
    }

    private double calculateParkingCosts(Price price){
        double parkingcost = 0;

        if(chargerType == ChargerType.AC){
            parkingcost = ((double) duration / 60) * price.parking_pricePerHour_AC;
        }
        else if(chargerType == ChargerType.DC){
            parkingcost = ((double) duration / 60) * price.parking_pricePerHour_DC;
        }

        return  parkingcost;
    }

    private double calculateChargingCost(Price price, Charger charger){
        double chargingcost = 0;

        if(chargerType == ChargerType.AC){
            chargingcost = ((double) duration / 60) * charger.getAC_Kw() * price.price_per_kWh_AC;
        }
        else if(chargerType == ChargerType.DC){
            chargingcost = ((double) duration / 60) * charger.getDC_Kw() * price.price_per_kWh_DC;
        }

        return  chargingcost;
    }

    @Override
    public String toString() {
        Location location = LocationManager.locationRepo.get(locationId);
        Price price = location.priceList.get(priceId);
        Charger charger = location.chargersRepo.get(chargerId);

        double energyPrice = chargerType == ChargerType.AC ? price.price_per_kWh_AC : price.price_per_kWh_DC;
        double parkingPrice = chargerType == ChargerType.AC ? price.parking_pricePerHour_AC : price.parking_pricePerHour_DC;
        double energyKwh = (duration / 60.0) *
                (chargerType == ChargerType.AC ? charger.getAC_Kw() : charger.getDC_Kw());

        double chargingCost = calculateChargingCost(price, charger);
        double parkingCost = calculateParkingCosts(price);
        double total = getInvoiceValue();

        return String.format(
                "%d - CHARGE - %s - duration: %dmin - energy: %.2fkWh - price_per_kWh_%s: %.2f - parking_price_%s: %.2f - energy_cost: %.2f - parking_cost: %.2f - total: %.2f",
                getInvoiceItemId(),
                getFormattedDate(),
                duration,
                energyKwh,
                chargerType.name(),
                energyPrice,
                chargerType.name(),
                parkingPrice,
                chargingCost,
                parkingCost,
                total
        );
    }
}
