package org.example;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

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
        locationManager.createLocation(name, address);
    }

    @And("the location {string} has a charger with Id {int} of type {string} and status {string}")
    public void theLocationHasAChargerWithIdOfTypeAndStatus(String locationName, int chargerId, String type, String status) {
        Location loc = locationManager.getLocationByName(locationName);

        Charger charger = loc.createCharger(type, status);

        assertEquals(chargerId, charger.getChargerId());
    }
    //endregion
    @Given("a customer with id {int} has a balance of {int} €")
    public void aCustomerWithIdHasABalanceOf€(int customerId, int expectedBalance) {
        Customer foundCustomer = customerManager.customerRepo.get(customerId);

        foundCustomer.topUp(expectedBalance);

        double actuelBalance = foundCustomer.getBalance();

        assertEquals(expectedBalance, actuelBalance);
    }

    @When("the customer with id {int} attempts to charge at location {string} at the charger with id {int} for {int} minutes")
    public void theCustomerWithIdAttemptsToChargeAtLocationAtTheChargerWithIdForMinutes(int customerId, String locationName, int chargerId, int minutes) {
        Customer foundCustomer = customerManager.customerRepo.get(customerId);
        Location location = locationManager.getLocationByName(locationName);

        foundCustomer.chargeEv(location, chargerId, minutes);
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
