package org.example;

import java.time.LocalDateTime;


public class ElectricChargingStationNetwork {
    public static void main(String[] args) {
        LocationManager locationService = new LocationManager();
        CustomerManager customerManager = new CustomerManager();

        //US1.1 Create Customer Account
        customerManager.createCustomer("Hansi", "Huberti@gmail.com");
        customerManager.createCustomer("Franz", "Ferdinand@erzherzog.at");

        //US1.4 View Customers
        System.out.println("---------Customers---------");
        System.out.println(customerManager.getAllCustomersAsString());

        //US2.2 TopUp Balance
        //Customer Hansi tops up 200.45 €
        Customer hansi = customerManager.customerRepo.get(1);
        hansi.topUp(200.45);

        //Customer Franz tops up 300.99 €
        Customer franz = customerManager.customerRepo.get(2);
        franz.topUp(300.99);

        //US2.1 View Balance
        System.out.println("---------Balances---------");
        System.out.println("Hansi's balance: " + hansi.getBalance() + " €");
        System.out.println("Franz's balance: " + franz.getBalance() + " €");

        //US6.1 Create Locations
        //Location 1: Karlsplatz Charging
        locationService.createLocation("Karlsplatz Charging", "Karlsplatz 1, 1010 Wien");
        Location location = locationService.getLocationByName("Karlsplatz Charging");

        //Location 2: Prater Charging
        locationService.createLocation("Prater Charging", "Prater 1, 1020 Wien");
        Location location2 = locationService.getLocationByName("Prater Charging");

        //US6.2 View Locations
        System.out.println("---------Locations---------");
        System.out.println(locationService.getAllLocationsAsString());


        //US7.1 Create Chargers at Locations
        //Adding chargers to location 1 Karlsplatz Charging
        location.createCharger("AC", "IN_OPERATION_FREE");
        location2.createCharger("DC", "IN_OPERATION_FREE");
        location.createPrices(1.00, 2.00, 2.50, 3.50, LocalDateTime.now());


        //Adding chargers to location 2 Prater Charging
        location2.createCharger("DC", "IN_OPERATION_FREE");
        location2.createCharger("AC", "OUT_OF_ORDER");
        location2.createPrices(2.00, 3.00, 3.50, 4.50, LocalDateTime.now());

        //US7.2 View Chargers at Locations
        //Viewing chargers at location 1 Karlsplatz Charging
        System.out.println("---------Chargers at Location 2---------");
        System.out.println(location.getAllChargersAsString());
        System.out.println(location.getCurrentPrice());
        
        //Viewing chargers at location 2 Prater Charging
        System.out.println("---------Chargers at Location 2---------");
        System.out.println(location2.getAllChargersAsString());
        System.out.println(location2.getCurrentPrice());


        //US3.1 View Locations Information
        System.out.println("---------Locations Information---------");
        String locInformation = locationService.viewLocationsInformation();
        System.out.println(locInformation);

        //US4.1 Charge EV
        //Hansi charges his EV for 60 minutes at location 1 Karlsplatz Charging, charger 1 (AC)
        hansi.chargeEv(1, 1, 60, "AC");
        //Franz charges his EV for 30 minutes at location 2 Prater Charging, charger 1 (DC)
        franz.chargeEv(2, 1, 30, "DC");


        //US4.2 View Invoice Items
        System.out.println("---------Hansi's Invoice Items---------");
        System.out.println(hansi.getAllInvoiceItemsAsString());
        System.out.println("---------Franz's Invoice Items---------");
        System.out.println(franz.getAllInvoiceItemsAsString());

        //US10.2 Update Prices & US10.1 Read Prices
        location.createPrices(1.50, 2.50, 3.00, 4.00, LocalDateTime.now());
        System.out.println("---------Updated Prices at Location 1---------");
        System.out.println(location.getCurrentPrice());
        location2.createPrices(2.50, 3.50, 4.00, 5.00, LocalDateTime.now());
        System.out.println("---------Updated Prices at Location 2---------");
        System.out.println(location2.getCurrentPrice());

        //US11.1 View All Invoices
        System.out.println("---------All Invoices for Hansi---------");
        System.out.println(hansi.getAllInvoiceItemsAsString());
        System.out.println("---------All Invoices for Franz---------");
        System.out.println(franz.getAllInvoiceItemsAsString());
    }
}
