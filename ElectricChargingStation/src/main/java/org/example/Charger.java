package org.example;

public class Charger {
    public int chargerId;
    public int locationId;
    public ChargerType chargerType;
    public Status status;
    public double powerRate;
    public double pricePerHour;

    public Charger(int locationId, ChargerType chargerType, Status status, double powerRate, double pricePerHour){
        this.locationId = locationId;
        this.chargerType = chargerType;
        this.status = status;
        this.powerRate = powerRate;
        this.pricePerHour = pricePerHour;
    }


    public String toString(){
        return "Charger ID: " + chargerId +
                " | Status: " + status +
                " | power rate: " + powerRate+
                " | Price per hour: " + pricePerHour;
    }
}
