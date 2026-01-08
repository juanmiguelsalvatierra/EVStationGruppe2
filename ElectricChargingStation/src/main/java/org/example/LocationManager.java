package org.example;

import java.util.HashMap;
import java.util.Map;

public class LocationManager {
    public static Map<Integer, Location> locationRepo = new HashMap<>();

    public void createLocation(String name, String address) {
        if(!checkDupes(name, address)){
            int newId = locationRepo.size() + 1;
            locationRepo.put(newId, new Location(name, address));
            locationRepo.get(newId).setId(newId);
        } else {
            throw new IllegalArgumentException("Exception - Location already exists");
        }
    }

    public boolean checkDupes(String name, String address) {
        for (Map.Entry<Integer, Location> entry : locationRepo.entrySet()) {
            if (entry.getValue().getName().equals(name) || entry.getValue().getAddress().equals(address)) {
                return true;
            }
        }
        return false;
    }

    public String getAllLocationsAsString(){
        StringBuilder actualOutput = new StringBuilder();
        for (Location location : locationRepo.values()) {
            actualOutput.append(location.toString()).append("\n");
        }
        return actualOutput.toString();
    }

    public void updateLocationName(int id, String name){
        locationRepo.get(id).setName(name);
    }

    public void updateLocationAddress(int id, String address){
        locationRepo.get(id).setAddress(address);
    }


    public static Location getLocationByName(String name){
        return locationRepo.values()
                .stream()
                .filter(loc -> loc.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public void deleteLocation(int id) {
        if (locationRepo.containsKey(id)){
            locationRepo.remove(id);
        } else{
            throw new IllegalArgumentException("Exception - Location does not exist");
        }
    }

    public String viewLocationsInformation(){
        StringBuilder actualOutput = new StringBuilder();
        for (Location location : locationRepo.values()) {
            actualOutput.append("--- ")
                    .append(location.getName())
                    .append(" ---\n");


            if(location.chargersRepo.isEmpty()) {
                actualOutput.append("No chargers available.\n\n");
            }
            else {
                for (Charger charger : location.chargersRepo.values()) {
                    actualOutput.append(charger.toString());
                    actualOutput.append(" - Charging Price: ");
                    actualOutput.append(charger.getChargerType().equals(ChargerType.AC)
                            ? location.getCurrentPrice().price_per_kWh_AC
                            : location.getCurrentPrice().price_per_kWh_DC);
                    actualOutput.append(" € kWh");
                    actualOutput.append(" - Parking Price: ");
                    actualOutput.append(charger.getChargerType().equals(ChargerType.AC)
                            ? location.getCurrentPrice().parking_pricePerHour_AC
                            : location.getCurrentPrice().parking_pricePerHour_DC);
                    actualOutput.append(" per hour");
                    actualOutput.append("\n");
                }
                actualOutput.append("\n");
            }
        }
        return actualOutput.toString();
    }


}
