package org.example;

import java.util.HashMap;
import java.util.Map;

public class LocationService {
    public Map<Integer, Location> locationRepo = new HashMap<>();
    public String allLocations = "";
    public boolean duplicates = false;

    public void createLocation(String name, String address) throws Exception {
        checkDupes(name, address);
        if(!duplicates){
            int newId = locationRepo.size() + 1;
            locationRepo.put(newId, new Location(name, address));
            locationRepo.get(newId).setId(newId);
        }

    }

    public void checkDupes(String name, String address) throws Exception {
        for (Map.Entry<Integer, Location> entry: locationRepo.entrySet()){
            if(entry.getValue().getName().equals(name)){
                duplicates = true;
                throw new Exception("Location already exists");
            }
            if(entry.getValue().getAddress().equals(address)){
                duplicates = true;
                throw new Exception("Location already exists");
            }
        }
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

    public void deleteLocataion(int id) throws Exception {
        if (locationRepo.containsKey(id)){
            locationRepo.remove(id);
        } else{
            throw new Exception("Location not found");
        }
    }
}
