package org.example;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class ChargeEVStepdefs {
    CustomerManager customerManager = new CustomerManager();
    LocationManager locationManager = new LocationManager();
    //region Background
    @Given("a customer {string} with the email {string} exists")
    public void aCustomerWithTheEmailExists(String name, String email) {
        customerManager.createCustomer(name, email);
    }

    @And("customer {string} has id {int}")
    public void customerHasId(String name, int id) {
        Customer foundCustomer = customerManager.customerRepo.get(id);

        assertNotNull(foundCustomer);
        assertEquals(id, foundCustomer.getId());
        assertEquals(name, foundCustomer.getName());
    }
    @And("a location {string} with the address {string} exists")
    public void aLocationWithTheAddressExists(String name, String address) {
        LocationManager.locationRepo.clear();
        locationManager.createLocation(name, address);
    }

    @And("the location {string} has a charger with Id {int} of type {string} and status {string}")
    public void theLocationHasAChargerWithIdOfTypeAndStatus(String locationName, int chargerId, String type, String status) {
        Location loc = locationManager.getLocationByName(locationName);

        Charger charger = loc.createCharger(type, status);

        assertEquals(chargerId, charger.getChargerId());
    }

    @And("the location {string} has the following current prices:")
    public void theLocationWithIdHasTheFollowingCurrentPrices(String locationName, DataTable pricesDataTable) {
        Location l = LocationManager.getLocationByName(locationName);
        l.priceList.clear();

        List<Map<String, String>> prices = pricesDataTable.asMaps();
        double parkingPriceDC = 0;
        double parkingPriceAC = 0;
        double pricePerKwhDC = 0;
        double pricePerKwhAC = 0;
        for (Map<String, String> priceRow : prices) {
            // Parse the values from the table
            pricePerKwhAC = Double.parseDouble(priceRow.get("price_per_kWh_AC"));
            pricePerKwhDC = Double.parseDouble(priceRow.get("price_per_kWh_DC"));
            parkingPriceAC = Double.parseDouble(priceRow.get("parking_price_AC"));
            parkingPriceDC = Double.parseDouble(priceRow.get("parking_price_DC"));
        }

        l.createPrices(pricePerKwhAC, pricePerKwhDC, parkingPriceAC, parkingPriceDC, LocalDateTime.now());
    }
    //endregion
    @Given("a customer with id {int} has a balance of {int} €")
    public void aCustomerWithIdHasABalanceOf€(int customerId, int expectedBalance) {
        Customer foundCustomer = customerManager.customerRepo.get(customerId);

        foundCustomer.topUp(expectedBalance, LocalDateTime.now());

        double actuelBalance = foundCustomer.getBalance();

        assertEquals(expectedBalance, actuelBalance);
    }

    @When("the customer with id {int} charges at location {string} at the charger with id {int} using {string} mode for {int} minutes")
    public void theCustomerWithIdAttemptsToChargeAtLocationAtTheChargerWithIdForMinutes(int customerId, String locationName, int chargerId, String type, int minutes) {
        Customer foundCustomer = customerManager.customerRepo.get(customerId);
        Location location = locationManager.getLocationByName(locationName);

        foundCustomer.chargeEv(location.getId(), chargerId, minutes, type, LocalDateTime.now());
    }

    @Then("the last invoice item from customer {int} reflects the correct price {double} and duration {int} minutes")
    public void theLastInvoiceItemFromCustomerReflectsTheCorrectPriceAndDurationMinutes(int customerId, double price, int minutes) {
        Customer foundCustomer = customerManager.customerRepo.get(customerId);

        InvoiceItem  invoiceItem = foundCustomer.getLatestInvoiceItem();

        ChargingItem chargingItem = assertInstanceOf(ChargingItem.class, invoiceItem);

        assertEquals(price, chargingItem.getInvoiceValue() * (-1));
        assertEquals(minutes, chargingItem.getChargingTime());
    }

    @And("at location {string} exists a charger with id {int} of type {string} and status {string}")
    public void atLocationExistsAChargerWithIdOfTypeAndStatus(String locationName, int chargerId, String type, String status) {
        Location location = locationManager.getLocationByName(locationName);
        Charger charger = location.createCharger(type, status);

        assertEquals(chargerId, charger.getChargerId());
    }

    @And("the balance of customer with id {int} is {double} €")
    public void theBalanceOfCustomerWithIdIs€(int customerId, double expectedBalance) {
        Customer foundCustomer = customerManager.customerRepo.get(customerId);

        double actuelBalance = foundCustomer.getBalance();

        assertEquals(expectedBalance, actuelBalance);
    }

    @Then("the balance for customer {int} remains {double} €")
    public void theBalanceForCustomerRemains€(int customerId, double expectedBalance) {
        Customer foundCustomer = customerManager.customerRepo.get(customerId);

        double actuelBalance = foundCustomer.getBalance();

        assertEquals(expectedBalance, actuelBalance);
    }
}
