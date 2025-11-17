package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocationRepository {
    private Map<Integer, Location> locationRepo = new HashMap<>();

    public Location save(Location location) {
        int newId = locationRepo.size() + 1;
        location.locationId = newId;
        locationRepo.put(newId, location);
        return location;
    }

    /* //nur all-locations required?
    public Location findById(int id) {
        return locationRepo.get(id);
    }*/

    public List<Location> ReadAll() {
        return new ArrayList<>(locationRepo.values());
    }
}
