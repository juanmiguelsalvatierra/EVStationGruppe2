package org.example;

import java.util.HashMap;
import java.util.Map;

public class LocationService {
    public Map<Integer, Location> locationRepo = new HashMap<>();
    public String allLocations = "";
    public String dupesStatus = "No dupes";

    public void createLocation(String name, String address) {
        checkDupes(name, address);
        if(dupesStatus.equals("No dupes")){
            int newId = locationRepo.size() + 1;
            locationRepo.put(newId, new Location(name, address));
            locationRepo.get(newId).locationId = newId;
        }

    }

    public void checkDupes(String name, String address){
        for (Map.Entry<Integer, Location> entry: locationRepo.entrySet()){
            if(entry.getValue().getName().equals(name)){
                dupesStatus = "Location already exists";
                return;
            }
            if(entry.getValue().getAddress().equals(address)){
                dupesStatus = "Location already exists";
                return;
            }
        }
    }

    public String getAllLocations(){
        for (Map.Entry<Integer, Location> entry : locationRepo.entrySet()) {
            Integer locationId = entry.getValue().locationId;
            Location location = entry.getValue();

            if(entry.getKey() == locationRepo.size() - 1){
                allLocations += entry.getValue();
            } else {
                allLocations += entry.getValue() + "\n";
            }
        }
        return allLocations;
    }
}
