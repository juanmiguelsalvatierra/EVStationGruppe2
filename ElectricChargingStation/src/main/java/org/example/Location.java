package org.example;

import java.util.List;

public class Location {
    public int locationId;
    public String address;
    public String description;
    public List<Charger> chargerList;

    public Location(int locationId, String address, String description){
        this.locationId = locationId;
        this.address = address;
        this.description = description;
    }

    public void addCharger(Charger charger){
        charger.locationId = this.locationId;
        this.chargerList.add(charger);
    }
}
