package org.example;

import java.util.HashMap;

public class Location {
    private int locationId;
    private String name;
    private String address;
    public HashMap<Integer, Charger> chargersRepo = new HashMap<>();

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

    public void setId(int id){
        this.locationId = id;
    }

    public Charger createCharger(String chargerType, String status){
        int newId = chargersRepo.size() + 1;
        chargersRepo.put(newId, new Charger(chargerType, status));
        chargersRepo.get(newId).setId(newId);
        return chargersRepo.get(newId);
    }

    public String getAllChargersAsString(){
        StringBuilder actualOutput = new StringBuilder();
        for (Charger charger : chargersRepo.values()) {
           actualOutput.append(charger.toString()).append("\n");
        }
        return actualOutput.toString();
    }


    @Override
    public String toString() {
        return locationId + " - " + name + " - " + address;
    }
}
