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
            System.out.println("Location not found");
        }
    }
}
