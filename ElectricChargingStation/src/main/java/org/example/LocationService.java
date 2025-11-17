package org.example;

public class LocationService {
    private LocationRepository locationRepo;

    /*//ADD TO APPLICATION CONTEXT
    public LocationRepository locationRepo = new LocationRepository();
    public LocationService locationService = new LocationService(locationRepo);
     */

    public LocationService(LocationRepository locationRepo) {
        this.locationRepo = locationRepo;
    }

    public Location createLocation(String address, String description) {
        Location loc = new Location(address, description);
        return locationRepo.save(loc);
    }
}
