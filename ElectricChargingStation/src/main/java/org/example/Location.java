package org.example;

import java.util.List;

public class Location {
    public int locationId;
    private String address;
    private String description;
    //public List<Charger> chargerList;

    public Location(String address, String description){
        this.address = address;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Location ID: " + locationId +
                " | Address: " + address +
                " | Description: " + description;
    }

    /*//juan
    public void addCharger(Charger charger){
        charger.locationId = this.locationId;
        this.chargerList.add(charger);
    }*/
}
