package org.example;

import java.util.HashMap;
import java.util.Map;

public class Location {
    private int locationId;
    private String name;
    private String address;
    public HashMap<Integer, Charger> chargers = new HashMap<>();

    public Location(String name, String address){
        this.name = name;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setId(int id){
        this.locationId = id;
    }

    public void CreateCharger(){
        chargers.put(chargers.size() + 1, new Charger());
    }

    public void ReadChargers(){
        System.out.println(chargers);
    }

    @Override
    public String toString() {
        return locationId + " - " + name + " - " + address;
    }
}
