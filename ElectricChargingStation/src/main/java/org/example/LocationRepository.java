package org.example;

import java.util.HashMap;
import java.util.Map;

public class LocationRepository {
    private Map<Integer, Location> locationRepo = new HashMap<>();

    public Location save(Location location) {
        int newId = locationRepo.size() + 1;
        location.locationId = newId;
        locationRepo.put(newId, location);
        return location;
    }
}
