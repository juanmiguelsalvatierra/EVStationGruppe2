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
        hansi.topUp(200.45, LocalDateTime.now());
        System.out.println("---------Hansi tops up 200.45€---------");

        //Customer Franz tops up 300.99 €
        Customer franz = customerManager.customerRepo.get(2);
        franz.topUp(300.99, LocalDateTime.now());
        System.out.println("---------Franzi tops up 300.99€---------");

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

        //Location 3: Stephansplatz Charging
        locationService.createLocation("Stephansplatz Charging", "Stephansplatz 3, 1010 Wien");
        Location location3 = locationService.getLocationByName("Stephansplatz Charging");

        //Location 4: Donauinsel Charging
        locationService.createLocation("Donauinsel Charging", "Donauinsel 1, 1220 Wien");
        Location location4 = locationService.getLocationByName("Donauinsel Charging");

        //Location 5: Mariahilf Charging
        locationService.createLocation("Mariahilf Charging", "Mariahilferstraße 50, 1060 Wien");
        Location location5 = locationService.getLocationByName("Mariahilf Charging");

        //Location 6: Technikum Charging
        locationService.createLocation("Technikum Charging", "Hochstaedtplatz, 1200 Wien");
        Location location6 = locationService.getLocationByName("Technikum Charging");

        //Location 7: Wirtschaftsuni Charging
        locationService.createLocation("Wirtschaftsuni Charging", "Welthandelsplatz, 1020 Wien");
        Location location7 = locationService.getLocationByName("Wirtschaftsuni Charging");

        //Location 8: Staatsoper Charging
        locationService.createLocation("Staatsoper Charging", "Opernring 2, 1010 Wien");
        Location location8 = locationService.getLocationByName("Staatsoper Charging");

        //Location 9: Rathaus Charging
        locationService.createLocation("Rathaus Charging", "Rathausplatz 1, 1010 Wien");
        Location location9 = locationService.getLocationByName("Rathaus Charging");

        //Location 10: Belvedere Charging
        locationService.createLocation("Belvedere Charging", "Prinz Eugen Straße 27, 1030 Wien");
        Location location10 = locationService.getLocationByName("Belvedere Charging");

        //US6.2 View Locations
        System.out.println("---------Locations---------");
        System.out.println(locationService.getAllLocationsAsString());


        //US7.1 Create Chargers at Locations
        //Adding chargers to location 1 Karlsplatz Charging
        location.createCharger("AC", "IN_OPERATION_FREE");
        location.createCharger("DC", "IN_OPERATION_FREE");
        location.createPrices(1.00, 2.00, 2.50, 3.50, LocalDateTime.now());

        //Adding chargers to location 2 Prater Charging
        location2.createCharger("DC", "IN_OPERATION_FREE");
        location2.createCharger("AC", "OUT_OF_ORDER");
        location2.createPrices(2.00, 3.00, 3.50, 4.50, LocalDateTime.now());

        //Adding chargers to location 3 Prater Charging
        location3.createCharger("DC", "IN_OPERATION_FREE");
        location3.createCharger("AC", "OUT_OF_ORDER");
        location3.createPrices(2.00, 3.00, 3.50, 4.50, LocalDateTime.now());

        //Adding chargers to location 4 Karlsplatz Charging
        location4.createCharger("AC", "IN_OPERATION_FREE");
        location4.createCharger("DC", "IN_OPERATION_FREE");
        location4.createPrices(1.00, 2.00, 2.50, 3.50, LocalDateTime.now());

        //Adding chargers to location 5 Prater Charging
        location5.createCharger("DC", "IN_OPERATION_FREE");
        location5.createCharger("AC", "OUT_OF_ORDER");
        location5.createPrices(2.00, 3.00, 3.50, 4.50, LocalDateTime.now());

        //Adding chargers to location 6 Prater Charging
        location6.createCharger("DC", "IN_OPERATION_FREE");
        location6.createCharger("AC", "OUT_OF_ORDER");
        location6.createPrices(2.00, 3.00, 3.50, 4.50, LocalDateTime.now());

        //Adding chargers to location 7 Karlsplatz Charging
        location7.createCharger("AC", "IN_OPERATION_FREE");
        location7.createCharger("DC", "IN_OPERATION_FREE");
        location7.createPrices(1.00, 2.00, 2.50, 3.50, LocalDateTime.now());

        //Adding chargers to location 8 Prater Charging
        location8.createCharger("DC", "IN_OPERATION_FREE");
        location8.createCharger("AC", "OUT_OF_ORDER");
        location8.createPrices(2.00, 3.00, 3.50, 4.50, LocalDateTime.now());

        //Adding chargers to location 9 Prater Charging
        location9.createCharger("DC", "IN_OPERATION_FREE");
        location9.createCharger("AC", "OUT_OF_ORDER");
        location9.createPrices(2.00, 3.00, 3.50, 4.50, LocalDateTime.now());

        //Adding chargers to location 10 Prater Charging
        location10.createCharger("DC", "IN_OPERATION_FREE");
        location10.createCharger("AC", "OUT_OF_ORDER");
        location10.createPrices(2.00, 3.00, 3.50, 4.50, LocalDateTime.now());

        //US7.2 View Chargers at Locations
        //Viewing chargers at location 1 Karlsplatz Charging
        System.out.println("---------Chargers at Location 1: Karlsplatz Charging---------");
        System.out.println(location.getAllChargersAsString());
        System.out.println(location.getCurrentPrice());
        
        //Viewing chargers at location 2 Prater Charging
        System.out.println("---------Chargers at Location 2: Prater Charging---------");
        System.out.println(location2.getAllChargersAsString());
        System.out.println(location2.getCurrentPrice());

        //Viewing chargers at location 3 Stephansplatz Charging
        System.out.println("---------Chargers at Location 3: Stephansplatz Charging---------");
        System.out.println(location3.getAllChargersAsString());
        System.out.println(location3.getCurrentPrice());

        //Viewing chargers at location 4 Donauinsel Charging
        System.out.println("---------Chargers at Location 4: Donauinsel Charging---------");
        System.out.println(location4.getAllChargersAsString());
        System.out.println(location4.getCurrentPrice());

        //Viewing chargers at location 5 Mariahilf Charging
        System.out.println("---------Chargers at Location 5: Mariahilf Charging---------");
        System.out.println(location5.getAllChargersAsString());
        System.out.println(location5.getCurrentPrice());

        //Viewing chargers at location 6 Technikum Charging
        System.out.println("---------Chargers at Location 6: Technikum Charging---------");
        System.out.println(location6.getAllChargersAsString());
        System.out.println(location6.getCurrentPrice());

        //Viewing chargers at location 7 Wirtschaftsuni Charging
        System.out.println("---------Chargers at Location 7: Wirtschaftsuni Charging---------");
        System.out.println(location7.getAllChargersAsString());
        System.out.println(location7.getCurrentPrice());

        //Viewing chargers at location 8 Staatsoper Charging
        System.out.println("---------Chargers at Location 8: Staatsoper Charging---------");
        System.out.println(location8.getAllChargersAsString());
        System.out.println(location8.getCurrentPrice());

        //Viewing chargers at location 9 Rathaus Charging
        System.out.println("---------Chargers at Location 9: Rathaus Charging---------");
        System.out.println(location9.getAllChargersAsString());
        System.out.println(location9.getCurrentPrice());

        //Viewing chargers at location 10 Belvedere Charging
        System.out.println("---------Chargers at Location 10: Belvedere Charging---------");
        System.out.println(location10.getAllChargersAsString());
        System.out.println(location10.getCurrentPrice());

        //US3.1 View Locations Information
        System.out.println("---------Locations Information---------");
        String locInformation = locationService.viewLocationsInformation();
        System.out.println(locInformation);

        //US4.1 Charge EV
        //Hansi charges his EV for 60 minutes at location 1 Karlsplatz Charging, charger 1 (AC)
        System.out.println("---------Hansi charges his EV at Location 1 Karlsplatz, Charger 1, Type AC for 60 minutes---------");
        hansi.chargeEv(1, 1, 60, "AC", LocalDateTime.now());
        //Franz charges his EV for 30 minutes at location 9 Rathaus Charging, charger 1 (DC)
        System.out.println("---------Franz charges his EV at Location 9 Rathaus, Charger 1, Type DC for 30 minutes---------");
        franz.chargeEv(9, 1, 30, "DC", LocalDateTime.now());


        //US4.2 View Invoice Items
        System.out.println("---------Hansi's Invoice Items---------");
        System.out.println(hansi.getAllInvoiceItemsAsString());
        System.out.println("---------Franz's Invoice Items---------");
        System.out.println(franz.getAllInvoiceItemsAsString());

        //US10.2 Update Prices & US10.1 Read Prices
        System.out.println("---------Prices get updated---------");
        location.createPrices(1.50, 2.50, 3.00, 4.00, LocalDateTime.now());
        System.out.println("---------Updated Prices at Location 1---------");
        System.out.println(location.getCurrentPrice());
        location2.createPrices(2.50, 3.50, 4.00, 5.00, LocalDateTime.now());
        System.out.println("---------Updated Prices at Location 2---------");
        System.out.println(location2.getCurrentPrice());

        //US11.1 View All Invoices
        System.out.println("\n---------All Invoices in the system---------");
        System.out.println(customerManager.viewAllInvoices());

        //US6.3 Update Locations
        System.out.println("---------Update name and address of location 2---------");
        System.out.println("---------Old name and address of location 2---------");
        System.out.println(location2);
        System.out.println("---------New name and address of location 2---------");
        locationService.updateLocationAddress(2, "Riesenrad 1, 1020 Wien");
        locationService.updateLocationName(2, "Riesenrad Charging");
        System.out.println(location2);

        //Get all locations
        System.out.println("---------Get all Locations to see the update---------");
        System.out.println(locationService.getAllLocationsAsString());

        //US6.4 Delete Location
        System.out.println("---------Delete Location 3---------");
        locationService.deleteLocation(3);
        System.out.println(locationService.getAllLocationsAsString());

        //US7.3 Update Charger
        System.out.println("---------Update Charger at Location 5---------");
        System.out.println("---------Old Chargers at Location 5---------");
        System.out.println(location5.getAllChargersAsString());
        location5.updateCharger(1, "AC", "OUT_OF_ORDER");
        System.out.println("---------New Chargers at Location 5---------");
        System.out.println(location5.getAllChargersAsString());

    }
}
