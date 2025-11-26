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
        StringBuilder actualOutput = new StringBuilder();
        for (Location location : locationRepo.values()) {
            actualOutput.append(location.toString()).append("\n");
        }
        return actualOutput.toString();
    }

    public void updateLocation(int id, String name){
        locationRepo.get(id).setName(name);
    }

    public void deleteLocataion(int id){
        locationRepo.remove(id);
    }
}
