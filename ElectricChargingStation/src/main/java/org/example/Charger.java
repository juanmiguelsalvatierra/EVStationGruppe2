package org.example;

public class Charger {
    public int chargerId;
    public int locationId;
    public Status status;
    public double powerRate;
    public double pricePerHour;

    public Charger(int chargerId, int locationId, Status status, double powerRate, double pricePerHour){
        this.chargerId = chargerId;
        this.locationId = locationId;
        this.status = status;
        this.powerRate = powerRate;
        this.pricePerHour = pricePerHour;
    }
}
