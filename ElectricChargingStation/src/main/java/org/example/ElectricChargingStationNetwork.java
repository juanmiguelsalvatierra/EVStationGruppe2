package org.example;


public class ElectricChargingStationNetwork {
    public static void main(String[] args) {
        LocationManager locationService = new LocationManager();
        CustomerManager customerManager = new CustomerManager();
        locationService.createLocation("Karlsplatz Charging", "Karlsplatz 1, 1010 Wien");
        Location location = locationService.getLocationByName("Karlsplatz Charging");

        location.createCharger("AC", "FREE");
        System.out.println(location.getAllChargersAsString());

        customerManager.createCustomer("Hansi", "Huberti");

        Customer hansi = customerManager.customerRepo.get(1);
        hansi.topUp(200.45);
        System.out.println("Actual Balance: " + hansi.getBalance());
    }
}
