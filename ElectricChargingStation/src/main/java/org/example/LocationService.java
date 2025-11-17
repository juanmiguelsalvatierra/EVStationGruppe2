package org.example;

import java.util.List;

public class LocationService {
    private LocationRepository locationRepo;

    public LocationService(LocationRepository locationRepo) {
        this.locationRepo = locationRepo;
    }

    public Location createLocation(String address, String description) {
        Location loc = new Location(address, description);
        return locationRepo.save(loc);
    }

    /*//nur all-locations required?
    public Location getLocation(int id) {
        return locationRepo.findById(id);
    }*/

    public List<Location> ReadAllLocations() {
        return locationRepo.ReadAll();
    }
}
