package org.example.Database.Entities;

public class Location {
    public int locationId;
    private String address;
    private String description;

    public Location(String address, String description){
        this.address = address;
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public int getLocationId() {
        return locationId;
    }

    @Override
    public String toString() {
        return "Location ID: " + locationId +
                " | Address: " + address +
                " | Description: " + description;
    }


}
