package org.example;


public class ElectricChargingStationNetwork {
    public static void main(String[] args) {
        LocationService locationService = new LocationService();
        
        locationService.createLocation("Karlsplatz Charging", "Karlsplatz 1, 1010 Wien");
        Location location = locationService.getLocationByName("Karlsplatz Charging");

        location.createCharger("AC", "FREE");
        System.out.println(location.getAllChargersAsString());
    }
}
