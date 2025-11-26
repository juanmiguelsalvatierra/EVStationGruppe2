package org.example.Database.Entities;

public class Charger {
    public int chargerId;
    public int locationId;
    public ChargerType chargerType;
    public Status status;
    public double powerRate;
    public double pricePerHour;

    public Charger(ChargerType chargerType, Status status, double powerRate, double pricePerHour){
        this.chargerType = chargerType;
        this.status = status;
        this.powerRate = powerRate;
        this.pricePerHour = pricePerHour;
    }

    public int getLocationId() {
        return locationId;
    }

    public int getChargerId() {
        return chargerId;
    }

    public ChargerType getChargerType() {
        return chargerType;
    }

    public Status getStatus() {
        return status;
    }

    public double getPowerRate() {
        return powerRate;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public String toString(){
        return "Charger ID: " + chargerId +
                " | Status: " + status +
                " | power rate: " + powerRate+
                " | Price per hour: " + pricePerHour;
    }
}
