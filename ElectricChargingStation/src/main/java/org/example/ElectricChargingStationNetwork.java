package org.example;

import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ElectricChargingStationNetwork {
    public static void main(String[] args) {
        LocationManager locationService = new LocationManager();
        CustomerManager customerManager = new CustomerManager();

        System.out.println("=============================");
        System.out.println("OWNER SETUP – Prepare System");
        System.out.println("=============================");

        //#US6.1 Create Locations
        System.out.println("\n---------10 Locations are created by the Owner---------");

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

        //#US6.2 View Locations
        System.out.println("\n---------Locations---------");
        System.out.println(locationService.getAllLocationsAsString());

        //#US7.1 Create Chargers at Locations
        //#US10.2 Create Prices at Locations
        System.out.println("\n---------Chargers & Prices for the Locations are created by the Owner---------");
        //Adding chargers to location 1 Karlsplatz Charging
        location.createCharger("AC", "IN_OPERATION_FREE");
        location.createCharger("DC", "IN_OPERATION_FREE");
        location.createCharger("AC", "OUT_OF_ORDER");
        location.createPrices(1.00, 2.00, 2.50, 3.50, LocalDateTime.now());

        //Adding chargers to location 2 Prater Charging
        location2.createCharger("DC", "IN_OPERATION_FREE");
        location2.createCharger("AC", "OUT_OF_ORDER");
        location2.createCharger("DC", "OCCUPIED");
        location2.createPrices(2.00, 3.00, 3.50, 4.50, LocalDateTime.now());

        //Adding chargers to location 3 Prater Charging
        location3.createCharger("DC", "IN_OPERATION_FREE");
        location3.createCharger("AC", "OUT_OF_ORDER");
        location3.createCharger("DC", "OCCUPIED");
        location3.createPrices(2.00, 3.00, 3.50, 4.50, LocalDateTime.now());

        //Adding chargers to location 4 Karlsplatz Charging
        location4.createCharger("AC", "IN_OPERATION_FREE");
        location4.createCharger("DC", "IN_OPERATION_FREE");
        location4.createCharger("DC", "IN_OPERATION_FREE");
        location4.createPrices(1.00, 2.00, 2.50, 3.50, LocalDateTime.now());

        //Adding chargers to location 5 Prater Charging
        location5.createCharger("DC", "IN_OPERATION_FREE");
        location5.createCharger("AC", "OUT_OF_ORDER");
        location5.createCharger("DC", "IN_OPERATION_FREE");
        location5.createPrices(2.00, 3.00, 3.50, 4.50, LocalDateTime.now());

        //Adding chargers to location 6 Prater Charging
        location6.createCharger("DC", "IN_OPERATION_FREE");
        location6.createCharger("AC", "OUT_OF_ORDER");
        location6.createCharger("DC", "IN_OPERATION_FREE");
        location6.createPrices(2.00, 3.00, 3.50, 4.50, LocalDateTime.now());

        //Adding chargers to location 7 Karlsplatz Charging
        location7.createCharger("AC", "IN_OPERATION_FREE");
        location7.createCharger("DC", "IN_OPERATION_FREE");
        location7.createCharger("DC", "IN_OPERATION_FREE");
        location7.createPrices(1.00, 2.00, 2.50, 3.50, LocalDateTime.now());

        //Adding chargers to location 8 Prater Charging
        location8.createCharger("DC", "IN_OPERATION_FREE");
        location8.createCharger("AC", "OUT_OF_ORDER");
        location8.createCharger("DC", "OCCUPIED");
        location8.createPrices(2.00, 3.00, 3.50, 4.50, LocalDateTime.now());

        //Adding chargers to location 9 Prater Charging
        location9.createCharger("DC", "IN_OPERATION_FREE");
        location9.createCharger("AC", "OUT_OF_ORDER");
        location9.createCharger("DC", "OCCUPIED");
        location9.createPrices(2.00, 3.00, 3.50, 4.50, LocalDateTime.now());

        //Adding chargers to location 10 Prater Charging
        location10.createCharger("DC", "IN_OPERATION_FREE");
        location10.createCharger("DC", "IN_OPERATION_FREE");
        location10.createCharger("AC", "OUT_OF_ORDER");
        location10.createPrices(2.00, 3.00, 3.50, 4.50, LocalDateTime.now());

        //#US7.2 View Chargers at Locations
        //#US10.1 Read Prices
        //Viewing chargers at location 1 Karlsplatz Charging
        System.out.println("\n---------Chargers at Location 1: Karlsplatz Charging---------");
        System.out.println(location.getAllChargersAsString());
        System.out.println(location.getCurrentPrice());

        //Viewing chargers at location 2 Prater Charging
        System.out.println("\n---------Chargers at Location 2: Prater Charging---------");
        System.out.println(location2.getAllChargersAsString());
        System.out.println(location2.getCurrentPrice());

        //Viewing chargers at location 3 Stephansplatz Charging
        System.out.println("\n---------Chargers at Location 3: Stephansplatz Charging---------");
        System.out.println(location3.getAllChargersAsString());
        System.out.println(location3.getCurrentPrice());

        //Viewing chargers at location 4 Donauinsel Charging
        System.out.println("\n---------Chargers at Location 4: Donauinsel Charging---------");
        System.out.println(location4.getAllChargersAsString());
        System.out.println(location4.getCurrentPrice());

        //Viewing chargers at location 5 Mariahilf Charging
        System.out.println("\n---------Chargers at Location 5: Mariahilf Charging---------");
        System.out.println(location5.getAllChargersAsString());
        System.out.println(location5.getCurrentPrice());

        //Viewing chargers at location 6 Technikum Charging
        System.out.println("\n---------Chargers at Location 6: Technikum Charging---------");
        System.out.println(location6.getAllChargersAsString());
        System.out.println(location6.getCurrentPrice());

        //Viewing chargers at location 7 Wirtschaftsuni Charging
        System.out.println("\n---------Chargers at Location 7: Wirtschaftsuni Charging---------");
        System.out.println(location7.getAllChargersAsString());
        System.out.println(location7.getCurrentPrice());

        //Viewing chargers at location 8 Staatsoper Charging
        System.out.println("\n---------Chargers at Location 8: Staatsoper Charging---------");
        System.out.println(location8.getAllChargersAsString());
        System.out.println(location8.getCurrentPrice());

        //Viewing chargers at location 9 Rathaus Charging
        System.out.println("\n---------Chargers at Location 9: Rathaus Charging---------");
        System.out.println(location9.getAllChargersAsString());
        System.out.println(location9.getCurrentPrice());

        //Viewing chargers at location 10 Belvedere Charging
        System.out.println("\n---------Chargers at Location 10: Belvedere Charging---------");
        System.out.println(location10.getAllChargersAsString());
        System.out.println(location10.getCurrentPrice());

        System.out.println("\n=======================================");
        System.out.println("CUSTOMER SETUP – Account & Transactions");
        System.out.println("=======================================");

        //#US1.1 Create Customer Account
        System.out.println("\n---------Customers Hansi and Franz are created---------");
        customerManager.createCustomer("Hansi", "Huberti@gmail.com");
        Customer hans = customerManager.customerRepo.get(1);
        customerManager.createCustomer("Franz", "Ferdinand@erzherzog.at");
        Customer franz = customerManager.customerRepo.get(2);

        //#US1.4 View Customers
        System.out.println("\n---------Customers---------");
        System.out.println(customerManager.getAllCustomersAsString());

        //#US1.2 Update Customer Account
        System.out.println("---------Customer Hansi updates his name and email---------");
        customerManager.updateCustomerName(1, "Hans");
        customerManager.updateCustomerEmail(1, "Hubert@gmx.at");
        //Customers displayed again to show changes
        System.out.println("\n---------Customers after update---------");
        System.out.println(customerManager.getAllCustomersAsString());

        //#US2.2 TopUp Balance
        //Customer Hans tops up 200.45 € at 1.12.2025 - 14:00
        LocalDateTime topUpDateTime = LocalDateTime.parse("2025-12-01T14:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        hans.topUp(200.45, topUpDateTime);
        System.out.println("---------Hans tops up 200.45€ at 1.12.2025 14:00---------");

        //Customer Franz tops up 300.99 € at 1.12.2025 14:30
        topUpDateTime = LocalDateTime.parse("2025-12-01T14:30:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        franz.topUp(300.99, topUpDateTime);
        System.out.println("\n---------Franzi tops up 300.99€ at 1.12.2025 14:30---------");

        //#US2.1 View Balance
        System.out.println("\n---------Balances---------");
        System.out.println("Hans's balance: " + hans.getBalance() + " €");
        System.out.println("Franz's balance: " + franz.getBalance() + " €");

        //#US2.3 Withdraw Transaction
        //Customer Franz withdraws 100.99€ at 3.12.2025 16:00
        LocalDateTime withdrawDateTime = LocalDateTime.parse("2025-12-03T16:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        franz.withdraw(100.99, withdrawDateTime);
        System.out.println("\n---------Franzi withdraws 100.99€ at 3.12.2025 16:00---------");

        //View Balance again after Franz's withdrawal
        System.out.println("\n---------Balances after withdrawal---------");
        System.out.println("Hans's balance: " + hans.getBalance() + " €");
        System.out.println("Franz's balance: " + franz.getBalance() + " €");

        System.out.println("\n=======================================");
        System.out.println("CUSTOMER - Plan & Execute Charge");
        System.out.println("=======================================");

        System.out.println("\n---------The customers use the locations information to find free nearby Chargers---------");

        //#US8.1 View Locations Information
        System.out.println("\n---------Locations Information---------");
        System.out.println(locationService.viewLocationsInformation());

        //#US4.1 Charge EV
        //Hansi charges his EV for 60 minutes at location 1 Karlsplatz Charging, charger 1 (AC) at 26.12.2025 - 18:00
        LocalDateTime chargingDateTime = LocalDateTime.parse("2025-12-26T18:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        hans.chargeEv(1, 1, 60, "AC", chargingDateTime);
        System.out.println("---------Hansi charges his EV at Location 1 Karlsplatz, Charger 1, Type AC for 60 minutes at 26.12.2025 - 18:00---------");


        //Franz charges his EV for 30 minutes at location 9 Rathaus Charging, charger 1 (DC) at 27.12.2025 - 19:30
        chargingDateTime = LocalDateTime.parse("2025-12-27T19:30:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        franz.chargeEv(9, 1, 30, "DC", chargingDateTime);
        System.out.println("---------Franz charges his EV at Location 9 Rathaus, Charger 1, Type DC for 30 minutes at 27.12.2025 - 19:30---------");


        //#US5.1 View Invoice Items
        //Franz wants to see his invoice items
        System.out.println("\n---------Franz wants to see his charging & transaction history---------");
        System.out.println("\n---------Franz's Invoice Items---------");
        System.out.println(franz.getAllInvoiceItemsAsString());

        //#US11.1 View all Invoices
        //The Owner wants to see all the invoices
        System.out.println("\n---------The Owner wants to see all the invoices---------");
        System.out.println("\n---------All Invoices in the system---------");
        System.out.println(customerManager.viewAllInvoices());

        //#US1.3 Delete Customer Account
        //Hans wants to delete his account
        System.out.println("\n---------Finally, Hans wants to delete his account, only Franz is left---------");
        customerManager.deleteCustomer(1);
        //Displaying the customer accounts once again
        System.out.println("\n---------Customers---------");
        System.out.println(customerManager.getAllCustomersAsString());

        System.out.println("=======================================");
        System.out.println("OWNER - Maintenance");
        System.out.println("=======================================");

        //#US7.3 Update Charger
        //The Owner updates the status of a Charger at Location 5
        System.out.println("\n---------The Owner updates Charger 1 at Location 5---------");
        System.out.println("\n---------Old Chargers at Location 5---------");
        System.out.println(location5.getAllChargersAsString());
        location5.updateCharger(1, "AC", "OUT_OF_ORDER");
        System.out.println("---------New Chargers at Location 5---------");
        System.out.println(location5.getAllChargersAsString());

        //#US7.4 Delete Charger
        //The Owner deletes Charger with the ID 3 from Location 5
        System.out.println("---------The Owner deletes the Charger with the ID 3 from Location 5---------");
        location5.deleteCharger(3);
        System.out.println("\n---------Chargers at Location 5---------");
        System.out.println(location5.getAllChargersAsString());

        //#US6.3 Update Locations
        //The Owner updates the location 2
        System.out.println("---------The Owner updates name and address of location 2---------");
        System.out.println("\n---------Old name and address of location 2---------");
        System.out.println(location2);
        System.out.println("\n---------New name and address of location 2---------");
        locationService.updateLocationAddress(2, "Riesenrad 1, 1020 Wien");
        locationService.updateLocationName(2, "Riesenrad Charging");
        System.out.println(location2);

        //#US6.4 Delete Location
        //The Owner deletes the location 3
        System.out.println("\n---------The Owner deletes Location 3---------");
        locationService.deleteLocation(3);
        System.out.println("\n---------Locations---------");
        System.out.println(locationService.getAllLocationsAsString());



    }
}
